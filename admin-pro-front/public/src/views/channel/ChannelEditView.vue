<script setup>

import{ref,onMounted,reactive} from 'vue';
import { useRoute } from 'vue-router';
import { useRouter } from 'vue-router';
import {findById,save}  from '@/api/channel';
import { useAdminStore } from '@/stores/admin';
import { ElMessageBox, ElMessage } from 'element-plus'
const route=useRoute()
const router=useRouter()
const adminStore = useAdminStore()
//设置请求头,token和对应的值
const headers={
    token:adminStore.getToken
}
//onMounted猴子函数 获取id值
onMounted(() =>{
  if(route.query.id)
{ ruleForm.id=route.query.id

}else{
ruleForm.id=0

}
//console.log(`AdminSaveView`,id)
//if(变量名称) 判断变量存不存在，存在就返回true
if(ruleForm.id){
//更新
findById(ruleForm.id).then((res) =>{
ruleForm.name = res.data.name
ruleForm.id = res.data.id
ruleForm.type = res.data.type
ruleForm.description = res.data.description
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
    //跳转到AdminPageView页面
    router.push({path:"/channel/page"})
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
                    window.location.href = "/admin/login";
                },
            })
        
        } else {
            //成功 正确数据
           ruleForm.imagePath=res.data
        }

}



</script>

<template>
    <h1>AdminSaveView</h1>
    <el-form
    ref="ruleFormRef"
    style="max-width: 600px"
    :model="ruleForm"
    :rules="rules"
    label-width="auto"
    status-icon
  >
    <el-form-item label="名字" prop="name">
      <el-input v-model="ruleForm.name" />
    </el-form-item>
    <el-form-item label="类型" prop="type" >
        <el-input
    v-model="ruleForm.type"
  />
</el-form-item>
<el-form-item label="详情" prop="description">
      <el-input v-model="ruleForm.description" />
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


