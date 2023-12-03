import { createBrowserRouter } from 'react-router-dom'
import LoginPage from '../pages/Login'
import NotFound from '../pages/NotFound'
import { Navigate } from 'react-router-dom' // 导入 Navigate 组件

const router = createBrowserRouter([
  {
    path: '/',
    element: <Navigate to="/login" replace /> // 使用 Navigate 组件进行重定向
  },
  {
    path: '/login',
    element: <LoginPage />
  },
  {
    path: '*',
    element: <NotFound />
  }
])

export default router
