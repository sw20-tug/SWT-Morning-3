
import axios from 'axios'

import { NoteData } from '@/model'

const API_URL = 'http://localhost:8090'

type ServerResponseNote = {
  id: number;
  title: string;
  description: string;
  timestamp: number;
}

export class NoteService {
  async getNotes () {
    const url = `${API_URL}/notes`
    const response = await axios.get(url)

    const notes: Array<NoteData> =
      response.data.map((note: ServerResponseNote) =>
        new NoteData(
          note.id,
          note.title,
          note.description,
          new Date(note.timestamp)))

    return notes
  }

  async getNoteById (id: string) {
    const url = `${API_URL}/notes/${id}`
    const response = await axios.get(url)
    const note: NoteData = response.data
    return note
  }

  async addNote (title: string, description: string) {
    const url = `${API_URL}/notes`

    await axios.post(url, {
      title: title,
      description: description
    })
  }

  async editNote (id: string, title: string, description: string) {
    const url = `${API_URL}/notes/${id}`

    await axios.put(url, {
      title: title,
      description: description
    })
  }
}
