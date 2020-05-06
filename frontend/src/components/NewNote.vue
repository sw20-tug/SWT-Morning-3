<template>
  <div class="box">
    <div class="box-header">
      <div class="box-title">
        <input type="text" v-model="title" placeholder="Title" style="width: 100%">
      </div>

      <div class="box-actions">
        <button @click="addNewNote" class="button icon" name="add">
          <SaveIcon size="32" />
        </button>
      </div>
    </div>

    <div class="box-body">
      <textarea rows="4" v-model="description" placeholder="Give a few details"></textarea>
      <TagHolder :tags="tags" id="tags"></TagHolder>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'

import { SaveIcon } from 'vue-feather-icons'

import { NoteService } from '../service/NoteService'
import TagHolder from './TagHolder.vue'

const service = new NoteService()

@Component({
  components: { SaveIcon, TagHolder }
})
export default class Note extends Vue {
  readonly title: string = ''
  readonly description: string = ''
  readonly tags: string[] = []

  async addNewNote () {
    await service.addNote(this.title, this.description, this.tags)
    this.$router.go(-1)
  }
}
</script>
