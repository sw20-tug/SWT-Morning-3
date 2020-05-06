<template>
  <div class="view">
    <div class="view-heading">
      <div class="action-container">
        <button @click="sortByCreationDate">Sort By Creation Date</button>
        <button @click="sortByTitle">Sort By Title</button>
        <input type="text" id="myInput" v-model="tagFilter" placeholder="Filter..">
        <router-link to="/new" class="button icon"><PlusSquareIcon size="32" /></router-link>
      </div>

      <h3>Overview</h3>
    </div>

    <div class="view-container">
      <div class="note-container">
        <Note v-for="note in filteredNotes" :key="note.id"
          :id="note.id" :title="note.title" :description="note.description"
          :timestamp="note.timestamp" :pinned="note.pinned" :tags="note.tags"/>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import Note from '@/components/Note.vue'
import { NoteData } from '@/model'
import { PlusSquareIcon } from 'vue-feather-icons'

@Component({
  components: {
    Note, PlusSquareIcon
  }
})
export default class App extends Vue {
  private tagFilter = ''

  containsTagFilter (tags: string[]) {
    if (tags.includes(this.tagFilter)) {
      return true
    }
    return false
  }

  get filteredNotes () {
    if (this.tagFilter === '') {
      return this.$store.getters.notes
    }
    return this.$store.getters.notes.filter((note: NoteData) => this.containsTagFilter(note.tags))
  }

  mounted () {
    this.$store.dispatch('sync')
  }

  sortByCreationDate () {
    this.$store.dispatch('sortByCreationDate')
  }

  sortByTitle () {
    this.$store.dispatch('sortByTitle')
  }
}

</script>
