import { useParams } from 'react-router-dom'
import { ScrollArea } from '@/components/ui/scroll-area'
import InviteUser from '@/components/InviteUser'
import TaskComments from '@/components/tasks/TaskComments'

const Task = () => {
  const { taskId } = useParams<{ taskId: string }>()
  return (
    <>
      <div className="mt5 lg:px-10">
        <div className="lg:flex gap-5 justify-between pb-4">
          <ScrollArea className="h-screen lg:w-[69%] pr-2">
            <div className="text-gray-400 pb-10 w-full">
              <h1 className="text-lg font-semibold pb-5">Fix critical bug</h1>
            </div>
            <div className="space-y-5 pb-10">
              <p className="w-full md:max-w-lg lg:max-xl">
                Lorem, ipsum dolor sit amet consectetur adipisicing elit. Aut,
                amet. Cupiditate nemo autem eum laudantium dolore repudiandae
                exercitationem, magni fuga culpa alias, neque ipsa? Quidem
                dolores vitae necessitatibus blanditiis eum.
              </p>
              <div className="flex">
                <p className="w-36">Assignee</p>
                <p>Daniel</p>
                <InviteUser />
              </div>
              <div className="flex">
                <p className="w-36">Department</p>
                <p>Sales</p>
              </div>
            </div>
          </ScrollArea>
          <div className="lg:w-[30%] rounded-md sticky right-5 top-10">
            <TaskComments />
          </div>
        </div>
      </div>
    </>
  )
}
export default Task
