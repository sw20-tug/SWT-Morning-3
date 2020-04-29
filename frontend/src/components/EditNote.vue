<template>
  <div class="note">
    <div class="note-header">
      <input type="text" v-model="title">
    </div>
    <div class="note-body">
      <textarea rows="4" v-model="description"></textarea>
    </div>
    <button @click="editNote">Edit</button>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Prop } from 'vue-property-decorator'

import { NoteService } from '../service/NoteService'
import { NoteData } from '@/model'

const service = new NoteService()

@Component({})
export default class Note extends Vue {
  @Prop({ default: 0 }) readonly id!: string
  private title = ''
  private description = ''
  private pinned = false
  private tags: string[] = []

  async mounted () {
    const note: NoteData = await service.getNoteById(this.id)
    this.title = note.title
    this.description = note.description
    this.pinned = note.pinned
    this.tags = note.tags
  }

  editNote () {
    service.editNote(this.id, this.title, this.description, this.pinned, this.tags)
      .then(() => this.$router.go(-1))
  }
}
</script>

<style lang="scss">
  .note {
    font: 16px/150% sans-serif;

    border: 1px solid #ccc;
    padding: 1rem;

    &:not(first-child) {
      margin-top: 0.5rem;
    }

    & > .note-header {
      display: flex;
      flex-direction: row;
      justify-content: space-between;

      & > p.note-title {
        font-weight: 700;
      }

      & > p.note-date {
        text-align: right;
      }
    }
  }

</style>
