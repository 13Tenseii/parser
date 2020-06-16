<template>
    <div v-text="message">
    </div>
</template>

<script lang="ts">
    import {Component, Vue} from "vue-property-decorator";

    @Component
    export default class MainPage extends Vue {
        private message: string = 'Main Page';
    }
</script>
