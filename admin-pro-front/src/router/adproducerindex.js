import { createRouter, createWebHistory } from 'vue-router'


const adproducer = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [

    {
      path: '/adproducer/page',
      name: 'adproducer-page',
      component: () => import('../views/adproducer/AdProducerPageView.vue')



    }, {
      path: '/adproducer/detail',
      name: 'adproducer-detail',
      component: () => import('../views/adproducer/AdProducerDetailView.vue')



    }
    , {
      path: '/adproducer/save',
      name: 'adproducer-save',
      component: () => import('../views/adproducer/AdProducerSaveView.vue')



    }
  ]
})

export default adproducer


/* export default [
  {
    path: '/admin',
    component: () => import('@/views/admin/AdminDashboard.vue'),
    children: [
      {
        path: 'users',
        component: () => import('@/views/admin/UserManagement.vue'),
      },
      {
        path: 'settings',
        component: () => import('@/views/admin/AdminSettings.vue'),
      },
    ],
  },
]; */