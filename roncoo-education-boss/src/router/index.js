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
        path: 'zone',
        component: () => import('@/views/index/zone'),
        children: [
          {
            path: 'zone',
            component: () => import('@/views/index/zone/zone')
          },
          {
            path: 'course',
            component: () => import('@/views/index/zone/course')
          }
         ]
      },
      {
        path: 'shuf',
        component: () => import('@/views/index/shuf'),
        children: [
          {
            path: 'adv',
            component: () => import('@/views/index/shuf/adv')
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
        path: 'course',
        component: () => import('@/views/course/course'),
        children: [
          {// 讲师列表
            path: 'course',
            component: () => import('@/views/course/course/course')
          },
          {
            path: 'audit',
            component: () => import('@/views/course/course/audit')
          }
        ]
      },
      {
        path: 'category',
        component: () => import('@/views/course/category'),
        children: [
          {
            path: 'category',
            component: () => import('@/views/course/category/category')
          }
        ]
      }
    ]
  },
  // 讲师管理
  {
    path: 'lecturer',
    component: () => import('@/views/lecturer'),
    children: [
      {
        path: 'manage',
        component: () => import('@/views/lecturer/manage'),
        children: [
          {// 讲师列表
            path: 'lecturer',
            component: () => import('@/views/lecturer/manage/lecturer')
          },
          {
            path: 'audit',
            component: () => import('@/views/lecturer/manage/audit')
          }
        ]
      },
      {
        path: 'lecturerProfit',
        component: () => import('@/views/lecturer/lecturerProfit'),
        children: [
          {
            path: 'profit',
            component: () => import('@/views/lecturer/lecturerProfit/profit')
          }
        ]
      }
    ]
  },
  // 用户管理
  {
    path: 'user',
    component: () => import('@/views/user'),
    children: [
    // 学员管理
      {
        path: 'manage',
        component: () => import('@/views/user/manage'),
        children: [
        // 学员列表
          {
            path: 'userExt',
            component: () => import('@/views/user/manage/userExt')
          }
        ]
      }
    ]
  },
  //系统管理
  {
    path: 'sys',
    component: () => import('@/views/sys'),
    children: [
      {
        path: 'pms',
        component: () => import('@/views/sys/pms'),
        children: [
          {
            path: 'user',
            component: () => import('@/views/sys/pms/user')
          },
          {
            path: 'role',
            component: () => import('@/views/sys/pms/role')
          },
          {
            path: 'menu',
            component: () => import('@/views/sys/pms/menu')
          }
        ]
      },
      {// 消息管理
        path: 'msg',
        component: () => import('@/views/sys/msg'),
        children: [
          {
            path: 'template',
            component: () => import('@/views/sys/msg/template')
          },
          {
            path: 'msgUser',
            component: () => import('@/views/sys/msg/msgUser')
          },
          {
            path: 'msg',
            component: () => import('@/views/sys/msg/msg')
          }
         ]
      },
      {// 站点管理
        path: 'website',
        component: () => import('@/views/sys/website'),
        children: [
          {
            path: 'website',
            component: () => import('@/views/sys/website/website')
          },
          {
            path: 'sys',
            component: () => import('@/views/sys/website/sys')
          }
        ]
      },
      {// 平台管理
        path: 'platform',
        component: () => import('@/views/sys/platform'),
        children: [
          {
            path: 'platform',
            component: () => import('@/views/sys/platform/platform')
          }
        ]
      },
      {// 日志查看
        path: 'log',
        component: () => import('@/views/sys/log'),
        children: [
          {
            path: 'sys',
            component: () => import('@/views/sys/log/sys')
          },
          {
            path: 'orderPay',
            component: () => import('@/views/sys/log/orderPay')
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
