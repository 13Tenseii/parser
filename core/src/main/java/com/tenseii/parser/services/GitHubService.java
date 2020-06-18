package com.tenseii.parser.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tenseii.parser.exceptions.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
public class GitHubService {
    private final static Logger logger = LoggerFactory.getLogger(GitHubService.class);

    public Long getLinesOfCode(String userName) throws ServiceException {
        var targetUrl = GitHubHttpsService.buildTargetUrl("users/" + userName);

        if (!isUserExist(targetUrl))
            throw new ServiceException("Bad username");

        targetUrl = GitHubHttpsService.buildTargetUrl("users/" + userName + "/repos");

        var reposNamesSet = getReposNamesSet(targetUrl);
        if (reposNamesSet.isEmpty())
            throw new ServiceException("No repos found");

        Long countOfLines = 0L;
        for (var repoName: reposNamesSet) {
            targetUrl = GitHubHttpsService.buildTargetUrl("repos/" + userName + "/" + repoName + "/commits");
            var repoCommitsRefSet = getRepoCommitsRefSet(targetUrl);

            if (!repoCommitsRefSet.isEmpty()) {
                for (var commitRef: repoCommitsRefSet) {
                    var commitInfoTargetUrl = targetUrl + "/" + commitRef;
                    var calculatedAdditionStat = getCommitAdditionStat(commitInfoTargetUrl);
                    if (calculatedAdditionStat != null)
                        countOfLines += calculatedAdditionStat;
                }
            }
        }
        return countOfLines;
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

    private Set<String> getReposNamesSet(String targetUrl) {
        var response = GitHubHttpsService.performGet(targetUrl);

        if (GitHubHttpsService.isValidResponse(response)) {
            return getFieldValuesSetFromArrayResponse(response, "name");
        }

        return Collections.emptySet();
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
