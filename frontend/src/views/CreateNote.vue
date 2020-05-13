<template>
  <div class="view">
    <div class="view-heading">
      <div class="action-container">
        <router-link to="/" class="button icon" style="margin-right: 1rem;">
          <ArrowLeftCircleIcon size="32" />
        </router-link>
      </div>

      <h3>New note</h3>
    </div>

    <div class="view-content">
      <NewNote />
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import NewNote from '@/components/NewNote.vue'

import { ArrowLeftCircleIcon } from 'vue-feather-icons'

@Component({
  components: {
    NewNote, ArrowLeftCircleIcon
  }
})
export default class App extends Vue {
  mounted () {
    this.$store.dispatch('sync')
  }
}
</script>
