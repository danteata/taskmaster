import { Outlet } from 'react-router-dom'
import { QueryClient, QueryClientProvider } from '@tanstack/react-query'

import { ThemeProvider } from '@/components/theme-provider'
import { ModeToggle } from '@/components/mode-toggle'
import '@/App.css'

const queryClient = new QueryClient()

function AuthLayout() {
  return (
    <QueryClientProvider client={queryClient}>
      <ThemeProvider defaultTheme="dark" storageKey="vite-ui-theme">
        <div className="min-h-screen flex flex-col">
          <header className="flex justify-between w-full flex-row p-4">
            <ModeToggle />
          </header>
          <main className="mx-4 flex flex-col gap-6">
            <div className="authContainer flex justify-center items-center min-h-[100vh]">
              <div className="box h-[30rem] w-[25rem]">
                <div className="mainContainer auth">
                  <div className="authBox w-full px-10 space-y-5"></div>
                </div>
                <Outlet />
              </div>
            </div>
          </main>
        </div>
      </ThemeProvider>
    </QueryClientProvider>
  )
}

export default AuthLayout
