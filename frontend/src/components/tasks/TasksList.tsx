import { Task } from '@/types/task'
const TasksList = ({ tasks }: { tasks: Task[] }) => {
  return (
    <div>
      {tasks.map((task) => (
        <div key={task.id}>
          <p>{task.title}</p>
        </div>
      ))}
    </div>
  )
}
export default TasksList
