import { AvatarIcon } from '@radix-ui/react-icons'
import { Avatar, AvatarFallback } from '../ui/avatar'

const UserList = () => {
  return (
    <>
      <div className="space-y-2">
        <div className="border rounded-md">
          <p className="py-2 px3"></p>
        </div>
        <div className="py-2 group hover:bg-slate-800 cursor-pointer flex items-center space-x-4 rounded-md border px-4">
          <Avatar>
            <AvatarFallback>
              <AvatarIcon />
            </AvatarFallback>
          </Avatar>
          <div className="space-y-1">
            <p className="leading-none text-sm">Daniel</p>
            <p className="text-muted-foreground text-sm">@dantheta</p>
          </div>
        </div>
      </div>
    </>
  )
}
export default UserList
