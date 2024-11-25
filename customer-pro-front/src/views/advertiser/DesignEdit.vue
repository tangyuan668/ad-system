 <template>
    <el-form :model="designForm" label-width="120px">
        <el-form-item label="需求号">
        <el-input v-model="designForm.requestId" />
      </el-form-item>
      <el-form-item label="设计名字">
        <el-input v-model="designForm.name" />
      </el-form-item>
      <el-form-item label="设计图" width="600">
        <el-image style="width: 100px; height: 100px" :src="designForm.imagePath"  />
</el-form-item>
      <el-form-item label="状态">
        <el-select v-model="designForm.status">
          <el-option label="审核中" value="审核中" />
          <el-option label="已通过" value="已通过" />
          <el-option label="未通过" value="未通过" />
         
        </el-select>
      </el-form-item>
      <el-form-item label="建议">
        <el-input v-model="designForm.advice" type="textarea" :rows="4" />
      </el-form-item>


      <el-form-item>
       <!--  <el-button type="primary" @click="submitForm">提交</el-button> -->
        <el-button @click="handleCancel">返回</el-button>
      </el-form-item>
    </el-form>
  </template>
  
  <script setup>
  import { ref, onMounted ,reactive} from 'vue';
  import { findById, save } from '@/api/design';
  import { useRoute, useRouter } from 'vue-router';
  
  const router = useRouter();
  const route = useRoute();
  
  const designForm = reactive({

  });
  
  onMounted(() => {
    if (route.query.id) {
      findById(route.query.id).then((res) => {
        console.log('API response:', res.data);
        designForm.id = res.data.id
        designForm.requestId = res.data.requestId
        designForm.name = res.data.name
        designForm.status = res.data.status
        designForm.imagePath= res.data.imagePath
        designForm.advice = res.data.advice
      console.log(`output->designForm`,designForm)


        
      });
    }
  });
  
  // 提交表单
  const submitForm = () => {
    save(designForm).then(() => {
      router.push('/advertiser/design');
    });
  };
  
  // 取消操作
  const handleCancel = () => {
    router.push('/advertiser/design');
  };
  </script>
  
  <style scoped>
  .el-form {
    max-width: 500px;
    margin: auto;
  }
  </style> 



 