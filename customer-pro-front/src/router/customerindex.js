import { createRouter, createWebHistory } from 'vue-router';

const routes = [
  {
    path: '/customer/center',
    name: 'PersonalCenter',
    component: () => import('../views/customer/PersonalCenter.vue') // 假设有 PersonalCenter.vue 文件
  },
  {
    path: '/customer/channel',
    name: 'ChannelManagement',
    component: () => import('../views/customer/ChannelManagement.vue') // 假设有 ChannelManagement.vue 文件
  },
  {
    path: '/customer/request',
    name: 'RequestManagement',
    component: () => import('../views/customer/RequestManagement.vue') // 假设有 RequestManagement.vue 文件
  },
  {
    path: '/customer/proposal',
    name: 'ProposalManagement',
    component: () => import('../views/customer/ProposalManagement.vue') // 假设有 ProposalManagement.vue 文件
  },
  {
    path: '/customer/proposal/edit',
    name: 'ProposalManagementEdit',
    component: () => import('../views/customer/ProposalEdit.vue') // 假设有 ProposalManagement.vue 文件
  },
  {
    path: '/customer/design',
    name: 'DesignPreviewManagement',
    component: () => import('../views/customer/DesignPreviewManagement.vue') // 假设有 DesignPreviewManagement.vue 文件
  }
  ,
  {
    path: '/customer/design/edit',
    name: 'DesignPage',
    component: () => import('../views/customer/DesignEdit.vue') // 假设有 DesignPreviewManagement.vue 文件
  }

  ,
  {
    path: '/customer/adjustment',
    name: 'AdjustmentManagement',
    component: () => import('../views/customer/AdjustmentManagement.vue') // 假设有 AdjustmentManagement.vue 文件
  },
  {
    path: '/customer/adjustment/edit',
    name: 'AdjustmentEditManagement',
    component: () => import('../views/customer/AdjustmentEdit.vue') // 假设有 AdjustmentManagement.vue 文件
  },

  {
    path: '/customer/sign',
    name: 'SignManagement',
    component: () => import('../views/customer/SignManagement.vue') // 假设有 ContractManagement.vue 文件
  },
  ,

  {
    path: '/customer/sign/edit',
    name: 'SignEdit',
    component: () => import('../views/customer/SignEdit.vue') // 假设有 ContractManagement.vue 文件
  },
  {
    path: '/customer/settlement',
    name: 'SettlementManagement',
    component: () => import('../views/customer/SettlementManagement.vue') // 假设有 SettlementManagement.vue 文件
  }
  ,
  {
    path: '/customer/settlement/edit',
    name: 'SettlementEditManagement',
    component: () => import('../views/customer/SettlementEdit.vue') // 假设有 SettlementManagement.vue 文件
  }


  /*,
  {
    path: '/customer/main',
    name: 'SettlementManagement',
    component: () => import('../views/customer/CustomerMain.vue') // 假设有 SettlementManagement.vue 文件
  } */
  ,
  {
    path: '/customer/login',
    name: 'customerLogin',
    component: () => import('../views/customer/CustomerLoginView.vue') // 假设有 ContractManagement.vue 文件
  }
  ,
  {
    path: '/customer/main',
    name: 'customerMain',
    component: () => import('../views/customer/CustomerMainView.vue') // 假设有 ContractManagement.vue 文件
  },
  {
    path: '/customer/channel/edit',
    name: 'customerChannelEdit',
    component: () => import('../views/customer/ChannelEditView.vue') // 假设有 ContractManagement.vue 文件
  },
  {
    path: '/customer/request/edit',
    name: 'customerRequsetEdit',
    component: () => import('../views/customer/RequestEdit.vue') // 假设有 ContractManagement.vue 文件
  }
];

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
});

export default router;
