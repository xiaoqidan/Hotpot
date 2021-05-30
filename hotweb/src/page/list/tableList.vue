<template>
    <div class="fillcontain">
        <head-top></head-top>
        <el-row class="query">
            <el-col :span="8">
                桌号：
                <el-input
                    placeholder="请输入内容"
                    prefix-icon="el-icon-search"
                    v-model="query.name" style="width:190px;">
                </el-input>
            </el-col>
            <el-col :span="8">
                桌子状态：
                <el-select v-model="query.type" placeholder="请选择">
                    <el-option
                        v-for="item in categorys"
                        :key="item.id"
                        :label="item.name"
                        :value="item.id">
                    </el-option>
                </el-select>
            </el-col>
        </el-row>
        <el-row class="query">
            <el-col :span="16">
                <el-button type="primary"  @click="handleEdit">新增</el-button>
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
                  label="桌子序号"
                  prop="name">
                </el-table-column>
                <el-table-column
                  label="容纳人数"
                  prop="categoryName">
                </el-table-column>
                <el-table-column
                  label="桌子位置"
                  prop="price">
                </el-table-column>
                <el-table-column
                    label="桌子类型"
                    prop="size">
                </el-table-column>
                <el-table-column
                    label="桌子状态"
                    prop="unit">
                </el-table-column>
                <el-table-column label="操作" width="160">
                  <template slot-scope="scope">
                    <el-button
                      size="small"
                      @click="handleEdit(scope.row)">可用</el-button>
                    <el-button
                      size="small"
                      type="danger"
                      @click="handleEdit(scope.row)">清洁</el-button>
                  <el-button
                      size="small"
                      type="danger"
                      @click="handleEdit(scope.row)">占用</el-button>
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
    			flag:true
    		}
    	},
    	created(){
    		this.getTable();
    	},
    	components: {
    		headTop,
    	},
    	methods: {
    		getTable(){
    			const url = window.fdConfig.url.feature.table;
                var params={
                    offset:this.offset,
                    limit:this.limit
                };
    			const _this = this;
    			this.$http.get(url,{params:params
    			}).then(function(res){
    			    var data = res.body.data;

    				_this.tableData = data;
    			},function(){
    			});
    		},
    		resetData(){
    			this.query = {};
    			this.getFoods();
    		},
    		exportExcel(){
    			location.href = "http://localhost:8080/hg/export/food";
    		},
    		handleSizeChange() {
    		},
    		handleCurrentChange(val) {
    			this.currentPage = val;
    			this.offset = (val - 1)*this.limit;
    			this.getFoods()
    		},
    		handleEdit(row) {
    		    if(row!=null){
    		        this.flag = false;
    			    this.selectTable = Object.assign({},row);
    			    this.selectTable.valid = this.selectTable.valid === 1;
    		    }else{
    		        this.flag = true;
    		        this.selectTable = {};
    		    }
    			this.dialogFormVisible = true;
    		},
    		handleServiceAvatarScucess(res, file) {
    			if (res.data ) {
    				this.selectTable.imgUrl = res.data;
    				console.log(this.selectTable.imgUrl)
    			}else{
    				this.$message.error('上传图片失败！');
    			}
    		},
    		beforeAvatarUpload(file) {
    			const isRightType = (file.type === 'image/jpeg') || (file.type === 'image/png');
    			const isLt2M = file.size / 1024 / 1024 < 2;

    			if (!isRightType) {
    				this.$message.error('上传头像图片只能是 JPG 格式!');
    			}
    			if (!isLt2M) {
    				this.$message.error('上传头像图片大小不能超过 2MB!');
    			}
    			return isRightType && isLt2M;
    		},
    		handleDelete(row){
    			this.$confirm('此操作将永久删除该数据, 是否继续?', '提示', {
    				confirmButtonText: '确定',
    				cancelButtonText: '取消',
    				type: 'warning'
    			}).then(() => {
    				const url = window.fdConfig.url.feature.foodEdit+"/"+row.id;
    				const _this = this;
    				this.$http.delete(url).then(function(){
    					this.$notify({
    						title: '成功',
    						message: '删除成功',
    						type: 'success'
    					});
    					_this.getFoods();
    				},function(){
    					this.$notify.error({
    						title: '错误',
    						message: '操作错误'
    					});
    				});
    			}).catch(() => {
    			});
    		},
    		updateFood(){
    			const url = window.fdConfig.url.feature.foodEdit;
    			const _this = this;
    			this.selectTable.valid = this.selectTable.valid ?1:0;
    			this.selectTable.categoryId = this.selectTable.categoryName;
    			this.$http.post(url,this.selectTable
                ).then(function(){
                    this.$notify({
                        title: '成功',
                        message: _this.flag?'添加成功':'修改成功',
                        type: 'success'
                    });
                    _this.dialogFormVisible = false;
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
