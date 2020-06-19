<template>
    <div class="">
        <Header></Header>
    </div>
</template>

<script lang="ts">
    import {Component, Vue} from "vue-property-decorator";
    import Header from "@/components/Header.vue";

    @Component({
        components: {
            Header
        }
    })
    export default class MainPage extends Vue {
        private header: Header = new Header();
    }
</script>
