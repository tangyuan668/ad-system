import { createRouter, createWebHistory } from 'vue-router'


const adProducer = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [

        {
            path: '/adProducer/login',
            name: 'adProducer-page',
            component: () => import('../views/adProducer/AdProducerLoginView.vue')



        }, {
            path: '/adProducer/main',
            name: 'adProducer-detail',
            component: () => import('../views/adProducer/AdProducerMainView.vue')



        }
        , {
            path: '/adProducer/deliver',
            name: 'adProducer-save',
            component: () => import('../views/adProducer/DeliverManagement.vue')



        }, {
            path: '/adProducer/deliver/edit',
            name: 'adProducer-save333',
            component: () => import('../views/adProducer/DeliverEdit.vue')



        },
        {
            path: '/adProducer/productionRequest',
            name: 'adProducer-save96',
            component: () => import('../views/adProducer/ProductionRequestManagement.vue')



        },
        {
            path: '/adProducer/productionRequest/edit',
            name: 'adProducer-save9699',
            component: () => import('../views/adProducer/ProductionRequestEdit.vue')



        },
        {
            path: '/adProducer/center',
            name: 'adProducer-save96798',
            component: () => import('../views/adProducer/PersonalCenter.vue')



        }


    ]
})

export default adProducer
