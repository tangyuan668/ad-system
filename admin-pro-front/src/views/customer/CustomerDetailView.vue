<script setup>
import { useRoute,useRouter } from 'vue-router';
import { onMounted,ref } from 'vue';
import { findById } from '@/api/customer';

const route = useRoute()
const router = useRouter()
const customerView=ref({})
onMounted(()=>{
    //获取路由query传的参数
const id=route.query.id
console.log(`output->id`,id)
findById(id).then((res)=>{

    console.log("output->res",res)
 customerView.value=res.data
})
})
const handleBackup=()=>{
router.back()

}
</script>
<template>
    <h1>CustomerDetailView</h1>
    <div>管理员用户名:</div>
    <div><el-input readonly v-model="customerView.username" ></el-input></div>
<div>管理员头像：</div>
<div> <el-image style="width: 100px; height: 100px" :src="customerView.imagePath"  /></div>
<!-- value-format 数据格式-->
 <!-- fromat 显示格式-->
  <div>创建时间</div>
<el-date-picker

        v-model="customerView.createTime"
        type="datetime"
        placeholder="select date and time"
        value-format="YYYY-MM-DD HH:mm:ss"
        format="YYYY-MM-DD HH:mm:ss"
        readonly
      />
      <div><el-button type="primary" @click="handleBackup">返回</el-button></div>

</template>


<style scoped>

</style>








