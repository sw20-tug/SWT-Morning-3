<template>
  <div class="view">
    <div class="view-heading">
      <h3>Export &amp; Import</h3>
    </div>

    <div class="view-container">
      <button @click="doExport">Export</button>
      <hr>
      <input type="file" accept="application/json" id="json-upload">
      <button @click="doImport">Import</button>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'

import { NoteService } from '@/service/NoteService'

const service = new NoteService()

@Component({})
export default class Overview extends Vue {
  doExport () {
    const contents = JSON.stringify(this.$store.getters.notes, null, 2)
    const blob = new Blob([contents], { type: 'application/json' })
    const url = URL.createObjectURL(blob)
    window.open(url)
  }

  doImport () {
    const el = document.getElementById('json-upload')

    if (el === null) {
      return
    }

    const inputEl = el as HTMLInputElement
    const files = inputEl.files

    if (files === null) {
      return
    }

    const f: File = files[0]
    const reader = new FileReader()

    reader.addEventListener('load', () => {
      if (typeof reader.result === 'string') {
        service.importNotes(reader.result)
      }
    })

    reader.readAsText(f)
  }
}
</script>
