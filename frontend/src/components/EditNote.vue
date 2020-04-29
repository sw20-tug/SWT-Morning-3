<template>
  <div class="box">
    <div class="box-header">
      <div class="box-title">
        <input type="text" v-model="title" placeholder="Title" style="width: 100%">
      </div>

      <div class="box-actions">
        <a @click="editNote" href="#" class="button icon small"><SaveIcon size="24" /></a>
      </div>
    </div>

    <div class="box-body">
      <textarea rows="4" v-model="description" placeholder="Give a few details"></textarea>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Prop } from 'vue-property-decorator'

import { SaveIcon } from 'vue-feather-icons'

import { NoteService } from '../service/NoteService'
import { NoteData } from '@/model'

const service = new NoteService()

@Component({
  components: { SaveIcon }
})
export default class Note extends Vue {
  @Prop({ default: 0 }) readonly id!: string
  private title = ''
  private description = ''

  async mounted () {
    const note: NoteData = await service.getNoteById(this.id)
    this.title = note.title
    this.description = note.description
  }

  editNote () {
    service.editNote(this.id, this.title, this.description)
      .then(() => this.$router.go(-1))
  }
}
</script>
