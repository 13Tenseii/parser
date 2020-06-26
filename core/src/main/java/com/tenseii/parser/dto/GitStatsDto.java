package com.tenseii.parser.dto;

import java.util.HashSet;
import java.util.Set;

public class GitStatsDto {

    private Long linesOfCode;
    private Long numberOfCommits;
    private Long numberOfRepos;
    private Set<RepoStatsDto> reposStats;

    public GitStatsDto() {
        reposStats = new HashSet<>();
    }

    public Long getLinesOfCode() {
        return linesOfCode;
    }

    public void setLinesOfCode(Long linesOfCode) {
        this.linesOfCode = linesOfCode;
    }

    public Long getNumberOfCommits() {
        return numberOfCommits;
    }

    public void setNumberOfCommits(Long numberOfCommits) {
        this.numberOfCommits = numberOfCommits;
    }

    public Long getNumberOfRepos() {
        return numberOfRepos;
    }

    public void setNumberOfRepos(Long numberOfRepos) {
        this.numberOfRepos = numberOfRepos;
    }

    public Set<RepoStatsDto> getReposStats() {
        return reposStats;
    }

    public void setReposStats(Set<RepoStatsDto> reposStats) {
        this.reposStats = reposStats;
    }
}
