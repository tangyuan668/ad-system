<template>
   <div class="notification-container">
    <header class="header">
      <el-icon class="back" @click="handleBack">
        <Back />
      </el-icon>
      <span>消息中心</span>
      <span class="mark-read" @click="markAllAsRead">全部已读</span>
      <span class="unread-count">未读: {{ unreadCount }}</span>
    </header>
    <div class="notifications">
      <el-card v-for="(item, index) in notifications" :key="index" class="notification-card">
        <el-row justify="space-between" align="middle">
          <el-col :span="3">
            <el-avatar :src="female"/>
          </el-col>
          <el-col :span="16">
            <p class="title">{{ item.type }}</p>
            <p class="message">{{ item.content }}</p>
          </el-col>
          <el-col :span="5" class="info">
            <p class="date">{{ item.createTime }}</p>
            <el-button
              v-if="item.status !== '已读'"
              type="primary"
              size="small"
              @click="markAsRead(item, index)"
            >
              标记为已读
            </el-button>
            <el-badge v-if="item.status=='未读'" value="1" class="badge"></el-badge>
            <!-- 新增删除按钮 -->
            <el-button
              type="danger"
              size="small"
              icon="el-icon-delete"
              @click="deleteNotification(item,index)"
              class="delete-button"
              v-if="item.status=='已读'"
            >
              删除已读
            </el-button>




          </el-col>
        </el-row>
      </el-card>
    </div>
  </div>
</template>

<script setup>
import { reactive ,ref,onMounted, computed} from 'vue';
import { useRouter } from 'vue-router';
import { ElMessage } from 'element-plus';
import { emailSave,getemail,updateemail,deleteemail } from '@/api/email';
import { useCustomerStore } from '@/stores/customer';
import { useCountStore } from '@/stores/count';
import female from '@/images/th (1).jpg'
const router = useRouter();
const store=useCustomerStore()
const notifications = ref([]);




const handleQueryPage=()=>{
getemail(store.customer.id).then((res)=>{

notifications.value=res.data
console.log(`output->notifications.value`,notifications.value)

}

)}

onMounted(()=>{

  handleQueryPage()
  useCountStore().saveCount(unreadCount.value)

})



const handleBack = () => {
  router.back();
};

// 标记为已读的处理函数
const markAsRead = (item, index) => {
  // 更新通知的状态为已读
  item.status = '已读';
  updateemail(item).then(()=>{
    console.log(`unreadCount.value`,unreadCount.value)
    useCountStore().saveCount(unreadCount.value)
    handleQueryPage()
  })
  // 可以添加更多逻辑，例如同步到服务器
};
// 删除通知的处理函数
const deleteNotification = (item,index) => {
  // 从通知列表中移除对应的项
  deleteemail(item.id).then(()=>{
    handleQueryPage()
  })

  // 这里可以添加更多逻辑，例如发送请求到服务器删除数据
};
// 全部标记为已读的处理函数
const markAllAsRead = () => {
  // 遍历所有通知，将状态设置为已读
  notifications.value.forEach((item) => {
    item.status = '已读';
    updateemail(item)
    useCountStore().saveCount(unreadCount)
  });
  // 可以在这里添加逻辑，比如发送请求到服务器同步已读状态
};

// 计算属性：未读消息数量
const unreadCount = computed(() => {
  return notifications.value.filter(item => item.status === '未读').length;
});
</script>

<style scoped>
.notification-container {
  padding: 10px 20px;
}

.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-size: 18px;
  margin-bottom: 20px;
}

.header .back {
  cursor: pointer;
}

.mark-read {
  font-size: 14px;
  color: #409eff;
  cursor: pointer;
}

.notifications {
  display: flex;
  flex-direction: column;
  gap: 10px;
}

.notification-card {
  padding: 10px;
  border-radius: 8px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
}

.notification-card .title {
  font-weight: bold;
  margin: 0;
}

.notification-card .message {
  margin: 0;
  color: #909399;
  font-size: 14px;
}

.notification-card .info {
  text-align: right;
}

.notification-card .date {
  font-size: 12px;
  color: #909399;
}

.badge {
  margin-top: 5px;
}
.read-button {
  /* color: #af1417;  *//* 可自定义按钮颜色 */
 /*  font-size: 12px; */
  margin-left: 20px; /* 与徽标的距离 */
}
.unread-count {
  margin-left: 20px; 
  font-weight: bold;
}
</style>