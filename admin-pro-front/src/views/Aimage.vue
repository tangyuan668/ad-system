<template>
  <div>
    <el-form-item label="头像">
      <el-upload
        class="avatar-uploader"
        action="/api/admin/upload/image"
        name="imageFile" 
        :show-file-list="false"
        :on-success="handleAvatarSuccess" 
        :before-upload="handleBeforeAvatarUpload"  
      >
        <!-- 成功上传后的图片回显 -->
        <img v-if="ruleForm.imagePath" :src="ruleForm.imagePath" class="avatar" />
        <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
      </el-upload>
    </el-form-item>

    <div v-if="result">
      <h3>审核结果</h3>
      <pre>{{ result }}</pre>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive } from 'vue';
import { ElMessage } from 'element-plus';
import { moderateImage } from '@/api/image';
import { Plus } from '@element-plus/icons-vue';

// 定义响应式数据
const ruleForm = reactive({
  imagePath: '',  // 用于存储图片的路径
});



const result = ref(null);  // 存储审核结果

// 定义上传前的验证函数
const handleBeforeAvatarUpload = (file) => {
  const isImage = file.type.startsWith('image/');
  const isLt2M = file.size / 1024 / 1024 < 20; // 确保文件大小限制为 2MB

  if (!isImage) {
    ElMessage.error('上传的文件必须是图片!');
  }
  if (!isLt2M) {
    ElMessage.error('上传的图片大小不能超过 2MB!');
  }
  return isImage && isLt2M;
};

// 定义上传成功的回调函数
const handleAvatarSuccess = async (response) => {
  // 处理上传成功后的逻辑，这里假设服务器返回了图片的访问路径
  if (response.code === 200) {
    ruleForm.imagePath = response.data.imagePath;  // 设置图片显示路径
    await submitForModeration(response.data.imagePath);  // 调用审核接口
  } else {
    ElMessage.error('上传失败，请重试');
  }
};

// 定义调用审核接口的函数
const submitForModeration = async (imagePath) => {
  try {
    const formData = new FormData();
    formData.append('imagePath', imagePath);  // 假设后端需要图片路径
    const response = await moderateImage(formData);  // 发送审核请求
    result.value = response.data;  // 显示审核结果
  } catch (error) {
    console.error('审核请求失败:', error);
    ElMessage.error('审核请求失败，请检查控制台');
  }
};
</script>

<style scoped>
.avatar-uploader {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 128px;
  height: 128px;
  border: 1px dashed #d9d9d9;
  border-radius: 50%;
  overflow: hidden;
  cursor: pointer;
}
.avatar {
  width: 100%;
  height: 100%;
}
</style>
