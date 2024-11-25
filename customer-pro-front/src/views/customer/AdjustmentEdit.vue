<template>
    <el-form :model="adjustmentForm" label-width="120px">
        <el-form-item label="设计号">
        <el-input v-model="adjustmentForm.designId" disabled />
      </el-form-item>
      <el-form-item label="调整标题">
        <el-input v-model="adjustmentForm.name" disabled />
      </el-form-item>
      <el-form-item label="内容">
        <el-input v-model="adjustmentForm.content" type="textarea" :rows="4" disabled />
      </el-form-item>

      <el-form-item label="状态">
        <el-select v-model="adjustmentForm.status">
          <el-option label="审核中" value="审核中" />
          <el-option label="已通过" value="已通过" />
          <el-option label="未通过" value="未通过" />
         
        </el-select>
      </el-form-item>



      <el-form-item>
        <el-button type="primary" @click="submitForm">提交</el-button>
        <el-button @click="handleCancel">返回</el-button>
      </el-form-item>
    </el-form>
  </template>
  
  <script setup>
  import { ref, onMounted ,reactive} from 'vue';
  import { findById, save } from '@/api/adjustment';
  import { useRoute, useRouter } from 'vue-router';
  
  const router = useRouter();
  const route = useRoute();
  
  const adjustmentForm = reactive({

  });
  
  onMounted(() => {
    if (route.query.id) {
      findById(route.query.id).then((res) => {
  
        adjustmentForm.id = res.data.id
        adjustmentForm.designId = res.data.designId
        adjustmentForm.name = res.data.name 
        adjustmentForm.status = res.data.status
        adjustmentForm.content = res.data.content


        
      });
    }
  });
  
  // 提交表单
  const submitForm = () => {
    save(adjustmentForm).then(() => {
      router.push('/customer/adjustment');
    });
  };
  
  // 取消操作
  const handleCancel = () => {
    router.push('/customer/adjustment');
  };
  </script>
  
  <style scoped>
  .el-form {
    max-width: 500px;
    margin: auto;
  }
  </style> 



 