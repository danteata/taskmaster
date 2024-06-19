import { useQuery } from '@tanstack/react-query'
import { fetchTasks } from '@/api'
export const useFetchTasks = (search?: string) => {
  const {
    data: tasks,
    error,
    isLoading,
    refetch: refreshTasks,
  } = useQuery({
    queryKey: ['tasks', { search }],
    queryFn: () => fetchTasks(search),
  })

  return { tasks, error, isLoading, refreshTasks }
}
