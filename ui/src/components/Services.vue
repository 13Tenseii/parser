<template>
    <div class="Services">
        <div class="Services__header">Services</div>
        <div class="Services__service-links">
            <div v-for="service in services"
                 v-bind:key="service"
                 v-text="service.$options.name"
                 v-bind:class="['Services__service_link',
                    {'Services__service_link__active': currentService === service}]"
                 v-on:click="switchTab(service)"></div>
        </div>

        <component v-bind:is="CurrentService"></component>
    </div>
</template>

<script lang="ts">
    import {Component, Vue} from "vue-property-decorator";
    import GitHubService from "@/components/services/GitHubService.vue";
    import VkService from "@/components/services/VkService.vue";
    import SoundCloudService from "@/components/services/SoundCloudService.vue";
    import {Computed} from "vuex/types/helpers";

    @Component({
        name: 'Services',
        components: {
            GitHubService,
            VkService,
            SoundCloudService
        }
    })
    export default class Services extends Vue {
        private gitHubService: GitHubService = new GitHubService();
        private vkService: VkService = new VkService();
        private soundCloudService: SoundCloudService = new SoundCloudService();

        private readonly services: Vue[] = [this.gitHubService, this.vkService, this.soundCloudService];

        private currentService: Vue = this.gitHubService;

        private switchTab(service: Vue): void {
            this.currentService = service;
        }

        private get CurrentService(): string | undefined {
            console.log(this.currentService);
            console.log("name " + this.currentService.$options.name);
            return this.currentService.$options.name;
        }
    }
</script>