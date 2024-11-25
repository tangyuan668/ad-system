import { createRouter, createWebHistory } from 'vue-router'


const advertiser = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [

    {
      path: '/advertiser/page',
      name: 'advertiser-page',
      component: () => import('../views/advertiser/AdvertiserPageView.vue')



    }, {
      path: '/advertiser/detail',
      name: 'advertiser-detail',
      component: () => import('../views/advertiser/AdvertiserDetailView.vue')



    }
    , {
      path: '/advertiser/save',
      name: 'advertiser-save',
      component: () => import('../views/advertiser/AdvertiserSaveView.vue')



    }
  ]
})

export default advertiser


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