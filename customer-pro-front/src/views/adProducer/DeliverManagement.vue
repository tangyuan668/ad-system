
<script setup>
import {ref,reactive,onMounted} from 'vue'
import {deliverpage,deletedeliver} from '@/api/deliver';
import { useRouter } from 'vue-router';
const router=useRouter()
//分页查询结果的数据总条数
const total=ref(0)
const handleClick = () => {
  console.log('click')
}
const queryPageParams = reactive({
  reid: 0,
  status: '全部', // 新增的状态筛选字段
  pageNo: 1,
  pageSize: 5,
  name:''
});

const handleQueryPage=()=>{
  deliverpage(queryPageParams).then((res)=>{

   // console.log('hhhhhhhhhhhhhhhhhhhhhhhhhh',res)
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
deletedeliver(id).then(()=>{
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
path:"/adProducer/deliver/edit",
//query方式传id
query:{id}

  })
}
//新增
const handleAdd=()=>{
router.push({
  path:"/adProducer/deliver/edit"
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
  deliverpage(queryPageParams).then((res)=>{

//console.log('output->res',res)
tableData.value=res.data.records
//把查询到的总条数赋值给 total变量
total.value=res.data.total
})
};

const back=()=>{
router.push({
  path:"/adProducer/main"
})

}

</script>

<template>
  
   
    <label for="'reid'">广告制作需求号</label>
  <el-input id="reid" v-model="queryPageParams.reid" style="width:240px"></el-input>
  <label for="'reid'">名字</label>
  <el-input id="reid" v-model="queryPageParams.name" style="width:240px"></el-input>
  <label for="status">状态</label>
<el-select v-model="queryPageParams.status" placeholder="选择状态" style="width:240px" @change="changeStatus">
  <el-option label="全部" value="全部"></el-option>
  <el-option label="已通过" value="已通过"></el-option>
  <el-option label="未通过" value="未通过"></el-option>
  <el-option label="审核中" value="审核中"></el-option>
</el-select>

  <el-button type="primary" round @click="handleQueryPage">查询</el-button>
  <el-button type="primary" round @click="handleAdd">新增</el-button>
  <el-button type="primary" round @click="back">返回</el-button>
    <el-table :data="tableData" style="width: 100%" stripe highlight-current-row>
      <el-table-column prop="requestId" label="广告需求号" width="180" />
      <el-table-column prop="name" label="广告设计图名" width="180" />
      <el-table-column label="广告设计图" width="450">
        <!-- el-table-column插槽:scope.row获取到一行数据 -->
    <template #default="scope">
        
        <el-image style="width: 100px; height: 100px" :src="scope.row.imagePath"  />

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
        <el-table-column prop="advice" label="建议" width="180">
  <template #default="scope">
    <span>
      {{ scope.row.advice ? (scope.row.advice.length > 20 ? scope.row.advice.slice(0, 20) + '...' : scope.row.advice) : '' }}
    </span>
  </template>
</el-table-column>
        <el-table-column prop="createTime" label="创建时间" width="180" />
        <el-table-column prop="updateTime" label="更新时间" width="300" />
    <el-table-column fixed="right" label="操作" width="180">
      <template #default="scope">
        <el-button link type="primary" size="small" @click="handleEdit(scope.row.id)">
           修改
        </el-button>
        <el-popconfirm
    confirm-button-text="确定"
    cancel-button-text="返回"
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





















<!-- <template>
    <div>
      <el-button type="primary" @click="handleAdd">新增广告提案</el-button>
      <el-table :data="deliverData" style="width: 100%" stripe highlight-current-row>
        <el-table-column prop="content" label="提案内容" width="300" />
        <el-table-column prop="budget" label="预算" width="180" />
        <el-table-column prop="status" label="状态" width="180" />
        <el-table-column prop="create_time" label="创建时间" width="180" />
        <el-table-column prop="update_time" label="更新时间" width="180" />
        <el-table-column label="操作" width="180">
          <template #default="scope">
            <el-button link type="primary" size="small" @click="handleEdit(scope.row.deliver_id)">
              编辑
            </el-button>
            <el-button link type="danger" size="small" @click="handleDelete(scope.row.deliver_id)">
              删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </template>
  
  <script setup>
  import { ref, onMounted } from 'vue';
  import { fetchDelivers, deleteDeliver } from '@/api/deliver';
  import { useRouter } from 'vue-router';
  
  const router = useRouter();
  const deliverData = ref([]);
  
  // 获取广告提案数据
  const loadDelivers = () => {
    fetchDelivers().then((res) => {
      deliverData.value = res.data;
    });
  };
  
  onMounted(() => {
    loadDelivers();
  });
  
  // 新增广告提案
  const handleAdd = () => {
    router.push({ path: '/adProducer/deliver/add' });
  };
  
  // 编辑广告提案
  const handleEdit = (id) => {
    router.push({ path: '/adProducer/deliver/edit', query: { id } });
  };
  
  // 删除广告提案
  const handleDelete = (id) => {
    deleteDeliver(id).then(() => {
      loadDelivers(); // 重新加载广告提案列表
    });
  };
  </script>
  
  <style scoped>
  .el-button {
    margin-bottom: 20px;
  }
  </style> -->