<template>
  <div class="box">
    <div class="box-header">
      <div class="box-title">
        <input type="text" v-model="title" placeholder="Title" style="width: 100%">
      </div>

      <div class="box-actions">
        <button @click="editNote" class="button icon small" name="edit">
          <SaveIcon size="24" />
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
import { Component, Vue, Prop } from 'vue-property-decorator'

import TagHolder from './TagHolder.vue'

import { SaveIcon } from 'vue-feather-icons'

import { NoteService } from '../service/NoteService'
import { NoteData } from '@/model'

const service = new NoteService()

@Component({
  components: { SaveIcon, TagHolder }
})
export default class Note extends Vue {
  @Prop({ default: 0 }) readonly id!: string
  private title = ''
  private description = ''
  private timestamp = 0
  private pinned = false
  private tags: string[] = []
  private completed = false
  private dateCompleted = 0

  async mounted () {
    const note: NoteData = await service.getNoteById(this.id)
    this.title = note.title
    this.description = note.description
    this.timestamp = note.timestamp
    this.pinned = note.pinned
    this.tags = note.tags
    this.completed = note.completed
    this.dateCompleted = note.dateCompleted
  }

  async editNote () {
    await service.editNote(this.id, this.title, this.description, this.timestamp, this.pinned, this.tags, this.completed, this.dateCompleted)
    this.$router.go(-1)
  }
}
</script>
