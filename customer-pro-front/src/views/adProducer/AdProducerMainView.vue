<template>
    <el-container class="main-container">
      <el-main class="main-content">
        <h2 class="page-title">广告管理系统</h2>
        <div class="buttons-container">
          <el-button type="primary" class="custom-button" icon="el-icon-user" @click="navigateTo('personalCenter')">
            个人中心
          </el-button>
          <el-button type="primary" class="custom-button" icon="el-icon-s-platform" @click="navigateTo('productionRequest')">
            广告制作需求管理
          </el-button>
          <el-button type="primary" class="custom-button" icon="el-icon-s-platform" @click="navigateTo('Deliver')">
            广告设计图交付管理
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
        router.push('/adProducer/center');
        break;
      case 'channelManagement':
        router.push('/adProducer/channel');
        break;
      case 'requestManagement':
        router.push('/adProducer/request');
        break;
      case 'proposalManagement':
        router.push('/adProducer/proposal');
        break;
      case 'designPreviewManagement':
        router.push('/adProducer/design');
        break;
      case 'adjustmentManagement':
        router.push('/adProducer/adjustment');
        break;
      case 'contractManagement':
        router.push('/adProducer/sign');
        break;
      case 'settlementManagement':
        router.push('/adProducer/settlement');
        break;
        case 'productionRequest':
        router.push('/adProducer/productionRequest');
        break;
        case 'Deliver':
        router.push('/adProducer/deliver');
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
    background: linear-gradient(135deg, #89f7fe 0%, #66a6ff 100%);
  }
  
  .main-content {
    display: flex;
    flex-direction: column;
    align-items: center;
    background-color: rgba(255, 255, 255, 0.95);
    border-radius: 15px;
    box-shadow: 0 15px 25px rgba(0, 0, 0, 0.2);
    padding: 60px 40px; /* 调整内边距 */
    max-width: 400px;
    width: 100%;
  }
  
  .page-title {
    font-size: 24px;
    color: #333;
    margin-bottom: 30px;
    text-align: center;
  }
  
  .buttons-container {
    display: flex;
    flex-direction: column;
    gap: 15px;
    width: 100%;
    align-items: center; /* 居中对齐 */
  }
  
  .custom-button {
    width: 85%; /* 调整按钮宽度，使其更符合白框的比例 */
    font-size: 18px;
    padding: 12px 20px;
    border-radius: 10px;
    transition: all 0.3s ease;
    background-color: rgba(64, 158, 255, 0.2); /* 半透明背景 */
    border-color: rgba(64, 158, 255, 0.4); /* 半透明边框 */
    color: #333; /* 深色文字 */
    display: flex;
    align-items: center;
    justify-content: center;
  }
  
  .custom-button .el-icon {
    margin-right: 10px;
  }
  
  .custom-button:hover {
    background-color: rgba(64, 158, 255, 0.35); /* 悬停时稍微加深的透明背景 */
    border-color: rgba(64, 158, 255, 0.5); /* 悬停时稍微加深的透明边框 */
    transform: translateY(-3px);
    box-shadow: 0 8px 16px rgba(0, 123, 255, 0.3);
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
  