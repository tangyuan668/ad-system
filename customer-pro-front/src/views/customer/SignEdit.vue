<template>
  <div class="form-container">
    <!-- 左侧表单部分 -->
    <el-form :model="signForm" label-width="120px" class="form-content">
      <el-form-item label="提案号">
        <el-input v-model="signForm.proposalId" />
      </el-form-item>
      <el-form-item label="标题">
        <el-input v-model="signForm.name" />
      </el-form-item>
      <el-form-item label="内容">
        <el-input v-model="signForm.content" type="textarea" :rows="4" />
      </el-form-item>

      <el-form-item label="签署日期">
        <el-date-picker
          v-model="signForm.date"
          type="datetime"
          placeholder="选择签署日期"
          format="YYYY-MM-DD HH:mm:ss"
          value-format="YYYY-MM-DD HH:mm:ss"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm" v-if="!route.query.id">提交</el-button>
        <el-button @click="handleCancel">返回</el-button>
      </el-form-item>
    </el-form>

    <!-- 右侧签名板部分 -->
    <div class="signature-content">
      <!-- 客户签名或签名板 -->
      <el-form-item v-if="!route.query.id" label="客户签名">
        <div class="signature-container">
          <vue-signature-pad
            ref="signaturePad"
            :options="signaturePadOptions"
            @end="onSignatureEnd"
            class="vue-signature-pad"
          />
          <el-button type="primary" @click="clearSignature" class="clear-button">清除签名</el-button>
        </div>
      </el-form-item>

      <el-form-item v-else label="客户签字">
        <div class="signature-container">
          <el-image
            class="signature-image"
            :src="signForm.customerSignature"
            fit="contain"
            alt="客户签名"
          />
        </div>
      </el-form-item>

      <!-- 广告商签名图片 -->
      <el-form-item label="广告商签名">
        <div class="signature-container">
          <el-image
            class="signature-image"
            :src="signForm.advertiserSignature"
            fit="contain"
            alt="广告商签名"
          />
        </div>
      </el-form-item>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, reactive } from 'vue';
import { findById, save } from '@/api/sign';
import { useRoute, useRouter } from 'vue-router';
import { VueSignaturePad } from 'vue-signature-pad'; // 导入 vue-signature-pad 组件
import { useCustomerStore } from '@/stores/customer';
import axios from 'axios';
import { ElMessage } from 'element-plus';
const router = useRouter();
const route = useRoute();

const signForm = reactive({});

onMounted(() => {
  if (route.query.id) {
    findById(route.query.id).then((res) => {
      signForm.id = res.data.id;
      signForm.proposalId = res.data.proposalId;
      signForm.name = res.data.name;
      signForm.content = res.data.content;
      signForm.customerSignature = res.data.customerSignature;
      signForm.advertiserSignature = res.data.advertiserSignature;
      signForm.date = res.data.date;
    });
  }
});

// 提交表单
const submitForm = () => {
  uploadSignature();
};

// 取消操作
const handleCancel = () => {
  router.push('/customer/sign');
};

// 签名板选项
const signaturePadOptions = {
  penColor: 'rgb(0, 0, 0)',
};

// 获取签名板的引用
const signaturePad = ref(null);

// 清除签名
const clearSignature = () => {
  if (signaturePad.value) {
    signaturePad.value.clearSignature();
  }
};

const uploadSignature = () => {
  const base64Signature = signaturePad.value.saveSignature().data;

  if (typeof base64Signature !== 'string') {
    console.error('签名数据无效:', base64Signature);
    return; // 如果数据无效，退出函数
  }

  const signatureBlob = base64ToBlob(base64Signature, 'image/png');

  const formData = new FormData();
  formData.append('imageFile', signatureBlob, 'signature.png'); // 注意这里的参数名改为 'imageFile'

  const headers = {
    'token': useCustomerStore().getToken,
  };

  axios.post('/aapi/admin/upload/image', formData, {
    headers: headers,
  })
  .then(response => {
    console.log('上传成功:', response.data.data);
    signForm.customerSignature = response.data.data; // 将图片路径赋值给 signForm.customerSignature
    save(signForm).then((res) => {




    if(res.state == 0){   ElMessage({

message: res.message,
type: 'success'

})
router.push('/customer/sign');
}
else{
  ElMessage({

message: res.message,
type: 'fail'

})

}







    });
  })
  .catch(error => {
    console.error('上传失败:', error);
  });
};

// Base64 转 Blob 函数
function base64ToBlob(base64, mime) {
  mime = mime || '';
  const base64Data = base64.split(',')[1]; // 仅提取 Base64 数据部分
  const sliceSize = 1024;
  const byteChars = atob(base64Data);
  const byteArrays = [];

  for (let offset = 0; offset < byteChars.length; offset += sliceSize) {
    const slice = byteChars.slice(offset, offset + sliceSize);
    const byteNumbers = new Array(slice.length);
    for (let i = 0; i < slice.length; i++) {
      byteNumbers[i] = slice.charCodeAt(i);
    }
    const byteArray = new Uint8Array(byteNumbers);
    byteArrays.push(byteArray);
  }

  return new Blob(byteArrays, { type: mime });
}
</script>

<style scoped>
.form-container {
  display: flex;
  justify-content: space-between;
  gap: 20px; /* 设置左右部分的间距 */
  width: 100%; /* 确保容器占满全宽 */
}

.form-content {
  flex: 1; /* 表单部分占据较少空间 */
  max-width: 500px;
  margin: auto;
}

.signature-content {
  flex: 1.5; /* 调整签名板部分的空间比例，缩小一点 */
  display: flex;
  flex-direction: column;
  gap: 20px; /* 增加签名框之间的间距 */
}

.signature-container {
  display: flex;
  flex-direction: column;
  align-items: flex-start; /* 调整按钮的对齐方式 */
  width: 100%; /* 确保容器宽度一致 */
}

.vue-signature-pad {
  border: 1px solid #c81d1d;
  width: 100%;
  height: 300px; /* 调整高度，缩小一点 */
  object-fit: contain; /* 确保内容适应容器 */
}

.signature-image {
  border: 1px solid #c81d1d;
  width: 100%;
  height: 250px; /* 调整高度，缩小一点 */
  object-fit: contain; /* 确保图像比例正确 */
}

.clear-button {
  align-self: flex-end; /* 将清除签名按钮对齐到右侧 */
  margin-top: 10px; /* 增加按钮与签名框之间的距离 */
}
</style>
