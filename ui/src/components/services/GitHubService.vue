<template>
    <div>
        github
    </div>
</template>

<script lang="ts">
    import {Component, Vue} from "vue-property-decorator";
    import Service from "@/components/services/Service";

    @Component({name: 'GitHubService'})
    export default class GitHubService extends Vue implements Service {
    }
</script>