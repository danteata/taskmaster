import { Card } from '@/components/ui/card'
import { DotFilledIcon } from '@radix-ui/react-icons'
import TaskCardActions from './TaskCardActions'
import TaskLabels from './TaskLabels'
const TaskItem = () => {
  return (
    <Card className="p-5 w-full lg:max-w-3xl">
      <div className="space-y-5">
        <div className="space-y-2">
          <div className="flex justify-between">
            <div className="flex items-center gap-5">
              <h1 className="cursor-pointer font-bold text-lg">
                Fix critical bug
              </h1>
              <DotFilledIcon />
              <p className="text-sm text-gray-400">bug</p>
            </div>
            <div>
              <TaskCardActions />
            </div>
          </div>
          <p className="text-gray-500 text-sm">
            Lorem ipsum dolor, sit amet consectetur adipisicing elit. Debitis
          </p>
        </div>
        <div className="flex flex-wrap gap-2 items-center">
          <TaskLabels labels={['critical', 'bug']} />
        </div>
      </div>
    </Card>
  )
}

export default TaskItem
