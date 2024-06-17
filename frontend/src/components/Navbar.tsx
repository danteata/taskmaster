import UserAvatar from './UserAvatar'
import AddTask from './tasks/AddTask'

const Navbar = () => {
  return (
    <div className="border-b py-4 px-5 flex items-center justify-between">
      <div className="flex items-center gap-3">
        <p className="text-xl font-bold cursor-pointer">Task Master</p>
      </div>
      <AddTask />
      <UserAvatar user={undefined} />
    </div>
  )
}
export default Navbar
