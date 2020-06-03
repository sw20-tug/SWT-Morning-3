
export class NoteData {
  readonly id: string
  readonly title: string
  readonly description: string
  readonly timestamp: number
  readonly pinned: boolean
  readonly tags: string[]
  readonly completed: boolean
  readonly dateCompleted: number

  constructor (id: string, title: string, description: string, timestamp: number, pinned: boolean, tags: string[], completed: boolean, dateCompleted: number) {
    this.id = id
    this.title = title
    this.description = description
    this.timestamp = timestamp
    this.pinned = pinned
    this.tags = tags
    this.completed = completed
    this.dateCompleted = dateCompleted
  }
}
