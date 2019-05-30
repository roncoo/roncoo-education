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
    path: 'pms',
    component: () => import('@/views/pms'),
    children: [
      {
        path: 'menu',
        component: () => import('@/views/pms/menu')
      },
      {
        path: 'pms',
        component: () => import('@/views/pms/pms')
      },
      {
        path: 'role',
        component: () => import('@/views/pms/role')
      },
      {
        path: 'operator',
        component: () => import('@/views/pms/operator')
      }

    ]
  },
  {
    path: 'trade',
    component: () => import('@/views/trade'),
    children: [
      {
        path: 'order',
        component: () => import('@/views/trade/order')
      },
      {
        path: 'record',
        component: () => import('@/views/trade/record')
      },
      {
        path: 'statistics',
        component: () => import('@/views/trade/statistics')
      },
      {
        path: 'profit',
        component: () => import('@/views/trade/profit')
      },
      {
        path: 'balance',
        component: () => import('@/views/trade/balance')
      }
    ]
  },
  {
    path: 'account',
    component: () => import('@/views/account'),
    children: [
      {
        path: 'account',
        component: () => import('@/views/account/account')
      },
      {
        path: 'detailed',
        component: () => import('@/views/account/detailed')
      },
      {
        path: 'history',
        component: () => import('@/views/account/history')
      },
      {
        path: 'settle',
        component: () => import('@/views/account/settle')
      },
      {
        path: 'statistics',
        component: () => import('@/views/account/statistics')
      },
      {
        path: 'receive',
        component: () => import('@/views/account/receive')
      }
    ]
  },
  {
    path: 'users',
    component: () => import('@/views/users'),
    children: [
      {
        path: 'info',
        component: () => import('@/views/users/info')
      },
      {
        path: 'add',
        component: () => import('@/views/users/add')
      },
      {
        path: 'agent',
        component: () => import('@/views/users/agent')
      }
    ]
  },
  {
    path: 'bank',
    component: () => import('@/views/bank'),
    children: [
      {
        path: 'way',
        component: () => import('@/views/bank/way')
      },
      {
        path: 'channel',
        component: () => import('@/views/bank/channel'),
        children: [
          {
            path: 'list',
            component: () => import('@/views/bank/channel/list')
          },
          {
            path: 'flow',
            component: () => import('@/views/bank/channel/flow')
          },
          {
            path: 'edit',
            component: () => import('@/views/bank/channel/edit')
          },
          {
            path: 'sum',
            component: () => import('@/views/bank/channel/sum')
          }
        ]
      },
      {
        path: 'channelCode',
        component: () => import('@/views/bank/channelCode'),
        children: [
          {
            path: 'list',
            component: () => import('@/views/bank/channelCode/list')
          },
          {
            path: 'edit',
            meta: { title: '编辑' },
            component: () => import('@/views/bank/channelCode/edit')
          }
        ]
      }
    ]
  },
  {
    path: 'pay',
    component: () => import('@/views/pay'),
    children: [
      {
        path: 'product',
        component: () => import('@/views/pay/product')
      },
      {
        path: 'rule',
        meta: { title: '支付路由设置' },
        component: () => import('@/views/pay/rule')
      },
      {
        path: 'way',
        component: () => import('@/views/pay/way'),
        children: [
          {
            path: 'list',
            meta: { title: '设置支付方式' },
            component: () => import('@/views/pay/way/list')
          },
          {
            path: 'edit',
            meta: { title: '编辑支付方式' },
            component: () => import('@/views/pay/way/edit')
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
