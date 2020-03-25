
export class NoteData {
  readonly id: number
  readonly title: string
  readonly description: string
  readonly timestamp: Date

  constructor (id: number, title: string, description: string, timestamp: Date) {
    this.id = id
    this.title = title
    this.description = description
    this.timestamp = timestamp
  }
}
