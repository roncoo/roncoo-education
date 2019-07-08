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
  //订单管理
  {
    path: 'order',
    component: () => import('@/views/order'),
    children: [
      {
        path: 'order',
        component: () => import('@/views/order')
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
  },
  //课程管理
  {
    path: 'course',
    component: () => import('@/views/course'),
    children: [
      {
        path: 'audit',
        component: () => import('@/views/course/audit')
      },
      {
        path: 'course',
        component: () => import('@/views/course/course')
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
  },
  // 系统管理
  {
    path: 'sys',
    component: () => import('@/views/sys'),
    children: [
      // 用户列表
      {
        path: 'user',
        component: () => import('@/views/sys/user')
      },
      // 角色列表
      {
        path: 'role',
        component: () => import('@/views/sys/role')
      },
      // 菜单列表
      {
        path: 'menu',
        component: () => import('@/views/sys/menu')
      },
      // 站内消息
      {
        path: 'msg',
        component: () => import('@/views/sys/msg')
      },
      // 站点设置
      {
        path: 'website',
        component: () => import('@/views/sys/website')
      },
      // 系统设置
      {
        path: 'sys',
        component: () => import('@/views/sys/sys')
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
