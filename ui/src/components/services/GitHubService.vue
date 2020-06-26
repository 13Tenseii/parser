<template>
    <div class="Github-service">
        <div class="Services__description">
            <i class="fas fa-circle Services__circle-icon"></i>
            This is a simple service that counts the number of lines of code written on your GitHub account
        </div>
        <div class="row">
            <div class="col">
                <label>
                    <i class="fas fa-circle Services__circle-icon"></i>
                    Please enter your GitHub name
                    <input placeholder="Name" v-model="gitHubName">
                </label>
            </div>
        </div>
        <div class="row">
            <div class="col">
                <button class="Custom-btn" v-on:click="onClick">Submit</button>
            </div>
        </div>
        <div v-if="result" class="Github-service__stats">
            <label class="Github-service__stats__label">Statistics</label>
            <div class="Github-service__table__row">
                <div class="Github-service__table__definition">Total lines of code</div>
                <div class="Github-service__table__value" v-text="result.linesOfCode"></div>
            </div>
            <div class="Github-service__table__row">
                <div class="Github-service__table__definition">Total number of commits</div>
                <div class="Github-service__table__value" v-text="result.numberOfCommits"></div>
            </div>
            <div class="Github-service__table__row">
                <div class="Github-service__table__definition">Number of repos</div>
                <div class="Github-service__table__value" v-text="result.numberOfRepos"></div>
            </div>
            <div class="Github-service__table__row" v-for="repo in result.reposStats"
                 v-bind:key="repo">
                <div class="Github-service__table__row">
                    <div class="Github-service__table__definition">Repository name</div>
                    <div class="Github-service__table__value" v-text="repo.repoName"></div>
                </div>
                <div class="Github-service__table__row">
                    <div class="Github-service__table__definition">Created at</div>
                    <div class="Github-service__table__value" v-text="getDate(repo.createdAt)"></div>
                </div>
                <div class="Github-service__table__row">
                    <div class="Github-service__table__definition">Updated at</div>
                    <div class="Github-service__table__value" v-text="getDate(repo.updatedAt)"></div>
                </div>
                <div class="Github-service__table__row">
                    <div class="Github-service__table__definition">Total number of commits</div>
                    <div class="Github-service__table__value" v-text="repo.numberOfCommits"></div>
                </div>
                <div class="Github-service__table__row">
                    <div class="Github-service__table__definition">Total lines of code</div>
                    <div class="Github-service__table__value" v-text="repo.linesOfCode"></div>
                </div>
            </div>
        </div>
    </div>
</template>

<script lang="ts">
    import {Component, Vue} from "vue-property-decorator";
    import RestApi from "@/api/RestApi";
    import GitStatsDto from "@/api/dto/GitStatsDto";

    @Component({name: 'GitHubService'})
    export default class GitHubService extends Vue {
        private restApi: RestApi = new RestApi();
        private result: GitStatsDto | undefined;

        private gitHubName: string = '';

        private onClick(): void {
            console.log(this.gitHubName);
            this.restApi.getGitStats(this.gitHubName);
        }

        private getDate(unixTime: number): string {
            return new Date(unixTime).toDateString();
        }
    }
</script>