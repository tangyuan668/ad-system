<template>
    <el-form :model="proposalForm" label-width="120px">

      <el-form-item label="需求号">
        <el-input v-model="proposalForm.requesetId" />
      </el-form-item>

      <el-form-item label="提案名字">
        <el-input v-model="proposalForm.name" />
      </el-form-item>
      <el-form-item label="提案内容">
        <el-input v-model="proposalForm.content" type="textarea" :rows="4" />
      </el-form-item>
      <el-form-item label="预算">
        <el-input-number v-model="proposalForm.budget" />
      </el-form-item>
      <el-form-item label="状态">
        <el-select v-model="proposalForm.status" disabled>
          <el-option label="审核中" value="审核中" />
          <el-option label="已通过" value="已通过" />
          <el-option label="未通过" value="未通过" />
         
        </el-select>
      </el-form-item>
      <el-form-item label="建议">
        <el-input v-model="proposalForm.advice" type="textarea" :rows="4" disabled />
      </el-form-item>


      <el-form-item>
        <el-button type="primary" @click="submitForm">提交</el-button>
        <el-button @click="handleCancel">取消</el-button>
      </el-form-item>
    </el-form>
  </template>
  
  <script setup>
  import { ref, onMounted ,reactive} from 'vue';
  import { findById, save } from '@/api/proposal';
  import { useRoute, useRouter } from 'vue-router';
import { ElMessage } from 'element-plus';
  
  const router = useRouter();
  const route = useRoute();
  
  const proposalForm = reactive({

  });
  
  onMounted(() => {
    if (route.query.id) {
      findById(route.query.id).then((res) => {
        proposalForm.requesetId=res.data.requesetId
        proposalForm.id = res.data.id
        proposalForm.name = res.data.name
        proposalForm.content = res.data.content
        proposalForm.budget = res.data.budget
        proposalForm.status = res.data.status
        proposalForm.advice = res.data.advice
      console.log(`output->proposalForm`,proposalForm)


        
      });
    }
  });
  
  // 提交表单
  const submitForm = () => {
    save(proposalForm).then((res) => {
      router.push('/advertiser/proposal');
      if(res.state == 0){   ElMessage({

message: res.message,
type: 'success'

})
router.push({path:"/advertiser/proposal/edit"})


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
    router.push('/advertiser/proposal');
  };
  </script>
  
  <style scoped>
  .el-form {
    max-width: 500px;
    margin: auto;
  }
  </style>