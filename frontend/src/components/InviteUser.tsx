import { PlusIcon } from 'lucide-react'
import { Button } from './ui/button'
import {
  Dialog,
  DialogClose,
  DialogContent,
  DialogHeader,
  DialogTrigger,
} from './ui/dialog'
import InviteUserForm from './InviteUserForm'

const InviteUser = () => {
  return (
    <Dialog>
      <DialogTrigger>
        <DialogClose>
          <Button size="sm" variant="outline" className="ml-2">
            <span>Invite user</span>
            <PlusIcon className="w-3 h-3" />
          </Button>
        </DialogClose>
      </DialogTrigger>
      <DialogContent>
        <DialogHeader>Invite member</DialogHeader>
        <InviteUserForm />
      </DialogContent>
    </Dialog>
  )
}
export default InviteUser
