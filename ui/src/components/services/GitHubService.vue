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
    </div>
</template>

<script lang="ts">
    import {Component, Vue} from "vue-property-decorator";
    import RestApi from "@/api/RestApi";

    @Component({name: 'GitHubService'})
    export default class GitHubService extends Vue {
        private restApi: RestApi = new RestApi();

        private gitHubName: string = '';

        private onClick(): void {
            console.log(this.gitHubName);
            this.restApi.getLinesOfCode(this.gitHubName);
        }
    }
</script>