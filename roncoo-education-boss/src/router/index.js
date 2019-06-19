import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

import Layout from '@/views/layout/Layout'

export const asyncRouterMap = [
  {
    path: 'dashboard',
    component: () => import('@/views/dashboard/index'),
    // 当菜单文件中没有该页面配置时，在标签栏显示的就是这里配置的标题
    meta: { title: '首页' }
  },
  {
    path: 'course',
    component: () => import('@/views/course'),
    children: [
      {
        path: 'audit',
        component: () => import('@/views/course/audit')
      }
    ]
  },
  // 讲师管理
  {
    path: 'lecturer',
    component: () => import('@/views/lecturer'),
    children: [
    // 讲师列表
      {
        path: 'lecturer',
        component: () => import('@/views/lecturer/lecturer')
      },
      // 讲师审核列表
      {
        path: 'audit',
        component: () => import('@/views/lecturer/audit')
      },
      // 分润列表
      {
        path: 'profit',
        component: () => import('@/views/lecturer/profit')
      }
    ]
  },
  // 用户管理
  {
    path: 'user',
    component: () => import('@/views/user'),
    children: [
    // 学员列表
      {
        path: 'userExt',
        component: () => import('@/views/user/userExt')
      }
    ]
  }
]
export const constantRouterMap = [
  { path: '/login', component: () => import('@/views/login/index') },
  { path: '*', component: () => import('@/views/404') },
  {
    path: '/',
    component: Layout,
    redirect: 'dashboard', // 设置登陆系统默认页面
    children: [
      { path: 'iframe', component: () => import('@/views/iframe/index') },
      { path: 'redirect/:path*', component: () => import('@/views/redirect/index') },
      ...asyncRouterMap
    ]
  }
]
console.log(constantRouterMap)

export default new Router({
  // mode: 'history', //后端支持可开
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap
})
