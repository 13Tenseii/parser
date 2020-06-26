export default interface RepoStatsDto {
    repoName: string,
    createdAt: number,
    updatedAt: number,
    numberOfCommits: number,
    linesOfCode: number;
}