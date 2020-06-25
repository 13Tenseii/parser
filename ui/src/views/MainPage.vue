<template>
    <div class="Main-page">
        <div class="Main-page__header-container">
            <Header></Header>
            <HeaderContent></HeaderContent>
        </div>
        <Services></Services>
        <About></About>
    </div>
</template>

<script lang="ts">
    import {Component, Vue} from "vue-property-decorator";
    import Header from "@/components/Header.vue";
    import HeaderContent from "@/components/HeaderContent.vue";
    import About from "@/components/About.vue";
    import Services from "@/components/Services.vue";

    @Component({
        name: 'MainPage',
        components: {
            Header,
            HeaderContent,
            Services,
            About
        }
    })
    export default class MainPage extends Vue {
        private header: Header = new Header();
    }
</script>
