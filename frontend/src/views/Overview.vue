<template>
  <div class="view">
    <div class="view-heading">
      <router-link to="/new" class="button icon" title="Create new note"><PlusSquareIcon size="32" data-cy="new-note" /></router-link>
      <router-link to="/io" class="button icon" title="Import/Export notes"><HexagonIcon size="32" /></router-link>

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

    <div class="action-container" :class="{ shown: actionsShown }">
      <a data-cy="toggle-actions" class="button icon" @click="actionsShown = !actionsShown" title="Actions"><MenuIcon size="32" /></a>
      <div v-if="actionsShown">
        <p>Sort:
          <button class="button" id="btn-sort-by-date" @click="sortByCreationDate">By creation date</button>
          <button class="button" id="btn-sort-by-title" @click="sortByTitle">By title</button>
        </p>
        <div class="container">
          <label for="myInput">Filter by tags</label>
          <div class="row">
            <input type="text" id="myInput" v-model="tagFilter" placeholder="Input tag">
            <button id="btn-filter-by-tag" @click="committedTagFilter = tagFilter">
              Apply
            </button>
          </div>
        </div>

        <div class="container">
          <label for="dateInput">Filter by date</label>
          <div class="row">
            <input type="date" id="dateInput" v-model="dateFilter">
            <button id="btn-filter-by-date" @click="committedDateFilter = dateFilter">
              Apply
            </button>
          </div>
        </div>

        <button id="btn-reset-filters" @click="committedTagFilter = ''; committedDateFilter = ''">Remove filters</button>

      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import Note from '@/components/Note.vue'
import { NoteData } from '@/model'
import { PlusSquareIcon, HexagonIcon, MenuIcon } from 'vue-feather-icons'
import moment from 'moment'

@Component({
  components: {
    Note, PlusSquareIcon, HexagonIcon, MenuIcon
  }
})
export default class Overview extends Vue {
  private tagFilter = ''
  private dateFilter = ''

  private committedTagFilter = ''
  private committedDateFilter = ''

  private actionsShown = false

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

<style scoped>
.action-container {
  width: 25%;
  padding: 0.5rem;

  position: absolute;
  bottom: 0;

  transition: 0.1s all;
}

.action-container.shown {
  background: rgba(255, 255, 255, 0.9);
  box-shadow: 0 0 2rem 0.1rem;
  bottom: 2rem;

  border: 1px solid rgba(0, 0, 0, 0.1);
  border-radius: 0.25rem;
}

.row {
  display: flex;
}

.container {
  margin-bottom: 1rem;
}
</style>
