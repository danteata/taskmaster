import { Active, DataRef, Over } from '@dnd-kit/core'
import { ColumnDragData } from '@/components/tasks/BoardColumn'
import { TaskDragData } from '@/components/tasks/TaskCard'

type DraggableData = ColumnDragData | TaskDragData

export function hasDraggableData<T extends Active | Over>(
  entry: T | null | undefined
): entry is T & {
  data: DataRef<DraggableData>
} {
  if (!entry) {
    return false
  }

  const data = entry.data.current

  if (data?.type === 'Column' || data?.type === 'Task') {
    return true
  }

  return false
}
