<template>
  <div class="note-container">
    <NewNote />
    <Note v-for="note in $store.getters.notes" :key="note.id"
      :id="note.id" :title="note.title" :description="note.description"
      :timestamp="note.timestamp" />
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import Note from './components/Note.vue'
import NewNote from './components/NewNote.vue'

@Component({
  components: {
    Note,
    NewNote
  }
})
export default class App extends Vue {
  mounted () {
    this.$store.dispatch('sync')
  }
}
</script>

<style lang="scss">
.note-container {
  display: flex;
  flex-direction: column;
  max-width: 40rem;
}
</style>
