import { ScrollArea } from '../ui/scroll-area'

const TaskDetails = () => {
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
            </div>
          </ScrollArea>
        </div>
      </div>
    </>
  )
}
export default TaskDetails
