<template>
	<div class="manage_page fillcontain">
		<el-row style="height: 100%;">
	  		<el-col :span="4"  style="min-height: 100%; background-color: #324057;">
				<el-menu :default-active="defaultActive" style="min-height: 100%;" theme="dark" router>
                    <el-menu-item index="categoryList"><i class="el-icon-menu"></i>菜品分类</el-menu-item>
                    <el-menu-item index="pictureList"><i class="el-icon-menu"></i>首页图片</el-menu-item>
                    <el-menu-item index="tjFood"><i class="el-icon-menu"></i>推荐菜品</el-menu-item>
                    <el-menu-item index="foodList"><i class="el-icon-menu"></i>菜品列表</el-menu-item>
                    <el-menu-item index="wmFood"><i class="el-icon-menu"></i>外卖菜品</el-menu-item>
                    <el-menu-item index="orderList"><i class="el-icon-menu"></i>店铺订单</el-menu-item>
                    <el-menu-item index="wmorder"><i class="el-icon-menu"></i>外卖订单</el-menu-item>
                    <el-menu-item index="commentList"><i class="el-icon-menu"></i>评论列表</el-menu-item>
                    <!--<el-menu-item index="tableList"><i class="el-icon-menu"></i>座号情况</el-menu-item>-->
                    <!--<el-menu-item index="pdList"><i class="el-icon-menu"></i>排队列表</el-menu-item>-->
                    <!--<el-menu-item index="userList"><i class="el-icon-menu"></i>员工信息</el-menu-item>-->
                    <el-menu-item index="monthList"><i class="el-icon-menu"></i>月销量</el-menu-item>
                    <el-menu-item index="yearList"><i class="el-icon-menu"></i>年度销量</el-menu-item>
				</el-menu>
			</el-col>
			<el-col :span="20" style="height: 100%;overflow: auto;">
				<keep-alive>
				    <router-view></router-view>
				</keep-alive>
			</el-col>
		</el-row>
  	</div>
</template>

<script>
    export default {
		computed: {
			defaultActive: function(){
				return this.$route.path.replace('/', '');
			}
		},
    }
</script>


<style lang="less" scoped>
	@import '../style/mixin';
	.manage_page{
		
	}
</style>
