<template>
  <div class="note">
    <div class="note-header">
      <p class="note-title">{{ title }}<span hidden>{{id}}</span></p>
      <p class="note-date">{{ date }}</p>
      <p class="note-pin"><a v-if="pinned == null || pinned == false" href="#" @click="pinNote(id, title, description, true, tags)">pin</a><a v-if="pinned == true" href="#" @click="pinNote(id, title, description, false, tags)">unpin</a></p>
    </div>
    <div class="note-body">
      <p>{{ description }}</p>
    </div>
    <div class="note-footer">
      <router-link :to="`/edit/${id}`">edit</router-link>&nbsp;
      <a href="#" @click="deleteNote(id)">delete</a>&nbsp;
      <router-link :to="`/share/${id}`">share</router-link>&nbsp;
      <router-link :to="`/export/${id}`">export</router-link>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Prop } from 'vue-property-decorator'

import moment from 'moment'
import { NoteService } from '@/service/NoteService'

const service = new NoteService()

@Component({})
export default class Note extends Vue {
  @Prop({ default: 0 }) readonly id!: string
  @Prop({ default: '' }) readonly title!: string
  @Prop({ default: '' }) readonly description!: string
  @Prop({ default: new Date(0) }) readonly timestamp!: Date
  @Prop({ default: false }) readonly pinned!: boolean
  @Prop({ default: [] }) readonly tags!: string[]

  get date () {
    console.log(this.timestamp)
    return moment(this.timestamp).format('YYYY-MM-DD')
  }

  deleteNote (id: string) {
    service.deleteNote(id)
      .then(() => this.$store.dispatch('sync'))
  }

  pinNote (id: string, title: string, description: string, pin: boolean, tags: string []) {
    service.editNote(id, title, description, pin, tags)
      .then(() => this.$store.dispatch('sync'))
  }
}
</script>

<style lang="scss">
.note {
  font: 16px/150% sans-serif;

  border: 1px solid #ccc;
  padding: 1rem;

  &:not(first-child) {
    margin-top: 0.5rem;
  }

  & > .note-header {
    display: flex;
    flex-direction: row;
    justify-content: space-between;

    & > p.note-title {
      font-weight: 700;
    }

    & > p.note-date {
      text-align: right;
    }

    & > p.note-pin {
      text-align: right;
    }
  }
  & > .note-footer {
    text-align: right;
  }
}

</style>
