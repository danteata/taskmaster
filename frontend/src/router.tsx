import { createBrowserRouter, Outlet } from 'react-router-dom'
import App from './App'
import Tasks from './pages/Tasks/Tasks'
import Task from './pages/Task'
import Signup from './pages/Auth/Signup'
import AuthLayout from './pages/Auth/AuthLayout'
import Login from './pages/Auth/Login'

export const router = createBrowserRouter([
  {
    path: '/',
    element: <App />,
    children: [
      {
        path: '/tasks',
        element: <Tasks />,
      },
      {
        path: '/tasks/:taskId',
        element: <Task />,
      },
    ],
  },
  {
    path: '/auth',
    element: <AuthLayout />,
    children: [
      {
        path: '/auth/signup',
        element: <Signup />,
      },
      {
        path: 'auth/login',
        element: <Login />,
      },
    ],
  },
])
