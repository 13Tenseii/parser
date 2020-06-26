package com.tenseii.parser.dto;

public class RepoStatsDto {
    private String repoName;
    private Long createdDate;
    private Long updatedDate;
    private Long numberOfCommits;
    private Long linesOfCode;

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Long updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Long getNumberOfCommits() {
        return numberOfCommits;
    }

    public void setNumberOfCommits(Long numberOfCommits) {
        this.numberOfCommits = numberOfCommits;
    }

    public Long getLinesOfCode() {
        return linesOfCode;
    }

    public void setLinesOfCode(Long linesOfCode) {
        this.linesOfCode = linesOfCode;
    }
}
