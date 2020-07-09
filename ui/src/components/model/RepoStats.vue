<template>
    <div class="Services">

    </div>
</template>

<script lang="ts">
    import {Component, Vue} from "vue-property-decorator";
    import RepoStatsDto from "@/api/dto/RepoStatsDto";


    @Component({name: 'RepoStats'})
    export default class RepoStats extends Vue {

        private _dto: RepoStatsDto | undefined;

        set dto(value: RepoStatsDto | undefined) {
            this._dto = value;
        }


        get dto(): RepoStatsDto | undefined {
            return this._dto;
        }
    }
</script>