<template>
  <div class="box">
    <div class="box-header">
      <p class="box-title">
        {{ title }}<br>
        <small class="box-date">{{ date }}</small>
      </p>

      <div class="box-actions">
        <a @click="togglePinNote" href="#" class="button icon small">
          <a v-if="pinned == null || pinned == false" name="pin">
            <AnchorIcon size="24" />
          </a>
          <a v-if="pinned == true" style="color: green" name="unpin">
            <AnchorIcon size="24" />
          </a>
        </a>

        <router-link :to="`/edit/${id}`" class="button icon small" name="edit">
          <EditIcon size="24" />
        </router-link>

        <a @click="deleteNote" href="#" class="button icon small" name="delete">
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

import { EditIcon, Trash2Icon, AnchorIcon } from 'vue-feather-icons'

import { NoteService } from '@/service/NoteService'

import moment from 'moment'

const service = new NoteService()

@Component({
  components: { EditIcon, Trash2Icon, AnchorIcon }
})
export default class Note extends Vue {
  @Prop({ default: '' }) readonly id!: string
  @Prop({ default: '' }) readonly title!: string
  @Prop({ default: '' }) readonly description!: string
  @Prop({ default: 0 }) readonly timestamp!: number
  @Prop({ default: false }) readonly pinned!: boolean
  @Prop({ default: [] }) readonly tags!: string[]

  get date () {
    return moment.unix(Math.floor(this.timestamp / 1000)).format('DD.MM.YYYY, hh:mm:ss')
  }

  deleteNote () {
    service.deleteNote(this.id)
      .then(() => this.$store.dispatch('sync'))
  }

  togglePinNote () {
    service.editNote(this.id, this.title, this.description, this.timestamp, !this.pinned, this.tags)
      .then(() => this.$store.dispatch('sync'))
  }
}
</script>
