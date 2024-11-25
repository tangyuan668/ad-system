<script setup>

import{ref,onMounted,reactive} from 'vue';
import { useRoute } from 'vue-router';
import { useRouter } from 'vue-router';
import {findById,save}  from '@/api/adproducer';
import { useAdProducerStore } from '@/stores/adproducer';
import { ElMessageBox, ElMessage } from 'element-plus'
const route=useRoute()
const router=useRouter()
const adproducerStore = useAdProducerStore()
//设置请求头,token和对应的值
const headers={
    token:adproducerStore.getToken
}
//onMounted猴子函数 获取id值
onMounted(() =>{
 ruleForm.id=route.query.id
//console.log(`AdProducerSaveView`,id)
//if(变量名称) 判断变量存不存在，存在就返回true
if(ruleForm.id){
//更新
findById(ruleForm.id).then((res) =>{
ruleForm.username = res.data.username
ruleForm.imagePath = res.data.imagePath
ruleForm.id = res.data.id

})
}
else{


}
})
const ruleForm=reactive({
})
const rules=reactive({

username:[{ required: true, message: '请输入用户名', trigger: 'blur' },
{ min: 3, max: 50, message: '用户名长度3到50', trigger: 'blur' }],
password:[{ required: true, message: '请输入密码', trigger: 'blur' },
{ min: 3, max: 20, message: '密码长度3到20', trigger: 'blur' }]

})
//获取到el-form元素的原生的DOM对象
const ruleFormRef = ref()

//返回事件回调函数
const handleBackup=()=>{
router.back()
}


const handlesubmitForm =  (formEl) => {
  if (!formEl) return
  //el-form元素的原生的DOM对象，然后进行验证，validate是DOM对象的函数
 formEl.validate((valid, fields) => {
    if (valid) {
      console.log('submit!')
      //axios把用户名和密码请求后端api
console.log(`output->ruleForm`,ruleForm)
//请求后端api(新增和更新)
save(ruleForm).then((res)=>{

    //显示成功信息
    ElMessage({

        message: res.message,
        type:'success'
    })
    //跳转到AdProducerPageView页面
    router.push({path:"/adproducer/page"})
})
    } else {
      console.log('error submit!', fields)
    }
  })
}
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
           ruleForm.imagePath=res.data
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

<template>
    <h1>AdProducerSaveView</h1>
    <el-form
    ref="ruleFormRef"
    style="max-width: 600px"
    :model="ruleForm"
    :rules="rules"
    label-width="auto"
    status-icon
  >
    <el-form-item label="用户名" prop="username">
      <el-input v-model="ruleForm.username" />
    </el-form-item>
    <el-form-item label="密码" prop="password" v-if="!ruleForm.id">
        <el-input
    v-model="ruleForm.password"
    type="password"
    show-password
  />
</el-form-item>
  <el-form-item label="头像" >
    <!-- 实质headers 目的时请求头加上token，上传校验登录问题 -->
    <!-- 上传文件的action时用他自己的axios请求，所以需要设置token -->
    <!--  name="imageFile"表示上传文件的参数名称（例如：imageFile） -->
    <el-upload
    class="avatar-uploader"
    action="/api/adproducer/upload/image"
    name="imageFile"
    :headers="headers"
    :show-file-list="false"
    :on-success="handleAvatarSuccess"
    :before-upload="handleBeforeAvatarUpload"
  >
  <!-- 上传图片成功之后的回显 -->
    <img v-if="ruleForm.imagePath" :src="ruleForm.imagePath" class="avatar" />
    <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
  </el-upload>
    </el-form-item>
    
    <el-form-item>
      <el-button type="primary" @click="handlesubmitForm(ruleFormRef)">
       保存
      </el-button>
      <el-button  @click="handleBackup">
       返回
      </el-button>
    </el-form-item>
</el-form>




</template>

<style scoped>

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


