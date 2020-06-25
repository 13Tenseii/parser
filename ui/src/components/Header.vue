<template>
    <div class="Header">
        <img src="../../public/static/logo.png" class="Header__logo-img"/>
        <div class="Header__links">
            <div class="Header__link">Services</div>
            <div class="Header__link">About</div>
            <div class="Header__link">Contacts</div>
        </div>
    </div>
</template>

<script lang="ts">
    import {Component, Vue} from "vue-property-decorator";

    @Component({name: 'Header'})
    export default class Header extends Vue {
    }
</script>