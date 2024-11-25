import { createRouter, createWebHistory } from 'vue-router';

const routes = [
  {
    path: '/advertiser/center',
    name: 'PersonalCenter',
    component: () => import('../views/advertiser/PersonalCenter.vue') // 假设有 PersonalCenter.vue 文件
  },
  {
    path: '/advertiser/channel',
    name: 'ChannelManagement',
    component: () => import('../views/advertiser/ChannelManagement.vue') // 假设有 ChannelManagement.vue 文件
  },
  {
    path: '/advertiser/request',
    name: 'RequestManagement',
    component: () => import('../views/advertiser/RequestManagement.vue') // 假设有 RequestManagement.vue 文件
  },
  {
    path: '/advertiser/proposal',
    name: 'ProposalManagement',
    component: () => import('../views/advertiser/ProposalManagement.vue') // 假设有 ProposalManagement.vue 文件
  },
  {
    path: '/advertiser/proposal/edit',
    name: 'ProposalManagementEdit',
    component: () => import('../views/advertiser/ProposalEdit.vue') // 假设有 ProposalManagement.vue 文件
  },
  {
    path: '/advertiser/design',
    name: 'DesignPreviewManagement',
    component: () => import('../views/advertiser/DesignPreviewManagement.vue') // 假设有 DesignPreviewManagement.vue 文件
  }
  ,
  {
    path: '/advertiser/design/edit',
    name: 'DesignPage',
    component: () => import('../views/advertiser/DesignEdit.vue') // 假设有 DesignPreviewManagement.vue 文件
  }

  ,
  {
    path: '/advertiser/adjustment',
    name: 'AdjustmentManagement',
    component: () => import('../views/advertiser/AdjustmentManagement.vue') // 假设有 AdjustmentManagement.vue 文件
  },
  {
    path: '/advertiser/adjustment/edit',
    name: 'AdjustmentEditManagement',
    component: () => import('../views/advertiser/AdjustmentEdit.vue') // 假设有 AdjustmentManagement.vue 文件
  },

  {
    path: '/advertiser/sign',
    name: 'SignManagement',
    component: () => import('../views/advertiser/SignManagement.vue') // 假设有 ContractManagement.vue 文件
  },
  ,

  {
    path: '/advertiser/sign/edit',
    name: 'SignEdit',
    component: () => import('../views/advertiser/SignEdit.vue') // 假设有 ContractManagement.vue 文件
  },
  {
    path: '/advertiser/settlement',
    name: 'SettlementManagement',
    component: () => import('../views/advertiser/SettlementManagement.vue') // 假设有 SettlementManagement.vue 文件
  }
  ,
  {
    path: '/advertiser/settlement/edit',
    name: 'SettlementEditManagement',
    component: () => import('../views/advertiser/SettlementEdit.vue') // 假设有 SettlementManagement.vue 文件
  }


  /*,
  {
    path: '/advertiser/main',
    name: 'SettlementManagement',
    component: () => import('../views/advertiser/AdvertiserMain.vue') // 假设有 SettlementManagement.vue 文件
  } */
  ,
  {
    path: '/advertiser/login',
    name: 'advertiserLogin',
    component: () => import('../views/advertiser/AdvertiserLoginView.vue') // 假设有 ContractManagement.vue 文件
  }
  ,
  {
    path: '/advertiser/main',
    name: 'advertiserMain',
    component: () => import('../views/advertiser/AdvertiserMainView.vue') // 假设有 ContractManagement.vue 文件
  },
  {
    path: '/advertiser/channel/edit',
    name: 'advertiserChannelEdit',
    component: () => import('../views/advertiser/ChannelEditView.vue') // 假设有 ContractManagement.vue 文件
  },
  {
    path: '/advertiser/request/edit',
    name: 'advertiserRequsetEdit',
    component: () => import('../views/advertiser/RequestEdit.vue') // 假设有 ContractManagement.vue 文件
  }
];

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
});

export default router;
