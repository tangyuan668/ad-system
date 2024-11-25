<script setup>
import { ref } from 'vue';
import image1 from '@/images/image.png';
import image2 from '@/images/image.png';
import image3 from '@/images/image.png';
const carouselItems = ref([
  { image: image1 }, // 动图或宣传图片1
  { image: image2 }, // 动图或宣传图片2
  { image: image3 }, // 动图或宣传图片3
]);

</script>

<template>
  <el-container style="height: 100vh;">
    <!-- 主内容区 -->
    <el-main class="main">
      <div class="content-wrapper">
        <h1 class="welcome-message">Welcome to 广告制作管理系统</h1>

        <!-- 宣传动图轮播 -->
        <el-carousel indicator-position="outside" height="300px" autoplay>
          <el-carousel-item v-for="(item, index) in carouselItems" :key="index">
            <img :src="item.image" class="carousel-image" alt="宣传图">
          </el-carousel-item>
        </el-carousel>

        <el-row :gutter="20" style="margin-top: 30px;">
          <el-col :span="8">
            <el-card>
              <template #header>
                <div class="clearfix">
                  <i class="el-icon-edit-outline"></i> 广告需求管理
                </div>
              </template>
              <div class="text">
                在这里您可以提交和管理广告需求，确保广告制作的顺利进行。
              </div>
            </el-card>
          </el-col>
          <el-col :span="8">
            <el-card>
              <template #header>
                <div class="clearfix">
                  <i class="el-icon-document-checked"></i> 广告提案管理
                </div>
              </template>
              <div class="text">
                管理您的广告提案，跟踪审核状态，确保广告按时投放。
              </div>
            </el-card>
          </el-col>
          <el-col :span="8">
            <el-card>
              <template #header>
                <div class="clearfix">
                  <i class="el-icon-film"></i> 广告制作管理
                </div>
              </template>
              <div class="text">
                管理广告的制作过程，确保广告制作按计划进行。
              </div>
            </el-card>
          </el-col>
        </el-row>
      </div>
    </el-main>
  </el-container>
</template>
<style scoped>
.main {
  background: linear-gradient(135deg, #ece9e6, #ffffff); /* 渐变背景 */
  padding: 40px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.content-wrapper {
  max-width: 1200px;
  width: 100%;
  animation: fadeIn 1s ease-in-out; /* 页面加载动画 */
}

.welcome-message {
  font-family: 'Montserrat', sans-serif; /* 使用自定义字体 */
  font-size: 32px;
  font-weight: bold;
  text-align: center;
  color: #333;
  margin-bottom: 40px;
  border-bottom: 3px solid #409eff; /* 标题底部装饰线 */
  display: inline-block;
  padding-bottom: 10px;
}

.carousel-image {
  width: 100%;
  height: 100%;
  object-fit: cover; /* 确保图片填满容器 */
  border-radius: 10px; /* 图片圆角 */
  transition: transform 0.3s ease-in-out;
}

.carousel-image:hover {
  transform: scale(1.05); /* 图片悬停放大效果 */
}

.el-card {
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1); /* 卡片阴影 */
  border-radius: 10px; /* 卡片圆角 */
  transition: transform 0.3s ease-in-out, box-shadow 0.3s ease-in-out;
}

.el-card:hover {
  transform: translateY(-5px); /* 卡片悬浮效果 */
  box-shadow: 0 6px 18px rgba(0, 0, 0, 0.15);
}

.text {
  font-size: 14px;
  color: #666;
  line-height: 1.6;
  margin: 10px 0;
  text-align: justify;
}

.clearfix i {
  margin-right: 8px;
  color: #409eff; /* 图标颜色 */
}

@keyframes fadeIn {
  from { opacity: 0; }
  to { opacity: 1; }
}

@media (max-width: 768px) {
  .welcome-message {
    font-size: 24px; /* 小屏幕下调整字体大小 */
  }

  .el-col {
    span: 24;
    margin-bottom: 20px; /* 小屏幕下卡片全宽显示 */
  }
}
</style>