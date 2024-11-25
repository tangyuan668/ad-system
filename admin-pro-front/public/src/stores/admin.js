import { ref, computed, reactive } from 'vue'
import { defineStore } from 'pinia'

export const useAdminStore = defineStore(
   //id
   'admin',
   //定义初始值
   () => {
      //定义token
      const token = ref('')

      //获取token
      const getToken = computed(() => token.value)
      const saveToken = (t) => {
         //保存token
         token.value = t
         console.log(`output->token.value`, token.value)
      }
      //定义admin信息
      const admin = reactive({
         username: '',
         realName: '',
         imagePath: '',
         id: 0
      })
      //保存admin信息
      const saveAdmin = (myAdmin) => {
         admin.username = myAdmin.username
         admin.realName = myAdmin.realName
         admin.imagePath = myAdmin.imagePath
         admin.id = myAdmin.id
      }

      //要返回token 因为持久化token常量
      return { getToken, token, saveToken, admin, saveAdmin }

   },
   //持久化
   {
      persist: {
         //开启持久化
         enabled: true,
         //策略
         strategies: [{
            //持久化key名称
            key: 'admin-info',
            //存储方式: localStorage或者sessionStorage
            storage: localStorage,
            //指定state中哪些数据需要被持久化，[]表示不持久化任何状态,undefined或者null表示持久化整个state
            //paths: ['token']
         }]

      }

   }
)