<template>
  <div class="box">
    <div class="box-header">
      <div class="box-title">
        <div class="actual-title">
          <label class="done">
            <input data-cy="checkbox-completed" type="checkbox" class="checkbox" checked="checked" v-model="completed" @change="toggleCompleteNote()">
            <span class="check">
            </span>
          </label>
          {{ title }}<br>
        </div>
        <small class="box-date"><strong>created:</strong> <span data-cy="note-date">{{ date }}</span></small><br>
        <small class="box-tags">
          <template v-if="tags.length > 0">
            <strong>tags:</strong> {{ tagText }} <br>
          </template>
        </small>
        <small v-if="completed" class="box-date" name="dateCompleted"><strong>done:</strong> <span data-cy="date-completed">{{ completedDate }}</span></small>
      </div>

      <div class="box-actions">
        <a @click="togglePinNote" href="#" class="button icon small" :class="{ inverted: pinned }">
          <a v-if="pinned == null || pinned == false" name="pin" title="Pin">
            <AnchorIcon size="24" />
          </a>
          <a v-if="pinned == true" name="unpin" title="Unpin">
            <AnchorIcon size="24" />
          </a>
        </a>

        <router-link :to="`/edit/${id}`" class="button icon small" name="edit" title="Edit">
          <EditIcon size="24" />
        </router-link>

        <a @click="deleteNote" href="#" class="button icon small" name="delete" title="Delete">
          <Trash2Icon size="24" />
        </a>

        <a @click="share" href="#" class="button icon small" name="share" title="Share">
          <Share2Icon size="24" />
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

import { EditIcon, Trash2Icon, AnchorIcon, Share2Icon } from 'vue-feather-icons'

import { NoteService } from '@/service/NoteService'

import moment from 'moment'

const service = new NoteService()

@Component({
  components: { EditIcon, Trash2Icon, AnchorIcon, Share2Icon }
})
export default class Note extends Vue {
  @Prop({ default: '' }) readonly id!: string
  @Prop({ default: '' }) readonly title!: string
  @Prop({ default: '' }) readonly description!: string
  @Prop({ default: 0 }) readonly timestamp!: number
  @Prop({ default: false }) readonly pinned!: boolean
  @Prop({ default: [] }) readonly tags!: string[]
  @Prop({ default: false }) readonly completed!: boolean
  @Prop({ default: 0 }) readonly dateCompleted!: number

  share () {
    const subject = this.title

    const created = `Created at ${moment(this.timestamp).format()}`

    const completed = this.completed
      ? `Completed at ${moment(this.dateCompleted).format()}`
      : 'Not completed.'

    const body = [
      this.description,
      created,
      completed
    ].join('\n\n')

    const link = encodeURI(`mailto:?to=&subject=${subject}&body=${body}`)

    window.open(link)
  }

  get tagText () {
    if (this.tags != null) {
      if (this.tags.length === 0) {
        return ''
      }

      return this.tags.join(', ')
    } else {
      return ''
    }
  }

  get date () {
    return moment.unix(Math.floor(this.timestamp / 1000)).format('DD.MM.YYYY, hh:mm:ss')
  }

  get completedDate () {
    return moment.unix(Math.floor(this.dateCompleted / 1000)).format('DD.MM.YYYY, hh:mm:ss')
  }

  deleteNote () {
    service.deleteNote(this.id)
      .then(() => this.$store.dispatch('sync'))
  }

  togglePinNote () {
    service.editNote(this.id, this.title, this.description, this.timestamp, !this.pinned, this.tags, this.completed, this.dateCompleted)
      .then(() => this.$store.dispatch('sync'))
  }

  toggleCompleteNote () {
    let dateCompleted = 0
    if (this.completed === true) {
      dateCompleted = new Date().getTime()
    }
    service.editNote(this.id, this.title, this.description, this.timestamp, this.pinned, this.tags, this.completed, dateCompleted)
      .then(() => this.$store.dispatch('sync'))
  }
}
</script>

<style scoped>
.actual-title {
  display: flex;
}

a.inverted {
  background-color: #5B507A;
  color: #fff !important;
}
</style>
