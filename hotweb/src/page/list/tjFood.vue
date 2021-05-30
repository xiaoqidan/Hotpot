<template>
    <div class="fillcontain">
        <head-top></head-top>
        <el-row class="query">
            <el-col :span="8">
                菜品名称：
                <el-input
                    placeholder="请输入内容"
                    prefix-icon="el-icon-search"
                    v-model="query.name" style="width:190px;">
                </el-input>
            </el-col>
            <el-col :span="8">
                菜品分类：
                <el-select v-model="query.type" placeholder="请选择">
                    <el-option
                        v-for="item in categorys"
                        :key="item.id"
                        :label="item.name"
                        :value="item.id">
                    </el-option>
                </el-select>
            </el-col>
            <el-col :span="8">
                类型：
                <el-select v-model="query.tj" placeholder="请选择">
                    <el-option
                        v-for="item in type"
                        :key="item.id"
                        :label="item.value"
                        :value="item.id">
                    </el-option>
                </el-select>
            </el-col>
        </el-row>
        <el-row class="query">
            <el-col :span="16">
            </el-col>
            <el-col :span="8">
                <el-button type="primary"  @click="getFoods">查询</el-button>
                <el-button type="primary"  @click="resetData" plain>重置</el-button>
            </el-col>
        </el-row>
        <div class="table_container">
            <el-table
                :data="tableData"
                style="width: 100%">
                <el-table-column
                    type="index"
                    width="100px">
                </el-table-column>
                <el-table-column
                  label="菜品名称"
                  prop="name">
                </el-table-column>
                <el-table-column
                  label="菜品分类"
                  prop="categoryName">
                </el-table-column>
                <el-table-column
                  label="菜品价格"
                  prop="price">
                </el-table-column>
                <el-table-column
                    label="销量"
                    prop="number">
                </el-table-column>
                <el-table-column label="操作" width="160">
                  <template slot-scope="scope">
                    <el-button
                      size="small"
                      v-if="scope.row.tj === 0"
                      @click="handleEdit(scope.row)">设为推荐</el-button>
                    <el-button
                      size="small"
                      type="danger"
                      v-if="scope.row.tj === 1"
                      @click="handleEdit(scope.row)">取消推荐</el-button>
                  </template>
                </el-table-column>
            </el-table>
            <div class="Pagination">
                <el-pagination
                  @size-change="handleSizeChange"
                  @current-change="handleCurrentChange"
                  :current-page="currentPage"
                  :page-size="15"
                  layout="total, prev, pager, next"
                  :total="count">
                </el-pagination>
            </div>
        </div>
    </div>
</template>

<script>
    import headTop from '../../components/headTop'
    export default {
    	data(){
    		return {
    			query:{name:'',type:'',},
    			categorys:[],
    			offset: 0,
    			limit: 15,
    			count: 0,
    			tableData: [],
    			currentPage: 1,
    			selectTable: {imgUrl:'',categoryId:''},
    			dialogFormVisible: false,
                type:[{id:1,value:'推荐菜品'},{id:0,value:'普通菜品'}],
    			flag:true
    		}
    	},
    	created(){
    		this.getFoods();
    		this.initCategory();
    	},
    	components: {
    		headTop,
    	},
    	methods: {
    		initCategory(){
    			const url = window.fdConfig.url.feature.category;
    			const _this = this;
    			this.$http.get(url,{
    			}).then(function(res){
    				_this.categorys = res.body;
    			},function(){
    			});
    		},
    		getFoods(){
    			const url = window.fdConfig.url.feature.food;
    			var params={
    				offset:this.offset,
    				limit:this.limit
    			};
    			if(this.query.type !== ''){params.categoryId = this.query.type;}
    			if(this.query.name !== ''){params.name = this.query.name;}
                if(this.query.tj !== ''){params.tj = this.query.tj;}
    			const _this = this;
    			this.$http.get(url,{
    				params:params
    			}).then(function(res){
    				var data = res.body;
    				_this.count = data.pageInfo.rowCount;
    				_this.tableData = data.data;
    			},function(){
    			});
    		},
    		resetData(){
    			this.query = {};
    			this.getFoods();
    		},
    		handleSizeChange() {
    		},
    		handleCurrentChange(val) {
    			this.currentPage = val;
    			this.offset = (val - 1)*this.limit;
    			this.getFoods()
    		},
    		handleEdit(row) {
                const url = window.fdConfig.url.feature.foodEdit;
                const _this = this;
                var data = {
                    id:row.id,
                    tj:row.tj === 1?0:1
                }
                this.$http.post(url,data
                ).then(function(){
                    this.$notify({
                        title: '成功',
                        message: '修改成功',
                        type: 'success'
                    });
                    _this.getFoods();
                },function(){
                    this.$notify.error({
                        title: '错误',
                        message: '操作错误'
                    });
                });
    		}
    	},
    }
</script>

<style lang="less">
	@import '../../style/mixin';
    .customWidth{
        width: 30%;
    }
    .query{
        margin: 20px 20px 0 20px;
    }
    .query .el-col {
        min-height: 1px;
    }
    .query .el-button {
        width: 100px;
    }
    .demo-table-expand {
        font-size: 0;
    }
    .demo-table-expand label {
        width: 90px;
        color: #99a9bf;
    }
    .demo-table-expand .el-form-item {
        margin-right: 0;
        margin-bottom: 0;
        width: 50%;
    }
    .table_container{
        padding: 20px;
    }
    .Pagination{
        display: flex;
        justify-content: flex-start;
        margin-top: 8px;
    }
    .avatar-uploader .el-upload {
        border: 1px dashed #d9d9d9;
        border-radius: 6px;
        cursor: pointer;
        position: relative;
        overflow: hidden;
    }
    .avatar-uploader .el-upload:hover {
        border-color: #20a0ff;
    }
    .avatar-uploader-icon {
        font-size: 28px;
        color: #8c939d;
        width: 120px;
        height: 120px;
        line-height: 120px;
        text-align: center;
    }
    .avatar {
        width: 120px;
        height: 120px;
        display: block;
    }
</style>
