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
    notes: state => state.notes
  },
  mutations: {
    addNote: (state, payload: NoteData) => {
      state.notes = [...state.notes, payload]
    }
  },
  actions: {
    sync: ({ commit, state }) => {
      service.getNotes()
        .then(notes =>
          notes.forEach(n => commit('addNote', n)))
    }
  },
  modules: {
  }
})
