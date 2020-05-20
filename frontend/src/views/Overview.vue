<template>
  <div class="view">
    <div class="view-heading">
      <div class="action-container">
        <button id="btn-sort-by-date" @click="sortByCreationDate">Sort By Creation Date</button>
        <button id="btn-sort-by-title" @click="sortByTitle">Sort By Title</button>
        <div>
          <label>Filter by tags:</label>
          <input type="text" id="myInput" v-model="tagFilter" placeholder="Filter..">
          <button id="btn-filter-by-tag" @click="committedTagFilter = tagFilter">Filter by tags</button>
        </div>
        <div>
          <input type="text" id="dateInput" v-model="dateFilter" placeholder="yyyy/mm/dd">
          <button id="btn-filter-by-date" @click="committedDateFilter = dateFilter">Filter by date</button>
        </div>
        <button id="btn-reset-filters" @click="committedTagFilter = ''; committedDateFilter = ''">Remove filters</button>

        <router-link to="/new" class="button icon"><PlusSquareIcon size="32" /></router-link>
      </div>

      <h3>Overview</h3>
    </div>

    <div class="view-container">
      <div class="note-container">
        <Note v-for="note in filteredNotes" :key="note.id"
          :id="note.id" :title="note.title" :description="note.description"
          :timestamp="note.timestamp" :pinned="note.pinned" :tags="note.tags"
          :completed="note.completed" :dateCompleted="note.dateCompleted"/>
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
import { NoteService } from '@/service/NoteService'

const service = new NoteService()

@Component({
  components: {
    Note, PlusSquareIcon
  }
})
export default class Overview extends Vue {
  private tagFilter = ''
  private dateFilter = ''

  private committedTagFilter = ''
  private committedDateFilter = ''

  containsTag (tag: string, tags: string[]) {
    if (tags != null && tags.includes(tag)) {
      return true
    }
    return false
  }

  get filteredNotes () {
    let notes = this.$store.getters.notes

    // apply tag filter
    if (this.committedTagFilter !== '') {
      notes = notes.filter((note: NoteData) => this.containsTag(this.committedTagFilter, note.tags))
    }

    // apply date filter
    if (this.committedDateFilter !== '') {
      notes = notes.filter((note: NoteData) => this.containsDate(this.committedDateFilter, note.timestamp))
    }

    return notes
  }

  containsDate (filter: string, timestamp: number) {
    const timestampFormatted = moment.unix(Math.floor(timestamp / 1000)).format('DD.MM.YYYY')
    const dateFilterFormatted = moment.unix(Math.floor(new Date(filter).getTime() / 1000)).format('DD.MM.YYYY')
    if (timestampFormatted === dateFilterFormatted) {
      return true
    } else {
      return false
    }
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
