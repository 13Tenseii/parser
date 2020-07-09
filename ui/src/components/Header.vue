<template>
    <div class="Header">
        <div class="Header__logo-img-container"><img src="../../public/static/logo.png" class="Header__logo-img"/></div>
        <div class="Header__links-container">
            <div class="Header__links">
                <div class="Header__link">SERVICES</div>
                <div class="Header__link">ABOUT</div>
                <div class="Header__link">CONTACTS</div>
            </div>
        </div>
    </div>
</template>

<script lang="ts">
    import {Component, Vue} from "vue-property-decorator";

    @Component({name: 'Header'})
    export default class Header extends Vue {
    }
</script>