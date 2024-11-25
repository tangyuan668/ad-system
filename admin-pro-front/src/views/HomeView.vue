

<script setup>
import { ref, onMounted, watch } from 'vue';
import * as echarts from 'echarts';
import image1 from '@/images/下载 (1).jpg';
import image2 from '@/images/OIP-C (1).jpg';
import image3 from '@/images/OIP-C.jpg';
import image4 from '@/images/OIP-C (2).jpg';
import image5 from '@/images/OIP-C (3).jpg';
import image6 from '@/images/OIP-C (4).jpg';

const carouselItems = ref([
  { image: image1 },
  { image: image2 },
  { image: image3 },
  { image: image4 },
  { image: image5 },
  { image: image6 }
]);

const adData = ref([
  { type: '视频广告', count: 120 },
  { type: '图片广告', count: 80 },
  { type: '横幅广告', count: 150 },
  { type: '弹窗广告', count: 70 },
  { type: '社交广告', count: 90 },
]);

const chart = ref(null);
const lineChart = ref(null);
const chartInstance = ref(null);
const lineChartInstance = ref(null);

const initChart = () => {
  chartInstance.value = echarts.init(chart.value);

  const option = {
    title: {
      text: '热门广告类型',
      left: 'center',
    },
    tooltip: {
      trigger: 'item',
    },
    legend: {
      bottom: '5%',
      left: 'center',
    },
    series: [
      {
        name: '广告类型',
        type: 'pie',
        radius: ['40%', '70%'],
        data: adData.value.map(item => ({
          value: item.count,
          name: item.type,
        })),
        emphasis: {
          itemStyle: {
            shadowBlur: 10,
            shadowOffsetX: 0,
            shadowColor: 'rgba(0, 0, 0, 0.5)',
          },
        },
      },
    ],
  };

  chartInstance.value.setOption(option);
};

const initLineChart = () => {
  lineChartInstance.value = echarts.init(lineChart.value);

  const option = {
    title: {
      text: '近十年热门广告类型变化',
      left: 'center',
    },
    tooltip: {
      trigger: 'axis',
    },
    legend: {
      bottom: '5%',
      left: 'center',
    },
    xAxis: {
      type: 'category',
      data: ['2014', '2015', '2016', '2017', '2018', '2019', '2020', '2021', '2022', '2023'],
    },
    yAxis: {
      type: 'value',
    },
    series: [
      {
        name: '视频广告',
        type: 'line',
        data: [50, 60, 80, 120, 130, 150, 170, 190, 210, 220],
      },
      {
        name: '图片广告',
        type: 'line',
        data: [70, 80, 90, 100, 110, 100, 95, 85, 80, 75],
      },
      {
        name: '横幅广告',
        type: 'line',
        data: [90, 100, 110, 120, 130, 140, 150, 160, 170, 180],
      },
      {
        name: '弹窗广告',
        type: 'line',
        data: [60, 65, 70, 75, 80, 70, 65, 60, 55, 50],
      },
      {
        name: '社交广告',
        type: 'line',
        data: [40, 50, 60, 70, 80, 90, 100, 110, 120, 130],
      },
    ],
  };

  lineChartInstance.value.setOption(option);
};

onMounted(() => {
  initChart();
  initLineChart();
});

watch(adData, () => {
  if (chartInstance.value) {
    initChart();
  }
});
</script>




<template>
  <el-container style="height: 100vh;">
    <el-main class="main">
    
      <div class="content-wrapper">
       <br><br> <br><br><br><br><br><br><br><br><br><br>
        <h1 class="welcome-message">Welcome to 广告制作管理系统</h1>
        <!-- 宣传动图轮播 -->
        <el-carousel indicator-position="outside" type="card" height="300px" autoplay>
          <el-carousel-item v-for="(item, index) in carouselItems" :key="index">
            <img :src="item.image" class="carousel-image" alt="宣传图">
          </el-carousel-item>
        </el-carousel>

        <!-- 并列图表容器 -->
        <div class="charts-wrapper">
          <!-- 饼图 -->
          <div ref="chart" class="chart"></div>

          <!-- 折线图 -->
          <div ref="lineChart" class="chart"></div>
        </div>


      </div>
    </el-main>
  </el-container>
</template>

<style scoped>
.main {
  background: linear-gradient(135deg, #ece9e6, #ffffff);
  padding: 40px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.content-wrapper {
  max-width: 1200px;
  width: 100%;
  animation: fadeIn 1s ease-in-out;
  text-align: center;
}

.welcome-message {
  font-family: 'Montserrat', sans-serif;
  font-size: 32px;
  font-weight: bold;
  text-align: center;
  color: #333;
  margin: 20px 0 40px;
  border-bottom: 3px solid #409eff;
  padding-bottom: 10px;
  display: inline-block;
  line-height: 1.2;
}

.carousel-image {
  width: 100%;
  height: 100%;
  object-fit: contain;
  border-radius: 10px;
  transition: transform 0.3s ease-in-out;
}

.carousel-image:hover {
  transform: scale(1.05);
}

.el-card {
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  border-radius: 10px;
  transition: transform 0.3s ease-in-out, box-shadow 0.3s ease-in-out;
}

.el-card:hover {
  transform: translateY(-5px);
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
  color: #409eff;
}

@keyframes fadeIn {
  from { opacity: 0; }
  to { opacity: 1; }
}

@media (max-width: 768px) {
  .welcome-message {
    font-size: 24px;
  }

  .el-col {
    span: 24;
    margin-bottom: 20px;
  }
}

.charts-wrapper {
  display: flex;
  justify-content: space-between;
  margin-top: 20px;
}

.chart {
  width: 48%; /* 每个图表占一半宽度，留些间隙 */
  height: 400px;
}
</style>
