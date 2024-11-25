<template>
    <el-form :model="deliverForm" label-width="120px">

      <el-form-item label="广告制作需求号">
        <el-input v-model="deliverForm.requestId" disabled/>
      </el-form-item>

      <el-form-item label="名字">
        <el-input v-model="deliverForm.name" disabled/>
      </el-form-item>


      <el-form-item label="广告设计图" v-if="!route.query.id" >
    <!-- 实质headers 目的时请求头加上token，上传校验登录问题 -->
    <!-- 上传文件的action时用他自己的axios请求，所以需要设置token -->
    <!--  name="imageFile"表示上传文件的参数名称（例如：imageFile） -->
    <el-upload
    class="avatar-uploader"
    action="/api/customer/upload/image"
    name="imageFile"
    :headers="headers"
    :show-file-list="false"
    :on-success="handleAvatarSuccess"
    :before-upload="handleBeforeAvatarUpload"
  >
  <!-- 上传图片成功之后的回显 -->
    <img v-if="deliverForm.imagePath" :src="deliverForm.imagePath" class="avatar" />
    <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
  </el-upload>
    </el-form-item>

    <el-form-item label="广告设计图" width="450" v-if="route.query.id">
        <!-- el-table-column插槽:scope.row获取到一行数据 -->
 
        
        <el-image style="width: 100px; height: 100px" :src="deliverForm.imagePath"  />

    
</el-form-item>

      <el-form-item label="状态">
        <el-select v-model="deliverForm.status" >
          <el-option label="审核中" value="审核中" />
          <el-option label="已通过" value="已通过" />
          <el-option label="未通过" value="未通过" />
         
        </el-select>
      </el-form-item>
      <el-form-item label="建议">
        <el-input v-model="deliverForm.advice" type="textarea" :rows="4"  />
      </el-form-item>


      <el-form-item>
        <el-button type="primary" @click="submitForm">提交</el-button>
        <el-button @click="handleCancel">取消</el-button>
      </el-form-item>
    </el-form>
  </template>
  
  <script setup>
  import { ref, onMounted ,reactive} from 'vue';
  import { findById, save } from '@/api/deliver';
  import { useRoute, useRouter } from 'vue-router';
import { ElMessage } from 'element-plus';
import { useAdvertiserStore } from '@/stores/advertiser';

const advertiserStore=useAdvertiserStore();
const headers={
    token:advertiserStore.getToken
}
  const router = useRouter();
  const route = useRoute();
  
  const deliverForm = reactive({

  });
  
  onMounted(() => {
    if (route.query.id) {
      findById(route.query.id).then((res) => {
        deliverForm.requestId=res.data.requestId
        deliverForm.id = res.data.id
        deliverForm.name = res.data.name
        deliverForm.imagePath = res.data.imagePath
        deliverForm.status = res.data.status
        deliverForm.advice = res.data.advice
      console.log(`output->deliverForm`,deliverForm)


        
      });
    }
  });
  
  // 提交表单
  const submitForm = () => {
    save(deliverForm).then((res) => {
      router.push('/advertiser/deliver');
      if(res.state == 0){   ElMessage({

message: res.message,
type: 'success'

})
router.push('/advertiser/deliver');
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
    router.push('/advertiser/deliver');
  };

//上传页面成功
const handleAvatarSuccess=(res) => {
    console.log(`output->response`,res)
    if (res.state > 0) {
            console.log(res.message)
            ElMessage.error(res.message || "系统出错");
        
        } else if (res.state === -1) { // 未登录或者登录过期
            // to re-login
            ElMessageBox.alert('登录已经过期，请重新登录', '登录过期', {
                confirmButtonText: '重新登录',
                callback: () => {
                    //跳转登录页面
                    window.location.href = "/login";
                },
            })
        
        } else {
            //成功 正确数据
           deliverForm.imagePath=res.data
        }

}
//上传前回调函数
const handleBeforeAvatarUpload=(rawFile)=>{
    if (!(rawFile.type !== 'image/jpeg'
    ||rawFile.type !== 'image/jpg'
        || rawFile.type !== 'image/png')) {
     
    ElMessage.error('上传图片必须时jpeg,jpg,png 格式')
    return false
  
}
  else if (rawFile.size / 1024 / 1024 > 50) {
    ElMessage.error('图片大小不能超过50M!')
    return false
  }
  //放行
  return true

}

  </script>
  
  <style scoped>
  .el-form {
    max-width: 500px;
    margin: auto;
  }

  .avatar-uploader .avatar {
  width: 178px;
  height: 178px;
  display: block;
}



.avatar-uploader .el-upload {
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  text-align: center;
}
  </style>