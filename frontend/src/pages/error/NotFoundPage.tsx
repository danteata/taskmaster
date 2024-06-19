import { Link } from 'react-router-dom'

const NotFoundPage = () => {
  return (
    <>
      <div className="flex flex-col gap-2">
        Page not found
        <Link to="/">Go Home</Link>
      </div>
    </>
  )
}
export default NotFoundPage
