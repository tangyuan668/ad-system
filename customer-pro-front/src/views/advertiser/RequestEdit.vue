<script setup>
import{ref,onMounted,reactive} from 'vue';
import { useRoute } from 'vue-router';
import { useRouter } from 'vue-router';
import {findById,save}  from '@/api/request';
import { useAdvertiserStore } from '@/stores/advertiser';
import { ElMessageBox, ElMessage } from 'element-plus'
import{emailSave} from '@/api/email'
import { useCountStore } from '@/stores/count';
const route=useRoute()
const router=useRouter()
const advertiserStore = useAdvertiserStore()
let num=0
const emailForm=reactive({customerId:'',
content:'',
imagePath:'',
status:'',
type:''


})
//设置请求头,token和对应的值
const headers={
    token:advertiserStore.getToken
}
//onMounted猴子函数 获取id值
onMounted(() =>{
  if(route.query.id)
{ ruleForm.id=route.query.id

}else{
ruleForm.id=0

}
//console.log(`AdvertiserSaveView`,id)
//if(变量名称) 判断变量存不存在，存在就返回true
ruleForm.advertiserId = advertiserStore.advertiser.id
if(ruleForm.id){
//更新
findById(ruleForm.id).then((res) =>{
ruleForm.title = res.data.title
ruleForm.id = res.data.id
ruleForm.description = res.data.description
ruleForm.budget=res.data.budget
ruleForm.status=res.data.status
ruleForm.customerId=res.data.customerId
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
    if(num==1)
    {
      emailForm.content='您有一条需求已被接取'
emailForm.imagePath='src/images/th (1).jpg'
emailForm.status='未读'
emailForm.type='系统消息'
emailForm.customerId=ruleForm.customerId
useCountStore().count.num++
useCountStore().count.cuid=ruleForm.customerId
emailSave(emailForm)
num=0
    }
    //跳转到AdvertiserPageView页面
    router.push({path:"/advertiser/request"})
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
                    window.location.href = "/advertiser/login";
                },
            })
        
        } else {
            //成功 正确数据
           ruleForm.imagePath=res.data
        }

}

const changeStatus = (value) => {

if(value=='已接取')
{
 /*  const emailForm=reactive({customerId:'',
content:'',
imagePath:'',
status:'',
type:'', */
num=1

}
if(value=='未接取')
{
num=0

}

};

</script>

<template>
    <h1>AdvertiserSaveView</h1>
    <el-form
    ref="ruleFormRef"
    style="max-width: 600px"
    :model="ruleForm"
    :rules="rules"
    label-width="auto"
    status-icon
  >
    <el-form-item label="标题" prop="title">
      <el-input v-model="ruleForm.title"  disabled/>
    </el-form-item>
    <el-form-item label="预算" prop="budget" >
        <el-input
    v-model="ruleForm.budget" disabled
  />
</el-form-item>

    <el-form-item label="详细">
        <el-input v-model="ruleForm.description" type="textarea" :rows="4" disabled />
      </el-form-item>
      <el-form-item label="状态">
        <el-select v-model="ruleForm.status" @change="changeStatus">
          <el-option label="已接取" value="已接取" />
          <el-option label="未接取" value="未接取" />
        </el-select>
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


