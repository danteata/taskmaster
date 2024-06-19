import { DotFilledIcon, DotsVerticalIcon } from '@radix-ui/react-icons'
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuTrigger,
} from '../ui/dropdown-menu'
import { Button } from '../ui/button'
const TaskCardActions = () => {
  return (
    <DropdownMenu>
      <DropdownMenuTrigger>
        <Button className="rounded-full" variant="ghost" size="icon">
          <DotsVerticalIcon />
        </Button>
      </DropdownMenuTrigger>
      <DropdownMenuContent>
        <DropdownMenuItem>In Progress</DropdownMenuItem>
        <DropdownMenuItem>Done</DropdownMenuItem>
        <DropdownMenuItem>Edit</DropdownMenuItem>
        <DropdownMenuItem>Delete</DropdownMenuItem>
      </DropdownMenuContent>
    </DropdownMenu>
  )
}
export default TaskCardActions
