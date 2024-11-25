//import './assets/main.css'

import { createApp } from 'vue'
import { createPinia } from 'pinia'
//导入pinia持久化插件对象
import piniaPersist from 'pinia-plugin-persist'
//导入ElementPlus组件
import ElementPlus from 'element-plus'
//引入ElementPlus的css
import 'element-plus/dist/index.css'
//引入图标库
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import App from './App.vue'
import router from './router'

const app = createApp(App)
const pinia = createPinia()
//pinia使用持久化插件
pinia.use(piniaPersist)
app.use(pinia)
//app.use(createPinia())
app.use(router)
//使用ElementPlus组件
app.use(ElementPlus)
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  //图标注册成全局组件 组件名称是图标的名称
  app.component(key, component)
}
app.mount('#app')
