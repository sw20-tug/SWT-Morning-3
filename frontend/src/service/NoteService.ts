
import axios from 'axios'
import { NoteData } from '@/model'

const API_URL = 'http://localhost:8080'

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
}
