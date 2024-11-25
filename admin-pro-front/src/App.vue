<script setup>
import { reactive } from 'vue'
import { useRouter } from 'vue-router'
import Header from '@/components/Header.vue'
import Footer from '@/components/Footer.vue'
import {useAdminStore} from '@/stores/admin'
//import { Lock, User } from "@element-plus/icons-vue"

const store = useAdminStore()
//不显示左边菜单栏，头，脚等，rul路径有哪些，需要配置
const noMenu = ['/login']
const router = useRouter()
const dataInfo = reactive({
  showMenu: true,
  defaultOpen: ['1', '2'],
  currentPath: '/',
})
//全局前置路由守卫，初始化的时候调用，每次路由切换之前被调用
 /* router.beforeEach((to, from, next) => {
  if (to.path == 'admin/login') {
    // 如果路径是 /login 则正常执行
    next()
  } else {
    // 如果不是 /login，判断是否有 token
    if (!store.getToken || store.getToken == '') {
      // 如果没有token说明没有登录过，则跳至登录页面
      if (to.path !== '/admin/login') { // 确保不会重复重定向到相同路径
        next({ path: '/admin/login' }); // 使用绝对路径
      } else {
        next();
      }
    } else {
      // 否则继续执行
      next()
    }
  }
  dataInfo.showMenu = !noMenu.includes(to.path)
  dataInfo.currentPath = to.path
})  */
/* router.beforeEach((to, from, next) => {
  // 确保路径匹配准确，添加斜杠
  if (to.path === '/admin/login') {
    // 如果路径是 /admin/login 则正常执行
    next();
  } else {
    // 如果不是 /admin/login，判断是否有 token
    if (!store.getToken || store.getToken === '') {
      // 如果没有 token 说明没有登录过，则跳至登录页面
      if (to.path !== '/admin/login') { // 确保不会重复重定向到相同路径
        next({ path: '/admin/login' }); // 使用绝对路径
      } else {
        next();
      }
    } else {
      // 如果有 token，则继续执行
      next();
    }
  }

  // 更新菜单显示和当前路径
  dataInfo.showMenu = !noMenu.includes(to.path);
  dataInfo.currentPath = to.path; // 确保拼写正确（小写 p）
}); */

</script>

<template>
  <div class="layout">
    <!-- 左边菜单栏、顶部、底部-->
    <el-container v-if="dataInfo.showMenu" class="container">
      <!-- 左边菜单栏 -->
      <el-aside class="aside">
        <div class="head">
          <div>
            <img src="@/images/th.jpg" alt="logo" />
          </div>
        </div>
        <div class="line" />
        <el-menu background-color="#455a75" text-color="#ffffff" active-text-color="#1abc9c" :router="true"
         :default-openeds="dataInfo.defaultOpen" :default-active="dataInfo.currentPath">

          <el-sub-menu index="1">
            <template #title>
              <span>首页</span>
            </template>
            <el-menu-item-group>
              <el-menu-item index="/home">
                <el-icon>
                  <Odometer />
                </el-icon>
                首页
              </el-menu-item>
            </el-menu-item-group>
          </el-sub-menu>
          <el-sub-menu index="2">
            <template #title>
              <span>用户登录</span>
            </template>
            <el-menu-item-group>
              <el-menu-item index="/admin/login">
                <el-icon>
                  <Picture />
                </el-icon>
                管理员
              </el-menu-item>
              <el-menu-item index="/admin/login">
                <a href="http://localhost:5173/customer/login" target="_blank">
                  <el-icon>
                    <Sell />
                  </el-icon>
                  广告客户
                </a>
              </el-menu-item>
              <el-menu-item index="/admin/login">
                <a href="http://localhost:5173/advertiser/login" target="_blank">
                <el-icon>
                  <Sell />
                </el-icon>
                广告商
                </a>
              </el-menu-item>
              <el-menu-item index="/admin/login">
                <a href="http://localhost:5173/adProducer/login" target="_blank">
                <el-icon>
                  <Sell />
                </el-icon>
                广告制作商
                </a>
              </el-menu-item>
            </el-menu-item-group>
          </el-sub-menu>
        </el-menu>
      </el-aside>
      <el-container class="content">
        <!-- Header 组件-->
        <Header />
        <!-- 变化的内部部分 -->
        <div class="main">
          <!-- 显示菜单功能对应路由的 数据区 -->
          <router-view />
        </div>
        <!-- Footer 组件-->
        <Footer />
      </el-container>
    </el-container>
    <!-- 没有左边菜单、顶部、底部 ，例如：登录、注册 -->
    <el-container v-else class="container">
      <!--显示路由，除了菜单的功能之外，例如：登录、注册等等-->
      <router-view />
    </el-container>
  </div>
</template>

<style scoped>
.layout {
  min-height: 100vh;
  background-color: #f4f7fa;
  font-family: 'Roboto', sans-serif;
}

.container {
  height: 100vh;
}

.aside {
  width: 220px !important;
  background-color: #455a75; /* 统一设置为较浅的颜色 */
  box-shadow: 2px 0 5px rgba(0, 0, 0, 0.1);
}


.head {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 60px;
  background-color: #34495e;
  margin-bottom: 10px;
}

.head>div {
  display: flex;
  align-items: center;
}

.head img {
  width: 45px;
  height: 45px;
  margin-right: 10px;
  border-radius: 50%;
  transition: transform 0.3s;
}

.head img:hover {
  transform: scale(1.1);
}

.head span {
  font-size: 22px;
  color: #ecf0f1;
  font-weight: bold;
}

.line {
  border-top: 1px solid rgba(255, 255, 255, 0.1);
  border-bottom: 1px solid rgba(0, 0, 0, 0.15);
}

.content {
  display: flex;
  flex-direction: column;
  max-height: 100vh;
  overflow: hidden;
}

.main {
  height: calc(100vh - 100px);
  overflow: auto;
  padding: 20px;
  background-color: #ecf0f1;
  border-radius: 10px;
  margin: 10px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}
</style>
