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
        path: 'orderInfo',
        component: () => import('@/views/order/orderInfo'),
        meta: { requireAuth: true }
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
            component: () => import('@/views/homepage/adv/pc'),
            meta: { requireAuth: true }
          }
        ]
      },
      {
        path: 'zone',
        component: () => import('@/views/homepage/zone'),
        children: [
          {
            path: 'pc',
            component: () => import('@/views/homepage/zone/pc'),
            meta: { requireAuth: true }
          },
          {
            path: 'course',
            meta: { title: '专区课程' },
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
            component: () => import('@/views/homepage/website/navBar'),
            meta: { requireAuth: true }
          },
          {
            path: 'websiteLink',
            component: () => import('@/views/homepage/website/websiteLink'),
            meta: { requireAuth: true }
          },
          {
            path: 'websiteNav',
            component: () => import('@/views/homepage/website/websiteNav'),
            meta: { requireAuth: true }
          },
          {
            path: 'websiteNavArticle',
            component: () => import('@/views/homepage/website/websiteNavArticle'),
            meta: { requireAuth: true }
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
          {// 课程列表
            path: 'course',
            component: () => import('@/views/course/course/course'),
            meta: { requireAuth: true }
          },
          {
            // 课程审核列表
            path: 'audit',
            component: () => import('@/views/course/course/audit'),
            meta: { requireAuth: true }
          }
        ]
      },
      {
        path: 'category',
        component: () => import('@/views/course/category'),
        children: [
          {
            // 课程分类列表
            path: 'category',
            component: () => import('@/views/course/category/category'),
            meta: { requireAuth: true }
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
            component: () => import('@/views/lecturer/lecturer/lecturer'),
            meta: { requireAuth: true }
          },
          {
            // 讲师审核列表
            path: 'audit',
            component: () => import('@/views/lecturer/lecturer/audit'),
            meta: { requireAuth: true }
          }
        ]
      },
      {
        path: 'profit',
        component: () => import('@/views/lecturer/profit'),
        children: [
          {
            // 讲师分润列表
            path: 'profit',
            component: () => import('@/views/lecturer/profit/profit'),
            meta: { requireAuth: true }
          }
        ]
      }
    ]
  },
  // 学员管理
  {
    path: 'user',
    component: () => import('@/views/user'),
    children: [
      // 学员列表
      {
        path: 'ext',
        component: () => import('@/views/user/ext'),
        meta: { requireAuth: true }
      },
      {
        // 用户学习日志列表
        path: 'studyLog',
        meta: { title: '学习日志' },
        component: () => import('@/views/user/studyLog')
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
            component: () => import('@/views/sys/pms/user'),
            meta: { requireAuth: true }
          },
          {
            // 角色管理
            path: 'role',
            component: () => import('@/views/sys/pms/role'),
            meta: { requireAuth: true }
          },
          {
            // 菜单管理
            path: 'menu',
            component: () => import('@/views/sys/pms/menu'),
            meta: { requireAuth: true }
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
            component: () => import('@/views/sys/msg/template'),
            meta: { requireAuth: true }
          },
          {
            // 用户消息
            path: 'msgUser',
            component: () => import('@/views/sys/msg/msgUser'),
            meta: { requireAuth: true }
          },
          {
            // 站点消息
            path: 'msg',
            component: () => import('@/views/sys/msg/msg'),
            meta: { requireAuth: true }
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
            component: () => import('@/views/sys/website/website'),
            meta: { requireAuth: true }
          },
          {
            // 系统设置
            path: 'sys',
            component: () => import('@/views/sys/website/sys'),
            meta: { requireAuth: true }
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
            component: () => import('@/views/sys/platform/platform'),
            meta: { requireAuth: true }
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
            component: () => import('@/views/sys/log/sys'),
            meta: { requireAuth: true }
          },
          {
            // 支付日志
            path: 'orderPay',
            component: () => import('@/views/sys/log/orderPay'),
            meta: { requireAuth: true }
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
export default new Router({
  // mode: 'history', //后端支持可开
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap
})
