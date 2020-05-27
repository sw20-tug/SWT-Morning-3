<template>
  <div class="view">
    <div class="view-heading">
      <h3>Export &amp; Import</h3>
    </div>

    <div class="view-container">
      <button @click="doExport">Export</button>
      <hr>
      <input type="file" accept="application/json">
      <button @click="doImport">Import</button>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import { NoteService } from '@/service/NoteService'

@Component({})
export default class Overview extends Vue {
  doExport () {
    const contents = JSON.stringify(this.$store.getters.notes, null, 2)
    const blob = new Blob([contents], { type: 'application/json' })
    const url = URL.createObjectURL(blob)
    window.open(url)
  }

  doImport () {
    console.log('did import')
  }
}
</script>
