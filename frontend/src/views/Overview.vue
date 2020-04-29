<template>
  <div class="view">
    <div class="view-heading">
      <div class="action-container">
        <router-link to="/new" class="button icon"><PlusSquareIcon size="32" /></router-link>
      </div>

      <h3>Overview</h3>
    </div>

    <div class="view-container">
      <div class="note-container">
        <Note v-for="note in $store.getters.notes" :key="note.id"
          :id="note.id" :title="note.title" :description="note.description"
          :timestamp="note.timestamp" />
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import Note from '@/components/Note.vue'

import { PlusSquareIcon } from 'vue-feather-icons'

@Component({
  components: {
    Note, PlusSquareIcon
  }
})
export default class App extends Vue {
  mounted () {
    this.$store.dispatch('sync')
  }
}
</script>
