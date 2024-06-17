import { Outlet } from 'react-router-dom'
import { QueryClient, QueryClientProvider } from '@tanstack/react-query'

import { ThemeProvider } from '@/components/theme-provider'
import { ModeToggle } from './components/mode-toggle'
import Footer from './components/Footer'
import './App.css'
import Navbar from './components/Navbar'

const queryClient = new QueryClient()

function App() {
  return (
    <QueryClientProvider client={queryClient}>
      <ThemeProvider defaultTheme="dark" storageKey="vite-ui-theme">
        <div className="min-h-screen flex flex-col">
          <header className="flex justify-between w-full flex-row p-4">
            <ModeToggle />
          </header>
          <main className="mx-4 flex flex-col gap-6">
            <Navbar />
            <Outlet />
            <Footer />
          </main>
        </div>
      </ThemeProvider>
    </QueryClientProvider>
  )
}

export default App
