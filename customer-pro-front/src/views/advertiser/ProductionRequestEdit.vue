<template>
    <el-form :model="productionRequestForm" label-width="120px">

      <el-form-item label="提案号">
        <el-input v-model="productionRequestForm.proposalId" />
      </el-form-item>
      <el-form-item label="制作者号">
        <el-input v-model="productionRequestForm.adproducerId" />
      </el-form-item>
      <el-form-item label="标题">
        <el-input v-model="productionRequestForm.name" />
      </el-form-item>
      <el-form-item label="提案内容">
        <el-input v-model="productionRequestForm.content" type="textarea" :rows="4" />
      </el-form-item>
      <el-form-item label="截至日期">
  <el-date-picker
    v-model="productionRequestForm.deadTime"
    type="datetime"
    placeholder="选择结算日期"
    format="YYYY-MM-DD HH:mm:ss"
    value-format="YYYY-MM-DD HH:mm:ss"
  />
</el-form-item>


      <el-form-item>
        <el-button type="primary" @click="submitForm">提交</el-button>
        <el-button @click="handleCancel">取消</el-button>
      </el-form-item>
    </el-form>
  </template>
  
  <script setup>
  import { ref, onMounted ,reactive} from 'vue';
  import { findById, save } from '@/api/productionRequest';
  import { useRoute, useRouter } from 'vue-router';
import { ElMessage } from 'element-plus';
  
  const router = useRouter();
  const route = useRoute();
  
  const productionRequestForm = reactive({

  });
  
  onMounted(() => {
    if (route.query.id) {
      findById(route.query.id).then((res) => {
        productionRequestForm.proposalId=res.data.proposalId
        productionRequestForm.id = res.data.id
        productionRequestForm.adproducerId = res.data.adproducerId
        productionRequestForm.name = res.data.name
        productionRequestForm.content = res.data.content
productionRequestForm.deadTime=res.data.deadTime
      console.log(`output->productionRequestForm`,productionRequestForm)


        
      });
    }
  });
  
  // 提交表单
  const submitForm = () => {
    save(productionRequestForm).then((res) => {
      router.push('/advertiser/productionRequest');
      if(res.state == 0){   ElMessage({

message: res.message,
type: 'success'

})
router.push('/advertiser/productionRequest');
}
else{
  ElMessage({

message: res.message,
type: 'fail'

})

}


   
    });
  };
  
  // 取消操作
  const handleCancel = () => {
    router.push('/advertiser/productionRequest');
  };
  </script>
  
  <style scoped>
  .el-form {
    max-width: 500px;
    margin: auto;
  }
  </style>