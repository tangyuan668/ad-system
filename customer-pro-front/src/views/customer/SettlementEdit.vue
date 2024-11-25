<template>
    <el-form :model="settlementForm" label-width="120px">
        <el-form-item label="签署方案号">
        <el-input v-model="settlementForm.signId" />
      </el-form-item>
      <el-form-item label="标题">
        <el-input v-model="settlementForm.name" />
      </el-form-item>
      <el-form-item label="内容">
        <el-input v-model="settlementForm.content" type="textarea" :rows="4" />
      </el-form-item>
      <el-form-item label="账单" v-if="route.query.id">
        <el-input v-model="settlementForm.amout" disabled />
      </el-form-item>

      <el-form-item label="状态">
        <el-select v-model="settlementForm.status" @change="changeStatus">
          <el-option label="已支付" value="已支付" />
          <el-option label="未支付" value="未支付" />
          
         
        </el-select>
      </el-form-item>


      <el-form-item label="结算日期">
  <el-date-picker
    v-model="settlementForm.paymentDate"
    type="datetime"
    placeholder="选择结算日期"
    format="YYYY-MM-DD HH:mm:ss"
    value-format="YYYY-MM-DD HH:mm:ss"
    :disabled="isPaymentDateDisabled"
  />
</el-form-item>



      <el-form-item>
        <el-button type="primary" @click="submitForm" >提交</el-button>
        <el-button @click="handleCancel">返回</el-button>
      </el-form-item>
    </el-form>
  </template>
  
  <script setup>
  import { ref, onMounted ,reactive,watch} from 'vue';
  import { findById, save,updatePaymentTime ,update} from '@/api/settlement';
  import { useRoute, useRouter } from 'vue-router';
  
  const router = useRouter();
  const route = useRoute();
  let isPaymentDateDisabled = ref(false)
  const settlementForm = reactive({

  });
  
  onMounted(() => {



    if (route.query.id) {
      findById(route.query.id).then((res) => {
  
        settlementForm.id = res.data.id
        settlementForm.signId = res.data.signId
        settlementForm.name = res.data.name 
        settlementForm.content = res.data.content
settlementForm.paymentDate= res.data.paymentDate
settlementForm.amout= res.data.amout
settlementForm.status=res.data.status

if (settlementForm.status== '未支付') {

settlementForm.paymentDate = ''; // 清空结算日期
isPaymentDateDisabled.value = true;   // 禁用结算日期选择
}else{
    isPaymentDateDisabled.value = false;  // 启用结算日期选择

}



        
      });
    }
  });
  
  // 提交表单
  const submitForm = () => {
    save(settlementForm).then(() => {


        if(route.query.id)
  {
    if (settlementForm.status === '未支付') {
       
        // 在这里处理 "未支付" 状态时的操作
updatePaymentTime(settlementForm.id)
findById(route.query.id).then((res) => {
  

settlementForm.paymentDate= res.data.paymentDate


  
});

      }
  }

update()





      router.push('/customer/settlement');
    });
  };
  
  // 取消操作
  const handleCancel = () => {
    router.push('/customer/settlement');
  };

  const changeStatus = (value) => {



    if (value== '未支付') {

    settlementForm.paymentDate = ''; // 清空结算日期
    isPaymentDateDisabled.value = true;   // 禁用结算日期选择
    }else{
        isPaymentDateDisabled.value = false;  // 启用结算日期选择

    }




    
  };


  </script>
  
  <style scoped>
  .el-form {
    max-width: 500px;
    margin: auto;
  }
  </style> 

