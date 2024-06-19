import axios, { AxiosRequestConfig } from 'axios'
import { Task } from '@/types/task'

export const api = axios.create({
  baseURL: 'https://jsonplaceholder.typicode.com/',
})

export const fetcher = async (url: string, options: AxiosRequestConfig) => {
  const res = await api.request({
    url,
    ...options,
  })
  return res.data
}

// export const addTask = async (data: any) =>
export const addTask = async (task: Pick<Task, 'title'>): Promise<Task> =>
  await fetcher('/tasks', { method: 'POST', data: task })

export const fetchTasks = async (query = ''): Promise<Task[]> =>
  await fetcher('/tasks', { method: 'GET', params: { q: query } })

export const departments = [
  { value: 'all', label: 'All' },
  { value: 'design', label: 'Design' },
  { value: 'development', label: 'Development' },
  { value: 'marketing', label: 'Marketing' },
  { value: 'sales', label: 'Sales' },
]

export const priorities = [
  { value: 'all', label: 'All' },
  { value: 'high', label: 'High' },
  { value: 'medium', label: 'Medium' },
  { value: 'low', label: 'Low' },
]
// const statuses = ['all', 'todo', 'doing', 'done']
export const labels = [
  { value: 'all', label: 'All' },
  { value: 'bug', label: 'Bug' },
  { value: 'design', label: 'Design' },
  { value: 'feature', label: 'Feature' },
]
