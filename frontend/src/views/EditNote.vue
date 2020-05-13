<template>
  <div class="view">
    <div class="view-heading">
      <div class="action-container">
        <router-link to="/" class="button icon" style="margin-right: 1rem;">
          <ArrowLeftCircleIcon size="32" />
        </router-link>
      </div>

      <h3>Edit note</h3>
    </div>

    <div class="view-content">
      <EditNote :id="$route.params.id" />
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import EditNote from '@/components/EditNote.vue'

import { ArrowLeftCircleIcon } from 'vue-feather-icons'

@Component({
  components: {
    EditNote, ArrowLeftCircleIcon
  }
})
export default class App extends Vue {

}
</script>
