import { Button } from '@/components/ui/button'
import { Card, CardContent, CardHeader, CardTitle } from '@/components/ui/card'
import { ScrollArea } from '@/components/ui/scroll-area'
import { MagnifyingGlassIcon, MixerHorizontalIcon } from '@radix-ui/react-icons'
import AddTask from '@/components/tasks/AddTask'
import { KanbanBoard } from '@/components/tasks/KanbanBoard'
import { useState } from 'react'
import { useFetchTasks } from '@/hooks/useFetchTasks'
import TasksFilter from '@/components/tasks/TasksFilter'
import { Input } from '@/components/ui/input'
import TaskItem from '@/components/tasks/TaskItem'
import { priorities, labels, departments } from '@/api'

const Tasks = () => {
  const [search, setSearch] = useState<string>('')

  const { tasks, isLoading } = useFetchTasks(search)

  //TODO get them from server

  const handleSearchChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    console.log('search change event e.target.value', e.target.value)
    setSearch(e.target.value)
  }

  const handleFilterChange = (section: string, value: string) => {
    console.log('category change value ', { section, value })
  }

  // if (isLoading) return <div>Loading...</div>

  return (
    <>
      <div className="relative px-5 lg:px-0 lg:flex gap-5 justify-center py-5">
        <section className="filter-section ">
          <Card className="p-5 sticky top-10">
            <div className="flex justify-between lg:w-[20rem]">
              <p className="text-xl -tracking-wider">filters</p>
              <Button variant="ghost" size="icon">
                <MixerHorizontalIcon />
              </Button>
            </div>
            <CardContent className="mt-5">
              <ScrollArea className="space-y-7 h-[70vh]">
                <div className="pt-9">
                  <h1 className="pb-3 text-gray-400 border-b">Priority</h1>
                  <div className="pt-5">
                    {priorities && (
                      <TasksFilter
                        options={priorities}
                        onValueChange={handleFilterChange}
                      />
                    )}
                  </div>
                </div>
                <div className="pt-9">
                  <h1 className="pb-3 text-gray-400 border-b">Department</h1>
                  <div className="pt-5">
                    {departments && (
                      <TasksFilter
                        options={departments}
                        onValueChange={handleFilterChange}
                      />
                    )}
                  </div>
                </div>
                <div className="pt-9">
                  <h1 className="pb-3 text-gray-400 border-b">Label</h1>
                  <div className="pt-5">
                    {labels && (
                      <TasksFilter
                        options={labels}
                        onValueChange={handleFilterChange}
                      />
                    )}
                  </div>
                </div>
              </ScrollArea>
            </CardContent>
          </Card>
        </section>
        <section className="tasks-section w-full lg:w-[48rem]">
          <div className="flex gap-2 items-center pb-5 justify-between">
            <div className="relative p-0 w-full">
              <Input
                onChange={handleSearchChange}
                placeholder="Search..."
                className="40% px-9"
              />
              <MagnifyingGlassIcon className="absolute top-3 left-4" />
            </div>
          </div>
          <div className="space-y-5 min-h-[74vh]">
            {search
              ? [1, 1, 1].map((item) => <TaskItem key={item} />)
              : [1, 1, 1, 1].map((item) => <TaskItem key={item} />)}
          </div>
          <Card className="p-5 sticky top-10">
            <CardHeader>
              <CardTitle>Tasks</CardTitle>
              <div className="flex justify-between">
                <div />
                <div className="flex-nowrap">
                  <AddTask />
                </div>
              </div>
              <div className="flex justify-between">
                <div />
                {/* {tasks && <TasksList tasks={tasks} />}
                <h1 className="scroll-m-20 text-4xl font-extrabold tracking-tight lg:text-5xl">
                  Tasks board
                </h1> */}
                <KanbanBoard />
              </div>
            </CardHeader>
          </Card>
        </section>
      </div>
    </>
  )
}
export default Tasks
