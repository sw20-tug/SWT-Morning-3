import Vue from 'vue'
import Vuex from 'vuex'

import { NoteData } from '../model'

import { NoteService } from '../service/NoteService'

const service = new NoteService()

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    notes: Array<NoteData>()
  },
  getters: {
    notes: state => state.notes.sort((a, b) => { if (a.pinned && !b.pinned) return -1; if (!a.pinned && b.pinned) return 1; else return 0 })
  },
  mutations: {
    addNote: (state, payload: NoteData) => {
      state.notes = [...state.notes, payload]
    },

    setNotes: (state, payload: Array<NoteData>) => {
      state.notes = payload
    }
  },
  actions: {
    sync: ({ commit }) => {
      service.getNotes()
        .then(notes => commit('setNotes', notes))
    }
  },
  modules: {
  }
})
