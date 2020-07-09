package com.tenseii.app.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tenseii.app.dto.GitStatsDto;
import com.tenseii.app.dto.RepoStatsDto;
import com.tenseii.app.exceptions.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class GitHubService {
    private final static Logger logger = LoggerFactory.getLogger(GitHubService.class);
    private final static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

    public GitStatsDto getGitStats(String userName) throws ServiceException {
        var targetUrl = GitHubHttpsService.buildTargetUrl("users/" + userName);

        if (!isUserExist(targetUrl))
            throw new ServiceException("Bad username");

        targetUrl = GitHubHttpsService.buildTargetUrl("users/" + userName + "/repos");

        var reposSet = getReposSet(targetUrl);
        if (reposSet.isEmpty())
            throw new ServiceException("No repos found");

        var gitStatsDto = new GitStatsDto();
        Long countOfLines = 0L;
        Long countOfCommits = 0L;
        for (var repo: reposSet) {
            targetUrl = GitHubHttpsService.buildTargetUrl("repos/" + userName + "/"
                    + repo.getRepoName() + "/commits");

            var repoCommitsRefSet = getRepoCommitsRefSet(targetUrl);

            if (!repoCommitsRefSet.isEmpty()) {
                var repoLines = 0L;
                for (var commitRef: repoCommitsRefSet) {
                    var commitInfoTargetUrl = targetUrl + "/" + commitRef;
                    var calculatedAdditionStat = getCommitAdditionStat(commitInfoTargetUrl);
                    if (calculatedAdditionStat != null) {
                        countOfLines += calculatedAdditionStat;
                        repoLines += calculatedAdditionStat;
                    }
                }
                var numberOfCommits = Long.valueOf(repoCommitsRefSet.size());

                countOfCommits += numberOfCommits;
                repo.setNumberOfCommits(numberOfCommits);

                repo.setLinesOfCode(repoLines);
            }

            gitStatsDto.getReposStats().add(repo);
        }

        gitStatsDto.setLinesOfCode(countOfLines);
        gitStatsDto.setNumberOfRepos((long) reposSet.size());
        gitStatsDto.setNumberOfCommits(countOfCommits);

        return gitStatsDto;
    }

    private Integer getCommitAdditionStat(String targetUrl) {
        var response = GitHubHttpsService.performGet(targetUrl);

        if (GitHubHttpsService.isValidResponse(response)) {
            var objectMapper = new ObjectMapper();
            try {
                var jsonNode = objectMapper.readTree(response.getBody());
                var statsNode = jsonNode.get("stats");
                var additions = statsNode.get("additions").asInt();
                var deletions = statsNode.get("deletions").asInt();

                return additions - deletions;
            } catch (JsonProcessingException exc) {
                logger.error("Error parsing commit stats", exc);
                return null;
            }
        }

        return null;
    }

    private Set<String> getRepoCommitsRefSet(String targetUrl) {
        var response = GitHubHttpsService.performGet(targetUrl);

        if (GitHubHttpsService.isValidResponse(response)) {
            return getFieldValuesSetFromArrayResponse(response, "sha");
        }

        return Collections.emptySet();
    }

    private Set<RepoStatsDto> getReposSet(String targetUrl) {
        var response = GitHubHttpsService.performGet(targetUrl);

        if (GitHubHttpsService.isValidResponse(response)) {
            var objectMapper = new ObjectMapper();
            try {
                var arrayNode = objectMapper.readTree(response.getBody());
                var iterator = arrayNode.iterator();
                var resultSet = new HashSet<RepoStatsDto>();

                iterator.forEachRemaining(node -> {
                    var repoDto = new RepoStatsDto();
                    repoDto.setRepoName(node.get("name").asText());
                    repoDto.setCreatedDate(convertDate(node.get("created_at").asText()));
                    repoDto.setUpdatedDate(convertDate(node.get("updated_at").asText()));
                    resultSet.add(repoDto);
                });

                return resultSet;
            } catch (JsonProcessingException exc) {
                logger.error("Error parsing response", exc);
                return Collections.emptySet();
            }
        }

        return Collections.emptySet();
    }

    private Long convertDate(String date) {
        try {
            Date convertedDate = dateFormat.parse(date);
            return convertedDate.getTime() / 1000;
        } catch (ParseException exc) {
            logger.error("Error converting date", exc);
            return null;
        }
    }

    private boolean isUserExist(String targetUrl) {
        var response = GitHubHttpsService.performGet(targetUrl);

        return GitHubHttpsService.isValidResponse(response);
    }

    private Set<String> getFieldValuesSetFromArrayResponse(ResponseEntity<String> response, String fieldName) {
        var objectMapper = new ObjectMapper();
        try {
            var arrayNode = objectMapper.readTree(response.getBody());
            var iterator = arrayNode.iterator();
            var resultSet = new HashSet<String>();
            iterator.forEachRemaining(node -> resultSet.add(node.get(fieldName).asText()));

            return resultSet;
        } catch (JsonProcessingException exc) {
            logger.error("Error parsing response", exc);
            return Collections.emptySet();
        }
    }
}
