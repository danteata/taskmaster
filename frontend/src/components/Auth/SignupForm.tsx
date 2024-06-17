import { useQueryClient, useMutation } from '@tanstack/react-query'
import { z } from 'zod'
import { useForm } from 'react-hook-form'
import { zodResolver } from '@hookform/resolvers/zod'
import {
  Form,
  FormControl,
  FormField,
  FormItem,
  FormMessage,
} from '@/components/ui/form'
import { Input } from '@/components/ui/input'
import { Button } from '@/components/ui/button'
import { useState } from 'react'
import { addTask } from '@/api'
import { Cross1Icon } from '@radix-ui/react-icons'

export const userRegistrationSchema = z.object({
  email: z.string(),
  password: z.string(),
  firstName: z.string(),
  lastName: z.string(),
})

const SignupForm = () => {
  const queryClient = useQueryClient()

  const { mutateAsync } = useMutation({
    mutationFn: addTask,
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['tasks'] })
    },
  })

  const form = useForm<z.infer<typeof userRegistrationSchema>>({
    resolver: zodResolver(userRegistrationSchema),
    defaultValues: {
      email: '',
      password: '',
      firstName: '',
      lastName: '',
    },
    mode: 'onChange',
  })

  const onSubmit = async (values: z.infer<typeof inviteUserSchema>) => {
    console.log(values)
    try {
      await mutateAsync(values)
    } catch (error) {}
    // setIsDialogOpen(false)
  }

  return (
    <Form {...form}>
      <form onSubmit={form.handleSubmit(onSubmit)} className="space-y-4">
        <FormField
          control={form.control}
          name="email"
          render={({ field }) => (
            <FormItem>
              <FormControl>
                <Input
                  id="email"
                  placeholder="Enter email"
                  className="border w-full border-gray-700 py-5 px-5"
                  {...field}
                />
              </FormControl>
              <FormMessage />
            </FormItem>
          )}
          //   label="Email"
        />

        <Button
          className="w-full my-5"
          type="submit"
          disabled={form.formState.isSubmitting || !form.formState.isValid}
        >
          Signup
        </Button>
      </form>
    </Form>
  )
}
export default SignupForm
