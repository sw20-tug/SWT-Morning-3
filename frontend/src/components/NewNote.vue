<template>
  <div class="note">
    <div class="note-header">
      <input type="text" v-model="title">
    </div>
    <div class="note-body">
      <textarea rows="4" v-model="description"></textarea>
    </div>
    <button @click="addNewNote">Add</button>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Prop } from 'vue-property-decorator'

import { NoteService } from '../service/NoteService'

const service = new NoteService()

@Component({})
export default class Note extends Vue {
  readonly title: string = ''
  readonly description: string = ''

  addNewNote () {
    service.addNote(this.title, this.description)
      .then(() => this.$store.dispatch('sync'))
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
