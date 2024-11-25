<script setup>
import { reactive,onBeforeMount,ref } from 'vue'
import { useRouter } from 'vue-router'
import {useCustomerStore} from '@/stores/customer'
import {useAdvertiserStore} from '@/stores/advertiser'
import { useAdProducerStore } from '@/stores/adProducer'
import { useCountStore } from '@/stores/count'
import { getNums } from '@/api/email'

let count=0
let count2=ref(0)
let state = reactive({ username:'',imagePath: ''})
let store=useCustomerStore()

if(useCustomerStore().customer.id!=0)
{
   store = useCustomerStore()
   // 用户名
   /*  hasBack: false,  */// 是否展示返回icon
state.username=store.customer.username
state.imagePath=store.customer.imagePath
count=1
getNums(useCustomerStore().customer.id).then((res) => {
        count2.value=res.data
        console.log(`output->count2`,count2.value)
    })
}
else if(useAdvertiserStore().advertiser.id!=0)
{
    store= useAdvertiserStore()
state.username=store.advertiser.username
state.imagePath=store.advertiser.imagePath

/* hasBack: false */ // 是否展示返回icon
}

else if(useAdProducerStore().adProducer.id!=0)
{

store= useAdProducerStore() 
state.username=store.adProducer.username
state.imagePath=store.adProducer.imagePath
}

const router = useRouter()
// 退出登录
const btnLogout = () => {
    //把token设置为空
    store.saveToken('')
count2=0
    if(useCustomerStore().customer.id!=0)
    {useCountStore().clear()}
    // 重置customer
    store.clear()
    state.username = '';
    state.imagePath = '';
    //console.log(`output->store.cus`,store.customer.username)
  
    //跳转登录页面
    router.push({
        path:"/home"
    }).then(() => {
        // 跳转完成后刷新页面
        window.location.reload();
    }).catch(err => {
        console.error(err);
    });
   
 
}
//全局后置路由守卫，组件初始化的时候调用，每次路由切换后执行(to,from) 没有next函数因为已经路由切换完毕
/* router.afterEach((to) => {
    const { id } = to.query */
   // console.log("===========afterEach 的to对象=============");
  //  console.log(to);
    //  state.name = pathMap[to.name]
    // level2 和 level3 需要展示返回icon
 //   console.log('to.name', to.name)
   /*  state.hasBack = ['level2', 'level3'].includes(to.name)
}) */

// 返回方法
const back = () => {
    router.back()
}

const handleIconClick = () => {
  router.push({
        path:"/customer/email"
    })
}
</script>
<template>
  <div class="header">
      <div class="left">
          <el-icon class="back" v-if="state.hasBack" @click="back">
              <Back />
          </el-icon>
          <span style="font-size: 20px">{{ state.name }}</span>
      </div>
      <div class="right" v-if="state.username"> <!-- 添加 v-if 来控制显示 -->
          <el-popover placement="bottom" :width="320" trigger="click" popper-class="popper-user-box">

          
            
           

              <template #reference>
                  <div class="author">

                    
                      <!-- 使用 el-badge 将徽章显示在图标旁边 -->
            <el-badge :value="count2"  class="badge-icon"  >
              <img
                class="left-icon"
                src="@/images/下载 (2).jpg"
                alt="图标"
                @click.stop="handleIconClick"
               
              />
            </el-badge>
                     
                    
                    
                    <img class="avatar" :src="state.imagePath" alt="用户头像" />
                      <span class="username">{{ state.username }}</span>
                      <i class="el-icon-caret-bottom" />
                  </div>
              </template>
              <div class="nickname">
                  <p>登录名：{{ state.username }}</p>
                  <el-tag size="small" effect="dark" class="logout" @click="btnLogout">退出</el-tag>
              </div>
          </el-popover>
      </div>
  </div>
</template>

<style scoped>
.header {
    height: 50px;
    border-bottom: 1px solid #e9e9e9;
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 0 20px;
}

.header .left .back {
    border: 1px solid #e9e9e9;
    padding: 5px;
    border-radius: 50%;
    margin-right: 5px;
    cursor: pointer;
}

.right > div > .icon {
    font-size: 18px;
    margin-right: 6px;
}

.author {
    margin-left: 0px; /* 减小左侧间距 */
    cursor: pointer;
    display: flex;
    align-items: center;
    padding: 5px; /* 增加整体的内边距 */
    margin-top: 10px; /* 向下移动整个 .author */
}

.author .left-icon {
    width: 40px; /* 根据需要调整图标大小 */
    height: 40px; 
    margin-right: 8px; /* 图标与头像之间的间距 */
    /* 如果需要额外调整单独的图标位置，可以在此设置 margin-top */
}

.author .avatar {
    width: 40px; /* 放大头像 */
    height: 40px; /* 放大头像 */
    border-radius: 50%; /* 使头像显示为圆形 */
    margin-right: 10px; /* 增加头像与用户名之间的间距 */
    object-fit: cover; /* 确保图片按比例显示 */
}
.username {
    font-size: 18px; /* 放大用户名字体 */
    color: #333;
}

.popper-user-box {
    background: url('https://s.yezgea02.com/lingling-h5/static/account-banner-bg.png') 50% 50% no-repeat !important;
    background-size: cover !important;
    border-radius: 0 !important;
}

.popper-user-box .nickname {
    position: relative;
    color: #131212;
}

.popper-user-box .nickname .logout {
    position: absolute;
    right: 0;
    top: 0;
    cursor: pointer;
}
.badge-icon {
  margin-right: 20px; /* 调整徽章和图标的间距 */
}
</style>
