
import axios from 'axios'

import { NoteData } from '@/model'

const API_URL = 'http://localhost:8090'

type ServerResponseNote = {
  id: string;
  title: string;
  description: string;
  dateCreated: number;
  pinned: boolean;
  tags: string[];
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
          note.dateCreated,
          note.pinned,
          note.tags))

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

    const response = await axios.post(url, {
      title: title,
      description: description,
      pinned: false,
      tags: []
    })

    return response
  }

  async editNote (id: string, title: string, description: string, timestamp: number, pinned: boolean, tags: string[]) {
    const url = `${API_URL}/notes/${id}`

    const response = await axios.put(url, {
      title: title,
      description: description,
      timestamp: timestamp,
      pinned: pinned,
      tags: tags
    })

    return response
  }

  async deleteNote (id: string) {
    const url = `${API_URL}/notes/${id}`

    await axios.delete(url)
  }
}
