<template>
  <el-container class="main-container">
    <el-main class="main-content">
      <div class="buttons-container">
        <el-button type="primary" class="custom-button personal-center-button" icon="el-icon-user" @click="navigateTo('personalCenter')">
          个人中心
        </el-button>
        <el-button type="primary" class="custom-button" icon="el-icon-s-custom" @click="navigateTo('customerManagement')">
          广告客户管理
        </el-button>
        <el-button type="primary" class="custom-button" icon="el-icon-s-flag" @click="navigateTo('advertiserManagement')">
          广告商管理
        </el-button>
        <el-button type="primary" class="custom-button" icon="el-icon-film" @click="navigateTo('producerManagement')">
          广告制作商管理
        </el-button>
        <el-button type="primary" class="custom-button" icon="el-icon-s-platform" @click="navigateTo('channelManagement')">
          广告投放渠道管理
        </el-button>
      </div>
    </el-main>
  </el-container>
</template>

<script setup>
import { useRouter } from 'vue-router';

const router = useRouter();

const navigateTo = (component) => {
  switch (component) {
    case 'personalCenter':
      router.push('/admin/center');
      break;
    case 'customerManagement':
      router.push('/customer/page');
      break;
    case 'advertiserManagement':
      router.push('/advertiser/page');
      break;
    case 'producerManagement':
      router.push('/adproducer/page');
      break;
    case 'channelManagement':
      router.push('/channel/page');
      break;
  }
};
</script>

<style scoped>
.main-container {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background: url('@/images/下载.jpg') no-repeat center center;
  background-size: cover;
}

.main-content {
  display: flex;
  justify-content: center;
  align-items: center;
/*  background-color: rgba(255, 255, 255, 0.8); */
  border-radius: 15px;
  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.1);
  padding: 40px;
}

.buttons-container {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.custom-button {
  width: 280px;
  font-size: 18px;
  padding: 12px 20px;
  border-radius: 8px;
  transition: all 0.3s ease;
  text-align: center;
  background-color: rgba(64, 158, 255, 0.2); /* 半透明背景 */
  border-color: rgba(64, 158, 255, 0.4); /* 半透明边框 */
  color: #ffffff; /* 白色文字 */
}

.personal-center-button {
  margin-left: 10px; /* 向右移动 */
}

.custom-button:hover {
  background-color: rgba(64, 158, 255, 0.3); /* 悬停时稍微加深的透明背景 */
  border-color: rgba(64, 158, 255, 0.5); /* 悬停时稍微加深的透明边框 */
  transform: translateY(-3px);
  box-shadow: 0 8px 16px rgba(0, 123, 255, 0.2);
}

.custom-button .el-icon {
  margin-right: 10px;
}

.el-button--primary {
  background-color: transparent; /* 默认透明背景 */
  border-color: transparent; /* 默认透明边框 */
}

.el-button--primary:hover {
  background-color: rgba(64, 158, 255, 0.3);
  border-color: rgba(64, 158, 255, 0.5);
}
</style>
