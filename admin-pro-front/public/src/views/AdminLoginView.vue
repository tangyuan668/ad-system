<template>

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
    <el-form-item label="密码" prop="password">
        <el-input
    v-model="ruleForm.password"
    type="password"
    show-password
  />
    </el-form-item>
    
    <el-form-item>
      <el-button type="primary" @click="submitForm(ruleFormRef)">
        登录
      </el-button>
    </el-form-item>
</el-form>
</template>

<script setup>
import axios from 'axios';
import {login} from '@/api/admin';
import { reactive,ref } from 'vue';
//导入userAdminStore函数
import { useAdminStore} from '@/stores/admin';
//导入userRouter函数
import { useRouter } from 'vue-router';
//获取adminStore对象
const adminStore=useAdminStore()
//获取router对象
const router=useRouter()
const ruleForm=reactive({
    username:'',
    password:''
})
const rules=reactive({

username:[{ required: true, message: '请输入用户名', trigger: 'blur' },
{ min: 3, max: 50, message: '用户名长度3到50', trigger: 'blur' }],
password:[{ required: true, message: '请输入密码', trigger: 'blur' },
{ min: 3, max: 20, message: '密码长度3到20', trigger: 'blur' }]

})
//获取到el-form元素的原生的DOM对象
const ruleFormRef = ref()
const submitForm =  (formEl) => {
  if (!formEl) return
  //el-form元素的原生的DOM对象，然后进行验证，validate是DOM对象的函数
 formEl.validate((valid, fields) => {
    if (valid) {
      console.log('submit!')
      //axios把用户名和密码请求后端api
      login(ruleForm).then((res) => {
        console.log('output->ref',res)
        //后端返回的token数据赋值全局变量中
        adminStore.saveToken(res.message)
        //保存admin信息
        adminStore.saveAdmin(res.data)
        //跳转/home页面
        router.push({
            path:"/admin/main"
        })
      })
    } else {
      console.log('error submit!', fields)
    }
  })
}

</script>

<style scoped>

</style>