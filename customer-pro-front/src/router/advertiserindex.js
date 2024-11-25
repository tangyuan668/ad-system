import { createRouter, createWebHistory } from 'vue-router';



const advertiser = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/advertiser/center',
      name: 'PersonalCenter1',
      component: () => import('../views/advertiser/PersonalCenter.vue') // 假设有 PersonalCenter.vue 文件
    },
    {
      path: '/advertiser/channel',
      name: 'ChannelManagement2',
      component: () => import('../views/advertiser/ChannelManagement.vue') // 假设有 ChannelManagement.vue 文件
    },
    {
      path: '/advertiser/request',
      name: 'RequestManagement3',
      component: () => import('../views/advertiser/RequestManagement.vue') // 假设有 RequestManagement.vue 文件
    },
    {
      path: '/advertiser/proposal',
      name: 'ProposalManagement4',
      component: () => import('../views/advertiser/ProposalManagement.vue') // 假设有 ProposalManagement.vue 文件
    },
    {
      path: '/advertiser/proposal/edit',
      name: 'ProposalManagementEdit6',
      component: () => import('../views/advertiser/ProposalEdit.vue') // 假设有 ProposalManagement.vue 文件
    },
    {
      path: '/advertiser/design',
      name: 'DesignPreviewManagement66',
      component: () => import('../views/advertiser/DesignPreviewManagement.vue') // 假设有 DesignPreviewManagement.vue 文件
    }
    ,
    {
      path: '/advertiser/design/edit456456',
      name: 'DesignPage4324',
      component: () => import('../views/advertiser/DesignEdit.vue') // 假设有 DesignPreviewManagement.vue 文件
    }

    ,
    {
      path: '/advertiser/adjustment',
      name: 'AdjustmentManagement432423',
      component: () => import('../views/advertiser/AdjustmentManagement.vue') // 假设有 AdjustmentManagement.vue 文件
    },
    {
      path: '/advertiser/adjustment/edit',
      name: 'AdjustmentEditManagement423426876',
      component: () => import('../views/advertiser/AdjustmentEdit.vue') // 假设有 AdjustmentManagement.vue 文件
    },

    {
      path: '/advertiser/sign',
      name: 'SignManagement4675767',
      component: () => import('../views/advertiser/SignManagement.vue') // 假设有 ContractManagement.vue 文件
    },
    ,

    {
      path: '/advertiser/sign/edit',
      name: 'SignEdit76547765',
      component: () => import('../views/advertiser/SignEdit.vue') // 假设有 ContractManagement.vue 文件
    },
    {
      path: '/advertiser/settlement',
      name: 'SettlementManagement765765475',
      component: () => import('../views/advertiser/SettlementManagement.vue') // 假设有 SettlementManagement.vue 文件
    }
    ,
    {
      path: '/advertiser/settlement/edit',
      name: 'SettlementEditManagement5435354',
      component: () => import('../views/advertiser/SettlementEdit.vue') // 假设有 SettlementManagement.vue 文件
    }


    ,
    {
      path: '/advertiser/main',
      name: 'SettlementManagement987978',
      component: () => import('../views/advertiser/AdvertiserMainView.vue') // 假设有 SettlementManagement.vue 文件
    }




    ,
    {
      path: '/advertiser/login',
      name: 'advertiserLogin765756867',
      component: () => import('../views/advertiser/AdvertiserLoginView.vue') // 假设有 ContractManagement.vue 文件
    }
    ,
    {
      path: '/advertiser/main',
      name: 'advertiserMain0980980',
      component: () => import('../views/advertiser/AdvertiserMainView.vue') // 假设有 ContractManagement.vue 文件
    },
    {
      path: '/advertiser/channel/edit',
      name: 'advertiserChannelEdit53453436',
      component: () => import('../views/advertiser/ChannelEditView.vue') // 假设有 ContractManagement.vue 文件
    },
    {
      path: '/advertiser/request/edit',
      name: 'advertiserRequsetEdit756756',
      component: () => import('../views/advertiser/RequestEdit.vue') // 假设有 ContractManagement.vue 文件
    },
    {
      path: '/advertiser/productionRequest',
      name: 'advertiserProductionRequsetEdit5345345432',
      component: () => import('../views/advertiser/ProductionRequestManagement.vue') // 假设有 ContractManagement.vue 文件
    },
    {
      path: '/advertiser/productionRequest/edit',
      name: 'advertiserRequsetEdit098987',
      component: () => import('../views/advertiser/ProductionRequestEdit.vue') // 假设有 ContractManagement.vue 文件
    },
    {
      path: '/advertiser/deliver/edit',
      name: 'advertiserDeliverEdit43242',
      component: () => import('../views/advertiser/DeliverEdit.vue') // 假设有 ContractManagement.vue 文件

    },
    {
      path: '/advertiser/deliver',
      name: 'advertiserDeliverManagement534536',
      component: () => import('../views/advertiser/DeliverManagement.vue') // 假设有 ContractManagement.vue 文件
    }


  ]
})

export default advertiser;









/* export const advertiserRoutes = [
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

 */
/*,
{
  path: '/advertiser/main',
  name: 'SettlementManagement',
  component: () => import('../views/advertiser/AdvertiserMain.vue') // 假设有 SettlementManagement.vue 文件
} */
/*  ,
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
*/



