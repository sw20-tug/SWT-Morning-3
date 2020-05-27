<template>
  <div class="view">
    <div class="view-heading">
      <h3>Export &amp; Import</h3>
    </div>

    <div class="view-container">
      <button @click="doExport">Export</button>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'

@Component({})
export default class Overview extends Vue {
  doExport () {
    const contents = JSON.stringify(this.$store.getters.notes, null, 2)
    const blob = new Blob([contents], { type: 'application/json' })
    const url = URL.createObjectURL(blob)
    window.open(url)
  }
}
</script>
