<template>
    <div class="">
        <img src="../../public/logo.png">
    </div>
</template>

<script lang="ts">
    import {Component, Vue} from "vue-property-decorator";
    // font phantasm
    @Component
    export default class MainPage extends Vue {
        private message: string = 'Main Page';
    }
</script>
