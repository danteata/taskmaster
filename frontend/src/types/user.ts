import { UUID } from 'crypto'

export type User = {
  id: UUID
  firstName: string
  surName: string
  email: string
  password: string
  avatarUrl: string
}
