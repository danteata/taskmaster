import SignupForm from '@/components/Auth/SignupForm'
import { Button } from '@/components/ui/button'

const Signup = () => {
  return (
    <>
      <div className="space-y-5">
        <SignupForm />
      </div>
      <div>
        <span>already have an account?</span>
        <Button variant="ghost">Signup</Button>
      </div>
    </>
  )
}
export default Signup
