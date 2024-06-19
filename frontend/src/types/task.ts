import { UUID } from 'crypto'

export type Task = {
  id: UUID
  title: string
  description: string
  status: string
  priority: string
  labels: string[]
}
