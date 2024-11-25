<script setup>
import {ref,reactive,onMounted} from 'vue'
import {adjustmentpage,deleteadjustment} from '@/api/adjustment';
import { useRouter } from 'vue-router';
const router=useRouter()
//分页查询结果的数据总条数
const total=ref(0)
const handleClick = () => {
  console.log('click')
}
const queryPageParams = reactive({
  deid: 0,
  status: '全部', // 新增的状态筛选字段
  pageNo: 1,
  pageSize: 5
});

const handleQueryPage=()=>{
  adjustmentpage(queryPageParams).then((res)=>{

    //console.log('output->res',res)
    tableData.value=res.data.records
    //把查询到的总条数赋值给 total变量
    total.value=res.data.total
})


}
//vue挂载完成之后执行onMouted钩子函数
onMounted(()=>{
    handleQueryPage()

})

const tableData = ref([
  /* {
    username: 'decade',
    imagePath: '',
    updateTime: '2022-01-01 12:00:00',
    createTime: '2022-01-01 12:00:00',
   
  } */
])

//修改每页数量的回调函数，参数表示修改完之后的每页数量
const handleSizeChange=(sizeValue)=>{
//console.log(`output->sizeValue`,sizeValue)
queryPageParams.pageSize = sizeValue
//请求后端api,并处理数据
handleQueryPage()

}
//该笔那页码的回调函数，参数表示修改完之后的页码
const handleCurrentChange=(currentValue)=>{
   // console.log(`output->currentValue`,currentValue)
 queryPageParams.pageNo = currentValue
 //请求后端api，并处理数据
 handleQueryPage()
}
//确认删除
const handleDeleteConfirm=(id)=>{
console.log(`output->id`,id)
deleteadjustment(id).then(()=>{
//删除成功后，再次分页查询，从后端获取最新数据，解决数据显示最新的问题
handleQueryPage()
})

}
//取消删除
const handeleDeleteCancel=(id)=>{
  console.log(`output->id`,id)

}
//编辑
const handleEdit=(id)=>{
  router.push({
path:"/customer/adjustment/edit",
//query方式传id
query:{id}

  })
}
//新增
const handleAdd=()=>{
router.push({
  path:"/customer/adjustment/edit"
})

}
//状态换颜色
const getTagType = (status) => {
  switch (status) {
    case '已通过':
      return 'success';  // 绿色标签
    case '未通过':
      return 'danger';   // 红色标签
    case '审核中':
      return 'warning';  // 黄色标签
    default:
      return '';         // 默认标签，没有特殊样式
  }
};
//状态改变
const changeStatus = () => {
  adjustmentpage(queryPageParams).then((res)=>{

//console.log('output->res',res)
tableData.value=res.data.records
//把查询到的总条数赋值给 total变量
total.value=res.data.total
})
};

const back=()=>{
router.push({
  path:"/customer/main"
})

}

</script>

<template>
  
   
    <label for="'deid'">需求号</label>
  <el-input id="deid" v-model="queryPageParams.deid" style="width:240px"></el-input>

  <label for="status">状态</label>
<el-select v-model="queryPageParams.status" placeholder="选择状态" style="width:240px" @change="changeStatus">
  <el-option label="全部" value="全部"></el-option>
  <el-option label="已通过" value="已通过"></el-option>
  <el-option label="未通过" value="未通过"></el-option>
  <el-option label="审核中" value="审核中"></el-option>
</el-select>

  <el-button type="primary" round @click="handleQueryPage">查询</el-button>
  <el-button type="primary" round @click="back">返回</el-button>
    <el-table :data="tableData" style="width: 100%" stripe highlight-current-row>
      <el-table-column prop="designId" label="设计号" width="180" />
      <el-table-column prop="name" label="名字" width="180" />
      <el-table-column prop="content" label="内容" width="180">
  <template #default="scope">
    <span>
      {{ scope.row.content ? (scope.row.content.length > 20 ? scope.row.content.slice(0, 20) + '...' : scope.row.content) : '' }}
    </span>
  </template>
</el-table-column>


 
 
        <el-table-column prop="status" label="状态" width="180">
  <template #default="scope">
    <el-tag
      :type="getTagType(scope.row.status)"
      :effect="'dark'"
    >
      {{ scope.row.status }}
    </el-tag>
  </template>
</el-table-column>
     


        <el-table-column prop="createTime" label="创建时间" width="180" />
        <el-table-column prop="updateTime" label="更新时间" width="300" />
    <el-table-column fixed="right" label="操作" width="180">
      <template #default="scope">
        <el-button link type="primary" size="small" @click="handleEdit(scope.row.id)">
            查看
        </el-button>
        <el-popconfirm
    confirm-button-text="确定"
    cancel-button-text="取消"
    icon-color="#626AEF"
    title="是否确认删除"
    @confirm="handleDeleteConfirm(scope.row.id)"
    @cancel="handeleDeleteCancel"
  >
  <template #reference>
        <el-button link type="danger" size="small" >删除</el-button>
     
  </template>
    </el-popconfirm>
  </template>
    </el-table-column>
  </el-table>
  <!--  v-model:current-page双向绑定当前页码-->
    <!-- v-model:page-size双向绑定每页数量 -->
     <!-- :total 绑定返回结果总条数变量 -->
  <el-pagination
      v-model:current-page="queryPageParams.pageNo"
      v-model:page-size="queryPageParams.pageSize"
      :page-sizes="[5, 10, 20, 30]"
      size="default"
      background
      layout="total, sizes, prev, pager, next, jumper"
      :total="total"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    />


</template>

<style scoped>

</style>





















