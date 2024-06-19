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
import { Textarea } from '@/components/ui/textarea'
import { Input } from '@/components/ui/input'
import { Button } from '@/components/ui/button'
import { useState } from 'react'
import { addTask } from '@/api'
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from '../ui/select'
import { labels } from '@/api'
import { Cross1Icon } from '@radix-ui/react-icons'

export const taskSchema = z.object({
  title: z.string(),
  description: z.string().optional(),
  priority: z.string(),
  status: z.string(),
  dueDate: z.date(),
})

const TaskForm = () => {
  const queryClient = useQueryClient()

  const { mutateAsync } = useMutation({
    mutationFn: addTask,
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['tasks'] })
    },
  })

  const form = useForm<z.infer<typeof taskSchema>>({
    resolver: zodResolver(taskSchema),
    defaultValues: {
      title: '',
      description: '',
      priority: '',
      status: '',
      dueDate: new Date(),
    },
    mode: 'onChange',
  })

  const onSubmit = async (values: z.infer<typeof formSchema>) => {
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
          name="title"
          render={({ field }) => (
            <FormItem>
              {/* <FormLabel htmlFor="body">Title</FormLabel> */}
              <FormControl>
                <Input
                  id="title"
                  placeholder="Add a title."
                  className="border w-full border-gray-700 py-5 px-5"
                  // className="resize-none"
                  {...field}
                />
              </FormControl>
              <FormMessage />
            </FormItem>
          )}
          //   label="Title"
        />
        <FormField
          control={form.control}
          name="priority"
          render={({ field }) => (
            <FormItem>
              {/* <FormLabel htmlFor="body">Description</FormLabel> */}
              <FormControl>
                <Select
                  value={field.value}
                  defaultValue="Low"
                  onValueChange={field.onChange}
                  id="priority"
                  // className="resize-none"
                  // {...field}
                >
                  <SelectTrigger className="w-full">
                    <SelectValue placeholder="Priority" />
                  </SelectTrigger>
                  <SelectContent>
                    <SelectItem value="Low">Low</SelectItem>
                    <SelectItem value="Medium">Medium</SelectItem>
                    <SelectItem value="High">High</SelectItem>
                  </SelectContent>
                </Select>
              </FormControl>
              <FormMessage />
            </FormItem>
          )}
        />
        <FormField
          control={form.control}
          name="labels"
          render={({ field }) => (
            <FormItem>
              {/* <FormLabel htmlFor="body">Description</FormLabel> */}
              <FormControl>
                <Select
                  // value={field.value}
                  onValueChange={field.onChange}
                  id="labels"
                  // className="resize-none"
                  // {...field}
                >
                  <SelectTrigger className="w-full">
                    <SelectValue placeholder="Labels" />
                  </SelectTrigger>
                  <SelectContent>
                    {labels &&
                      labels.map((label) => (
                        <SelectItem key={label} value={label} />
                      ))}
                    {/* <SelectItem value="Low">Low</SelectItem>
                    <SelectItem value="Medium">Medium</SelectItem>
                    <SelectItem value="High">High</SelectItem> */}
                  </SelectContent>
                </Select>
              </FormControl>
              <div className="flex gap-1 flex-wrap">
                <div className="cursor-pointer flex rounded-full items-center border gap-2 py-1">
                  <span className="text-sm">bug</span>
                  <Cross1Icon className="h-3 w-3" />
                </div>
              </div>
              <FormMessage />
            </FormItem>
          )}
        />
        <FormField
          control={form.control}
          name="description"
          render={({ field }) => (
            <FormItem>
              {/* <FormLabel htmlFor="body">Description</FormLabel> */}
              <FormControl>
                <Textarea
                  id="description"
                  placeholder="Add more details."
                  // className="resize-none"
                  {...field}
                />
              </FormControl>
              <FormMessage />
            </FormItem>
          )}
        />

        <Button
          className="w-full my-5"
          type="submit"
          disabled={form.formState.isSubmitting || !form.formState.isValid}
        >
          Create Task
        </Button>
      </form>
    </Form>
  )
}
export default TaskForm
