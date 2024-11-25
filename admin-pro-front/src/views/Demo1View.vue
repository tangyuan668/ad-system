<template>
    <div>
        <el-container>
 <!-- 上面部分 -->
            <el-header>
                <div class="grid-content ep-bg-purple-dark"></div>
            </el-header>
   <!--  下面部分 -->
        <el-container style="height: 120px;">
     <el-aside>
    <div class="grid-content ep-bg-purple-dark"></div>
</el-aside>
<el-container>

    <el-main>
        <div class="grid-content ep-bg-purple"></div>
    </el-main>
    <el-footer>
     
        <div class="grid-content ep-bg-purple"></div>
    </el-footer>
</el-container>
        </el-container>


</el-container>




    </div>




</template>


<style scoped>
.grid-content {
  height: 40px;
}

.ep-bg-purple-dark {
  background-color: red;
}

.ep-bg-purple {
  background-color: green;
}

.ep-bg-purple-light {
  background-color: yellow;
}

</style>