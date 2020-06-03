<template>
  <div class="view">
    <div class="view-heading">
      <div class="action-container">
        <router-link to="/" class="button icon" style="margin-right: 1rem;">
          <ArrowLeftCircleIcon size="32" />
        </router-link>
      </div>
      <h3>Export &amp; Import</h3>
    </div>

    <div class="view-container">
      <div class="container">
        <h5>Export</h5>

        <p>You can click the button below to generate a JSON
        file containing all of your notes. This backup can
        be then used to restore previous states of your data,
        through <strong>Import</strong>.</p>

        <button @click="doExport">Export</button>
      </div>

      <div class="container">
        <h5>Import</h5>

        <p>Select the file you want to import and then click
        on the button with the same name. Please note that
        you should only input backups made from this interface.
        No guarantee is provided if you mishandle the data,
        so be careful and don't try to break anything.</p>

        <input type="file" accept="application/json" id="json-upload">
        <button @click="doImport">Import</button>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'

import { NoteService } from '@/service/NoteService'

import { ArrowLeftCircleIcon } from 'vue-feather-icons'

const service = new NoteService()

@Component({
  components: { ArrowLeftCircleIcon }
})
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

<style scoped>
.container {
  max-width: 20rem;
  margin: 5rem;
}
</style>
