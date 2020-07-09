<template>
    <div class="Github-service">
        <div class="Services__description">
            <i class="fas fa-circle Services__circle-icon"></i>
            This is a simple service that counts statistics on your GitHub account
        </div>
        <div class="Services__description">
            <label>
                <i class="fas fa-circle Services__circle-icon"></i>
                Please enter your GitHub name:
                <input class="Github-service__input"
                       v-on:blur="onBlur"
                       v-on:keypress.enter="onBlur">
            </label>
        </div>
        <div class="Github-service__stats" v-if="result">
            <div class="Github-service__stats__label">Statistics</div>
            <div class="Github-service__stat-section">
                <i class="Github-service__icon Github-service__icon__circle-icon fas fa-circle"></i>
                <div class="Github-service__stat-section__description">Total lines of code:</div>
                <div class="Github-service__stat-section__value" v-text="result.linesOfCode"></div>
            </div>
            <div class="Github-service__stat-section">
                <i class="Github-service__icon Github-service__icon__circle-icon fas fa-circle"></i>
                <div class="Github-service__stat-section__description">Total number of commits:</div>
                <div class="Github-service__stat-section__value" v-text="result.numberOfCommits"></div>
            </div>
            <div class="Github-service__stat-section">
                <i class="Github-service__icon Github-service__icon__circle-icon fas fa-circle"></i>
                <div class="Github-service__stat-section__description">Total number of repositories:</div>
                <div class="Github-service__stat-section__value" v-text="result.numberOfRepos"></div>
            </div>
            <div class="Github-service__stat-section"
                 v-for="repo in result.reposStats"
                 v-bind:key="repo">
                <i v-on:click="hideAndShowRepo(repo.repoName)"
                   v-bind:class="!isDropped(repo.repoName)
                   ? 'Github-service__icon fas fa-caret-right'
                   : 'Github-service__icon fas fa-caret-down'"></i>
                <div class="Github-service__stat-section__description" v-text="repo.repoName"></div>
                <div v-if="isDropped(repo.repoName)" class="Github-service__repo-stats">
<!--                    <component v-bind:is="new RepoS"></component>-->
                </div>
            </div>
        </div>
    </div>
</template>

<script lang="ts">
    import {Component, Vue} from "vue-property-decorator";
    import RestApi from "@/api/RestApi";
    import GitStatsDto from "@/api/dto/GitStatsDto";
    import RepoStats from "@/components/model/RepoStats.vue";
    import RepoStatsDto from "@/api/dto/RepoStatsDto";

    @Component({name: 'GitHubService'})
    export default class GitHubService extends Vue {
        private restApi: RestApi = new RestApi();
        private result: GitStatsDto | undefined;

        private gitHubName: string = '';
        private droppedRepos: string[] = [];

        private onBlur(event: Event): void {
            const elem = event.target as HTMLInputElement;
            if (!elem)
                return;

            elem.blur();
            if (this.gitHubName !== elem.value && elem.value.trim().length !== 0) {
                this.gitHubName = elem.value;
                this.restApi.getGitStats(this.gitHubName)
                    .then(resp => {
                        this.result = resp.data;
                        this.$forceUpdate();
                    })
                    .catch(() => console.log("Error"));
            }
        }

        private isDropped(repoName: string): boolean {
            return this.droppedRepos.includes(repoName);
        }

        private hideAndShowRepo(repoName: string): void {
            if (this.isDropped(repoName))
                this.droppedRepos = this.droppedRepos.filter(it => it !== repoName);
            else
                this.droppedRepos.push(repoName);
        }

        private getDate(unixTime: number): string {
            return new Date(unixTime).toDateString();
        }

        private getNewRepoStatsComponent(dto: RepoStatsDto): RepoStats {
            // let component = new RepoStats();
            // component.dto
            return new RepoStats()
        }
    }
</script>