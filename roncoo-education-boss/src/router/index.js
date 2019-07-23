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
        component: () => import('@/views/order/order'),
        children: [
          {
            path: 'orderInfo',
            component: () => import('@/views/order/order/orderInfo')
          }
         ]
      }
    ]
  },
  //首页管理
  {
    path: 'homepage',
    component: () => import('@/views/homepage'),
    children: [
      {
        path: 'adv',
        component: () => import('@/views/homepage/adv'),
        children: [
          {
            path: 'pc',
            component: () => import('@/views/homepage/adv/pc')
          }
        ]
      },
      {
        path: 'zone',
        component: () => import('@/views/homepage/zone'),
        children: [
          {
            path: 'pc',
            component: () => import('@/views/homepage/zone/pc')
          },
          {
            path: 'course',
            component: () => import('@/views/homepage/zone/course')
          }
         ]
      },
      {
        path: 'website',
        component: () => import('@/views/homepage/website'),
        children: [
          {
            path: 'navBar',
            component: () => import('@/views/homepage/website/navBar')
          },
          {
            path: 'websiteLink',
            component: () => import('@/views/homepage/website/websiteLink')
          },
          {
            path: 'websiteNav',
            component: () => import('@/views/homepage/website/websiteNav')
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
        path: 'lecturer',
        component: () => import('@/views/lecturer/lecturer'),
        children: [
          {// 讲师列表
            path: 'lecturer',
            component: () => import('@/views/lecturer/lecturer/lecturer')
          },
          {
            path: 'audit',
            component: () => import('@/views/lecturer/lecturer/audit')
          }
        ]
      },
      {
        path: 'profit',
        component: () => import('@/views/lecturer/profit'),
        children: [
          {
            path: 'profit',
            component: () => import('@/views/lecturer/profit/profit')
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
        path: 'user',
        component: () => import('@/views/user/user'),
        children: [
        // 学员列表
          {
            path: 'ext',
            component: () => import('@/views/user/user/ext')
          },
          {
            path: 'studyLog',
            component: () => import('@/views/user/user/studyLog')
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
        // 权限管理
        path: 'pms',
        component: () => import('@/views/sys/pms'),
        children: [
          {
            // 用户管理
            path: 'user',
            component: () => import('@/views/sys/pms/user')
          },
          {
            // 角色管理
            path: 'role',
            component: () => import('@/views/sys/pms/role')
          },
          {
            // 菜单管理
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
            // 消息管理
            path: 'template',
            component: () => import('@/views/sys/msg/template')
          },
          {
            // 用户消息
            path: 'msgUser',
            component: () => import('@/views/sys/msg/msgUser')
          },
          {
            // 站点消息
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
            // 站点设置
            path: 'website',
            component: () => import('@/views/sys/website/website')
          },
          {
            // 系统设置
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
            // 应用管理
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
            // 系统操作日志
            path: 'sys',
            component: () => import('@/views/sys/log/sys')
          },
          {
            // 支付日志
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
