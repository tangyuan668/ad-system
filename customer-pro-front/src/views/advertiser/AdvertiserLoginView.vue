<template>

  <div class="auth-container">
      <el-card class="auth-card" shadow="hover">
        <div class="auth-header">
          <h2>{{ isLogin ? '登录' : '注册' }}</h2>
        </div>
        <el-form
          v-if="isLogin"
          ref="loginFormRef"
          :model="loginForm"
          :rules="loginRules"
          label-width="80px"
          status-icon
        >
          <el-form-item label="用户名" prop="username">
            <el-input v-model="loginForm.username" placeholder="请输入用户名" />
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input
              v-model="loginForm.password"
              type="password"
              placeholder="请输入密码"
              show-password
            />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" block @click="submitLoginForm(loginFormRef)">
              登录
            </el-button>
          </el-form-item>
          <el-form-item>
            <span @click="toggleForm" class="toggle-link">没有账号？立即注册</span>
          </el-form-item>
        </el-form>
  
        <el-form
          v-else
          ref="registerFormRef"
          :model="registerForm"
          :rules="registerRules"
          label-width="80px"
          status-icon
        >
          <el-form-item label="用户名" prop="username">
            <el-input v-model="registerForm.username" placeholder="请输入用户名" />
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input
              v-model="registerForm.password"
              type="password"
              placeholder="请输入密码"
              show-password
            />
          </el-form-item>
          <el-form-item label="确认密码" prop="confirmPassword">
            <el-input
              v-model="registerForm.confirmPassword"
              type="password"
              placeholder="请确认密码"
              show-password
            />
          </el-form-item>
          <el-form-item label="验证码" prop="captcha">
    <el-input v-model="registerForm.captcha" placeholder="请输入验证码">
      <template #append>
        <span class="captcha-box">{{ generatedCaptcha }}</span>
        <el-button @click="generateCaptcha" size="mini" class="refresh-captcha">刷新</el-button>
      </template>
    </el-input>
  </el-form-item>
          
          <el-form-item>
            <el-button type="primary" block @click="submitRegisterForm(registerFormRef)">
              注册
            </el-button>
          </el-form-item>
          <el-form-item>
            <span @click="toggleForm" class="toggle-link">已有账号？立即登录</span>
          </el-form-item>
        </el-form>
      </el-card>
    </div>
  </template>
  
  <script setup>
  import axios from 'axios';
  import {login} from '@/api/advertiser';
  import { reactive,ref } from 'vue';
  //导入userAdvertiserStore函数
  import { useAdvertiserStore} from '@/stores/advertiser';
  //导入userRouter函数
  import { useRouter } from 'vue-router';
  import { register } from '@/api/advertiser';
  //获取advertiserStore对象
  const advertiserStore=useAdvertiserStore()
  //获取router对象
  const router=useRouter()
  const loginForm=reactive({
      username:'',
      password:''
  })
  const isLogin = ref(true)
  const loginRules=reactive({
  username:[{ required: true, message: '请输入用户名', trigger: 'blur' },
  { min: 3, max: 50, message: '用户名长度3到50', trigger: 'blur' }],
  password:[{ required: true, message: '请输入密码', trigger: 'blur' },
  { min: 3, max: 20, message: '密码长度3到20', trigger: 'blur' }]
  
  })
  //获取到el-form元素的原生的DOM对象
  const loginFormRef = ref()
  const registerFormRef=ref()
  const submitLoginForm =  (formEl) => {
    if (!loginFormRef.value) return
    //el-form元素的原生的DOM对象，然后进行验证，validate是DOM对象的函数
    loginFormRef.value.validate((valid, fields) => {
      if (valid) {
        console.log('submit!')
        //axios把用户名和密码请求后端api
        login(loginForm).then((res) => {
          console.log('output->ref',res)
          //后端返回的token数据赋值全局变量中
          advertiserStore.saveToken(res.message)
          //保存advertiser信息
          advertiserStore.saveAdvertiser(res.data)
          //跳转/home页面
          router.push({
              path:"/advertiser/main"
          }).then(() => {
        // 跳转完成后刷新页面
        window.location.reload();
    }).catch(err => {
        console.error(err);
    });
        })
      } else {
        console.log('error submit!', fields)
      }
    })
  }
  const registerForm = ref({
    username: '',
    password: '',
    confirmPassword: '',
    captcha: ''
  })
  
  const registerRules = {
    username: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
    password: [{ required: true, message: '请输入密码', trigger: 'blur' }],
    confirmPassword: [
      { required: true, message: '请确认密码', trigger: 'blur' },
      { validator: validateConfirmPassword, trigger: 'blur' }
    ],
    captcha: [{ required: true, message: '请输入验证码', trigger: 'blur' }]
  };
  function validateConfirmPassword(rule, value, callback) {
    if (value !== registerForm.value.password) {
      callback(new Error('两次输入的密码不一致'))
    } else {
      callback()
    }
  }
  const generatedCaptcha = ref(''); // 静态验证码，实际应从服务器获取
  
  const generateCaptcha = () => {
    // 生成一个4位随机数验证码
    generatedCaptcha.value = Math.random().toString(36).substring(2, 6).toUpperCase();
  };
  const submitRegisterForm = (formRef) => {
    formRef.validate((valid) => {
      if (valid) {
        if (registerForm.value.captcha !== generatedCaptcha.value) {
          alert('验证码错误');
          return;
        }
        const registerForm2 = reactive({username:registerForm.value.username, password:registerForm.value.password})
        console.log(`output->registerForm2`,registerForm2)
        register(registerForm2)
        toggleForm(); 
        alert('注册成功');
        
  
      } else {
        console.log('注册表单有误');
        return false;
      }
    });
  
  
  };
  const toggleForm = () => {
    isLogin.value = !isLogin.value
  }
  // 页面加载时生成初始验证码
  generateCaptcha();
  </script>
  
  <style scoped>
  .auth-container {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    background-color: #f5f5f5;
  }
  
  .auth-card {
    max-width: 400px;
    width: 100%;
    padding: 20px;
  }
  
  .auth-header {
    text-align: center;
    margin-bottom: 20px;
  }
  
  .toggle-link {
    color: #409eff;
    cursor: pointer;
    text-align: center;
    display: block;
    margin-top: 10px;
  }
  .captcha-box {
    background-color: #f2f2f2;
    padding: 5px 10px;
    border-radius: 4px;
    font-weight: bold;
    letter-spacing: 2px;
    display: inline-block;
    margin-right: 10px; /* 添加一些右边距 */
  }
  
  .refresh-captcha {
    margin-left: 10px;
  }
  </style>