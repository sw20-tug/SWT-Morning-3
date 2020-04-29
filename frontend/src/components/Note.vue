<template>
  <div class="box">
    <div class="box-header">
      <p class="box-title">
        {{ title }}<br>
        <small class="box-date">{{ date }}</small>
      </p>

      <div class="box-actions">
        <router-link :to="`/edit/${id}`" class="button icon small">
          <EditIcon size="24" />
        </router-link>

        <a @click="deleteNote" href="#" class="button icon small">
          <Trash2Icon size="24" />
        </a>
      </div>
    </div>

    <div class="box-body">
      <p>{{ description }}</p>
    </div>

  </div>
</template>

<script lang="ts">
import { Component, Vue, Prop } from 'vue-property-decorator'

import { EditIcon, Trash2Icon } from 'vue-feather-icons'

import { NoteService } from '@/service/NoteService'

import moment from 'moment'

const service = new NoteService()

@Component({
  components: { EditIcon, Trash2Icon }
})
export default class Note extends Vue {
  @Prop({ default: '' }) readonly id!: string
  @Prop({ default: '' }) readonly title!: string
  @Prop({ default: '' }) readonly description!: string
  @Prop({ default: new Date(0) }) readonly timestamp!: Date

  get date () {
    return moment(this.timestamp).format('YYYY-MM-DD')
  }

  deleteNote () {
    service.deleteNote(this.id)
      .then(() => this.$store.dispatch('sync'))
  }
}
</script>
