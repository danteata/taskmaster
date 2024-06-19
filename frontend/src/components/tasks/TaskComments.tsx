import { ScrollArea } from '@/components/ui/scroll-area'
import { Avatar, AvatarFallback } from '../ui/avatar'
import { Input } from '../ui/input'
import { Button } from '../ui/button'
import { PaperPlaneIcon } from '@radix-ui/react-icons'
import { useState } from 'react'
import { PaperclipIcon } from 'lucide-react'

const TaskComments = () => {
  const [comment, setComment] = useState<string>('')
  const handleCommentChange = (e) => {
    setComment(e.target.value)
  }

  const handleSendComment = () => {}

  return (
    <div className="sticky">
      <div className="border rounded-lg">
        <h1 className="border-b p-5">Comments</h1>
        <ScrollArea className="h-[32-rem] w-full p-5 flex gap-3 flex-col">
          {[...Array(4)].map((_, index) => (
            <>
              {index % 2 == 0 && (
                <div key={index} className="flex gap-2 mb-2 justify-start">
                  <Avatar>
                    <AvatarFallback>D</AvatarFallback>
                  </Avatar>
                  <div className="space-y-2 py-2 px-5 border rounded-ss-2xl rounded-e-xl">
                    <p>Emmanuel</p>
                    <p className="text-gray-300">How is it going?</p>
                  </div>
                </div>
              )}
              {index % 2 == 1 && (
                <div key={index} className="flex gap-2 mb-2 justify-end">
                  <div className="space-y-2 py-2 px-5 border rounded-se-2xl rounded-s-xl">
                    <p>Emmanuel</p>
                    <p className="text-gray-300">Wrapping up</p>
                  </div>
                  <Avatar>
                    <AvatarFallback>D</AvatarFallback>
                  </Avatar>
                </div>
              )}
            </>
          ))}
        </ScrollArea>
        <div className="relative p-0">
          <Input
            placeholder="type comment"
            className="py-7 border-t outline-none focus:outline-none focus:ring-0 rounded-none border-b-0 border-x-0"
            value={comment}
            onChange={handleCommentChange}
          />
          <Button
            onclick={handleSendComment}
            className="absolute right-12 top-3 rounded-full"
            size="icon"
            variant="ghost"
          >
            <PaperclipIcon className="w-4 h-4" />
          </Button>
          <Button
            type="submit"
            onclick={handleSendComment}
            className="absolute right-2 top-3 rounded-full"
            size="icon"
            variant="ghost"
          >
            <PaperPlaneIcon className="w-4 h-4" />
          </Button>
        </div>
      </div>
    </div>
  )
}
export default TaskComments
