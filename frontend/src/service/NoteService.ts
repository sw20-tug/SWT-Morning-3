
import axios from 'axios'
import moment from 'moment'

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

  async addNote (title: string, description: string) {
    const url = `${API_URL}/notes`

    await axios.post(url, {
      title: title,
      description: description
    })
  }
}
