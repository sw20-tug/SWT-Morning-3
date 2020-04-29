
export class NoteData {
  readonly id: string
  readonly title: string
  readonly description: string
  readonly timestamp: number
  readonly pinned: boolean
  readonly tags: string[]

  constructor (id: string, title: string, description: string, timestamp: number, pinned: boolean, tags: string[]) {
    this.id = id
    this.title = title
    this.description = description
    this.timestamp = timestamp
    this.pinned = pinned
    this.tags = tags
  }
}
