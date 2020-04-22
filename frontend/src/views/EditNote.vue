<template>
  <div class="note-container">
    <EditNote :id="$route.params.id"/>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import EditNote from '@/components/EditNote.vue'

@Component({
  components: {
    EditNote
  }
})
export default class App extends Vue {

}
</script>

<style lang="scss">
  .note-container {
    display: flex;
    flex-direction: column;
    max-width: 40rem;
  }
</style>
