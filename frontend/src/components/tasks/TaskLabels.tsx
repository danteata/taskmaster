import { Badge } from '../ui/badge'

const TaskLabels = ({ labels }: { labels: string[] }) => {
  return labels.map((label) => (
    <Badge key={label} className="mr-2" variant="outline">
      {label}
    </Badge>
  ))
}
export default TaskLabels
