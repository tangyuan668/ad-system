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
    <el-form-item label="头像">
      <el-upload
        class="avatar-uploader"
        action="/api/admin/upload/image"
        name="imageFile"
        :headers="headers"
        :show-file-list="false"
        :on-success="handleAvatarSuccess"
        :before-upload="handleBeforeAvatarUpload"
      >
        <img v-if="ruleForm.imagePath" :src="ruleForm.imagePath" class="avatar" />
        <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
      </el-upload>
    </el-form-item>

    <el-form-item>
      <el-button type="primary" @click="handlesubmitForm(ruleFormRef)">
        保存
      </el-button>
      <el-button @click="handleBackup">
        返回
      </el-button>
    </el-form-item>
  </el-form>
</template>

<script setup>
import { ref, onMounted, reactive } from 'vue';
import { useRoute } from 'vue-router';
import { useRouter } from 'vue-router';
import { findById, save } from '@/api/admin';
import { useAdminStore } from '@/stores/admin';
import { ElMessageBox, ElMessage } from 'element-plus';
import { Plus } from '@element-plus/icons-vue';
import { moderateImage } from '@/api/image';  // 引入审核接口

const route = useRoute();
const router = useRouter();
const adminStore = useAdminStore();

const headers = {
  token: adminStore.getToken,
};

const ruleForm = reactive({
  imagePath: '',  // 用于存储图片的路径
});

const rules = reactive({
  username: [{ required: true, message: '请输入用户名', trigger: 'blur' },
    { min: 3, max: 50, message: '用户名长度3到50', trigger: 'blur' }],
  password: [{ required: true, message: '请输入密码', trigger: 'blur' },
    { min: 3, max: 20, message: '密码长度3到20', trigger: 'blur' }],
});

const ruleFormRef = ref();

const handleBackup = () => {
  router.back();
};

const handlesubmitForm = (formEl) => {
  if (!formEl) return;
  formEl.validate((valid) => {
    if (valid) {
      save(ruleForm).then((res) => {
        ElMessage({
          message: res.message,
          type: 'success',
        });
        router.push({ path: "/admin/page" });
      });
    }
  });
};

// 上传前验证函数
const handleBeforeAvatarUpload = (rawFile) => {
  const isImage = rawFile.type === 'image/jpeg' || 
                  rawFile.type === 'image/jpg' || 
                  rawFile.type === 'image/png';

  if (!isImage) {
    ElMessage.error('上传图片必须是 jpeg, jpg, png 格式');
    return false;
  }
  if (rawFile.size / 1024 / 1024 > 50) {
    ElMessage.error('图片大小不能超过 50MB!');
    return false;
  }
  return true;
};

// 上传成功后的回调函数，调用审核接口
const handleAvatarSuccess = async (res) => {
  if (res.state > 0) {
    ElMessage.error(res.message || "系统出错");
  } else if (res.state === -1) { // 未登录或者登录过期
    ElMessageBox.alert('登录已经过期，请重新登录', '登录过期', {
      confirmButtonText: '重新登录',
      callback: () => {
        window.location.href = "login";
      },
    });
  } else {
    ruleForm.imagePath = res.data;
    // 调用审核接口
    await submitForModeration(res.data);
  }
};

// 调用审核接口的函数
const submitForModeration = async (imagePath) => {
  try {
    const data = { filePath: imagePath };  // 使用对象形式传递参数
    const response = await moderateImage(data);  // 发送审核请求
console.log(`output->response`,response)
    // 确保 response 和 response.data 存在
    if (response && response.success !== undefined) {
        if (response.success) {
            ElMessage.success('图片审核通过！');
        } else {
            ElMessage.error(response.message || '图片审核未通过，请更换图片重试。');
            ruleForm.imagePath = '';  // 如果审核不通过，清空已上传图片路径
        }
    } else {
        ElMessage.error('服务器未返回有效数据，请稍后重试。');
    }
} catch (error) {
    console.error('审核请求失败:', error);
    ElMessage.error('审核请求失败，请检查控制台');
}
}
onMounted(() => {
  ruleForm.id = route.query.id;
  if (ruleForm.id) {
    findById(ruleForm.id).then((res) => {
      ruleForm.username = res.data.username;
      ruleForm.imagePath = res.data.imagePath;
      ruleForm.id = res.data.id;
    });
  }
});
</script>

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
