<template>
  <div class="box">
    <div class="box-header">
      <div class="box-title">
        <input type="text" v-model="title" placeholder="Title" style="width: 100%">
      </div>

      <div class="box-actions">
        <a @click="addNewNote" href="#" class="button icon"><SaveIcon size="32" /></a>
      </div>
    </div>

    <div class="box-body">
      <textarea rows="4" v-model="description" placeholder="Give a few details"></textarea>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'

import { SaveIcon } from 'vue-feather-icons'

import { NoteService } from '../service/NoteService'

const service = new NoteService()

@Component({
  components: { SaveIcon }
})
export default class Note extends Vue {
  readonly title: string = ''
  readonly description: string = ''

  addNewNote () {
    service.addNote(this.title, this.description)
      .then(() => this.$router.go(-1))
  }
}
</script>
