<template>
    <div class="Header-content">
        <div class="Header-content__central">
            <div class="Header-content__label">
                13Tenseii
            </div>
            <div class="Header-content__description">
                WebDevelopment for fun and everything else
            </div>
            <div class="Header-content__vk"><i class="fab fa-vk" v-on:click="redirectVk"></i></div>
        </div>
    </div>
</template>

<script lang="ts">
    import {Component, Vue} from "vue-property-decorator";

    @Component({name: 'HeaderContent'})
    export default class HeaderContent extends Vue {

        private redirectVk(): void {
            window.location.href = 'https://vk.com/13tenseii';
        }
    }
</script>