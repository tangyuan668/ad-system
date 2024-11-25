import { createRouter, createWebHistory } from 'vue-router'
import advertiser from './advertiserindex';
import adproducer from './adproducerindex';
import channel from './channelindex';
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [

    {
      path: '/home',
      name: 'home',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/HomeView.vue')
    }, {
      path: '/demo1',
      name: 'demo1',
      component: () => import('../views/Demo1View.vue')



    }
    , {
      path: '/admin/login',
      name: 'login',
      component: () => import('../views/AdminLoginView.vue')



    }, {
      path: '/admin/page',
      name: 'admin-page',
      component: () => import('../views/AdminPageView.vue')



    }, {
      path: '/admin/detail',
      name: 'admin-detail',
      component: () => import('../views/AdminDetailView.vue')



    }
    , {
      path: '/admin/save',
      name: 'admin-save',
      component: () => import('../views/AdminSaveView.vue')



    }
    , {
      path: '/admin/center',
      name: 'admin-center',
      component: () => import('../views/AdminCenterView.vue')



    }
    , {
      path: '/admin/main',
      name: 'admin-main',
      component: () => import('../views/AdminMainPageView.vue')



    }
    , {
      path: '/admin/main',
      name: 'admin-main',
      component: () => import('../views/AdminMainPageView.vue')



    }
    , {
      path: '/customer/page',
      name: 'customer-page',
      component: () => import('../views/customer/CustomerPageView.vue')



    }, {
      path: '/customer/detail',
      name: 'customer-detail',
      component: () => import('../views/customer/CustomerDetailView.vue')



    }
    , {
      path: '/customer/save',
      name: 'customer-save',
      component: () => import('../views/customer/CustomerSaveView.vue')
    },
    {
      path: '/Aimage',
      name: 'customer-save33',
      component: () => import('../views/Aimage.vue')
    },
    ...advertiser.getRoutes(),
    ...adproducer.getRoutes(),
    ...channel.getRoutes()
  ]
})

export default router
