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
        path: 'course',
        component: () => import('@/views/course/course')
      }
    ]
  },
  {
    path: 'user',
    component: () => import('@/views/user'),
    children: [
      {
        path: 'userExt',
        component: () => import('@/views/user/userExt')
      }
    ]
  },
  //首页管理
  {
    path: 'index',
    component: () => import('@/views/index'),
    children: [
       {
        path: 'course',
        component: () => import('@/views/index/course'),
        children: [
          {
            path: 'adv',
            component: () => import('@/views/index/course/adv')
          },
          {
            path: 'zone',
            component: () => import('@/views/index/course/zone')
          },
          {
            path: 'courseCategory',
            component: () => import('@/views/index/course/courseCategory'),
            children: [
              {
                path: 'recommendCourse',
                name: 'recommendCourse',
                component: () => import('@/views/index/course/courseCategory/recommendCourse')
              }
              ]
          }
        ]
      },
      {
        path: 'system',
        component: () => import('@/views/index/system'),
        children: [
          {
            path: 'navBar',
            component: () => import('@/views/index/system/navBar')
          },
          {
            path: 'websiteLink',
            component: () => import('@/views/index/system/websiteLink')
          },
          {
            path: 'websiteNav',
            component: () => import('@/views/index/system/websiteNav')
          }
        ]
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
