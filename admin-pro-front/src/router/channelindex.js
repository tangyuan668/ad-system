import { createRouter, createWebHistory } from 'vue-router'


const channel = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [

    {
      path: '/channel/page',
      name: 'channel-page',
      component: () => import('../views/channel/ChannelPageView.vue')



    }, {
      path: '/channel/edit',
      name: 'channel-edit',
      component: () => import('../views/channel/ChannelEditView.vue')



    }

  ]
})

export default channel


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