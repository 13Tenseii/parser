import RepoStatsDto from "@/api/dto/RepoStatsDto";

export default interface GitStatsDto {
    linesOfCode: number,
    numberOfCommits: number,
    numberOfRepos: number,
    reposStats: RepoStatsDto[];
}