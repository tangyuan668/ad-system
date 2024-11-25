import { ref, computed, reactive } from 'vue'
import { defineStore } from 'pinia'
export const useCountStore = defineStore(
    //id
    'count',
    //定义初始值
    () => {

        //定义count信息
        const count = reactive({
            cuid: 0,
            num: 0


        })
        //保存count信息
        const saveCount = (id, cuid) => {
            console.log(`saveCount->id`, id)
            count.num = id
            count.cuid = cuid

        }
        //清空cusomer信息
        const clear = () => {

            count.num = 0
            count.cuid = 0
        }


        //要返回token 因为持久化token常量
        return { count, saveCount, clear }

    },
    //持久化
    {
        persist: {
            //开启持久化
            enabled: true,
            //策略
            strategies: [{
                //持久化key名称
                key: 'count-info',
                //存储方式: localStorage或者sessionStorage
                storage: localStorage,
                //指定state中哪些数据需要被持久化，[]表示不持久化任何状态,undefined或者null表示持久化整个state
                //paths: ['token']
            }]

        }

    }
)