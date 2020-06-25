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
        <keep-alive>
            <component v-bind:is="currentService"></component>
        </keep-alive>
    </div>
</template>

<script lang="ts">
    import {Component, Vue} from "vue-property-decorator";
    import Service from "@/components/services/Service";
    import GitHubService from "@/components/services/GitHubService.vue";
    import VkService from "@/components/services/VkService.vue";
    import SoundCloudService from "@/components/services/SoundCloudService.vue";

    @Component({name: 'Services'})
    export default class Services extends Vue {
        private gitHubService: GitHubService = new GitHubService();
        private vkService: VkService = new VkService();
        private soundCloudService: SoundCloudService = new SoundCloudService();

        private readonly services: Service[] = [this.gitHubService, this.vkService, this.soundCloudService];

        private currentService: Service = this.gitHubService;

        private switchTab(service: Service): void {
            this.currentService = service;
        }
    }
</script>