<template>
  <div class="view">
    <div class="view-heading">
      <div class="action-container">
        <button @click="sortByCreationDate">Sort By Creation Date</button>
        <button @click="sortByTitle">Sort By Title</button>
        <div>
          <label>Filter by tags:</label>
          <input type="text" id="myInput" v-model="tagFilter" placeholder="Filter..">
          <button @click="filterByTag">Filter by tags</button>
        </div>
        <div>
          <input type="text" id="dateInput" v-model="filterDate" placeholder="yyyy/mm/dd">
          <button @click="filterByDate">Filter by date</button>
        </div>

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
import moment from 'moment'

@Component({
  components: {
    Note, PlusSquareIcon
  }
})
export default class App extends Vue {
  private tagFilter = ''
  private filterDate = ''
  private filteredNotes = this.$store.getters.notes

  containsTagFilter (tags: string[]) {
    if (tags.includes(this.tagFilter)) {
      return true
    }
    return false
  }

  filterByTag () {
    if (this.tagFilter === '') {
      this.filteredNotes = this.$store.getters.notes
    }
    this.filteredNotes = this.$store.getters.notes.filter((note: NoteData) => this.containsTagFilter(note.tags))
    this.$forceUpdate()
  }

  containsDateFilter (timestamp: number) {
    const timestampFormatted = moment.unix(Math.floor(timestamp / 1000)).format('DD.MM.YYYY')
    const dateFilterFormatted = moment.unix(Math.floor(new Date(this.filterDate).getTime() / 1000)).format('DD.MM.YYYY')
    // console.log(Math.round(new Date(this.filterDate).getTime() / 1000))
    if (timestampFormatted === dateFilterFormatted) {
      return true
    } else {
      return false
    }
  }

  filterByDate () {
    // check if date is valid
    if (this.filterDate === '') {
      // console.log("this.filterDate === ''")
      this.filteredNotes = this.$store.getters.notes
    }
    console.log(this.filterDate)
    this.filteredNotes = this.$store.getters.notes.filter((note: NoteData) => this.containsDateFilter(note.timestamp))
  }

  mounted () {
    this.$store.dispatch('sync')
    this.filteredNotes = this.$store.getters.notes
  }

  sortByCreationDate () {
    this.$store.dispatch('sortByCreationDate')
  }

  sortByTitle () {
    this.$store.dispatch('sortByTitle')
  }
}

</script>
