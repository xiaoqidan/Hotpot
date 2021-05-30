<template>
    <div class="fillcontain">
        <head-top></head-top>
        <el-row class="query">
            <el-col :span="16">
                <el-button type="primary"  @click="handleAdd">新增</el-button>
            </el-col>
        </el-row>
        <div class="table_container">
            <el-table
                :data="tableData"
                stripe
                style="width: 100%">
                <el-table-column
                    type="index"
                    width="100px">
                </el-table-column>
                <el-table-column
                  label="分类名称"
                  prop="name">
                </el-table-column>
                <el-table-column
                  label="顺序"
                  prop="sort">
                </el-table-column>
                <el-table-column
                    label="类型"
                    prop="typeValue">
                </el-table-column>
                <el-table-column
                  label="是否显示"
                  prop="validValue">
                </el-table-column>
                <el-table-column
                    label="菜品数量"
                    prop="number">
                </el-table-column>
                <el-table-column label="操作" width="200">
                <template slot-scope="scope">
                    <el-button
                        size="small"
                        @click="handleAdd(scope.$index, scope.row)">编辑</el-button>
                    <el-button
                        size="small"
                        type="danger"
                        @click="handleDelete(scope.row)">删除</el-button>
                </template>
            </el-table-column>
            </el-table>
            <el-dialog title="分类信息" v-model="dialogFormVisible" customClass="customWidth" >
                <el-form :model="selectTable">
                    <el-form-item label="名称：" label-width="100px">
                        <el-input v-model="selectTable.name"  type="text" :max='15'  style="width: 200px;" show-word-lmit></el-input>
                    </el-form-item>
                    <el-form-item label="顺序：" label-width="100px">
                        <el-input type="number" v-model="selectTable.sort" style="width: 200px;"></el-input>
                    </el-form-item>
                    <el-form-item label="是否可见：" label-width="100px">
                        <el-switch v-model="selectTable.valid" value=""  ></el-switch>
                    </el-form-item>
                    <el-form-item label="类型：" label-width="100px">
                        <el-select v-model="selectTable.type" clearable  >
                            <el-option
                                v-for="item in types"
                                :key="item.id"
                                :label="item.name"
                                :value="item.id">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-form>
              <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="updateCategory">同 意</el-button>
              </div>
            </el-dialog>
        </div>
    </div>
</template>

<script>
    /* eslint-disable indent */

    import headTop from '../../components/headTop'
    export default {
    	data(){
    		return {
    			tableData: [],
    			selectTable: {},
    			dialogFormVisible: false,
    			categoryOptions: [],
    			selectedCategory: [],
    			flag:true,
                types:[{id:0,name:'本店'},{id:1,name:'外卖'}]
    		}
    	},
    	components: {
    		headTop,
    	},
    	created(){
    		this.getCategory();
    	},
    	methods: {
    		getCategory(){
    			const url = window.fdConfig.url.feature.category ;
    			const _this = this;
    			this.$http.get(url,{
    			}).then(function(res){
    				var data = res.body;
    				_this.count = data.length;
    				for(var i =0 ;i<data.length;i++){
    					data[i].validValue = data[i].valid === 1?'是':'否';
                        data[i].typeValue = data[i].type === 1?'外卖':'本店';
    				}
    				_this.tableData = data;
    			},function(){

    			});
    		},
    		handleAdd(index, row) {
    			if(row!=null) {
    				this.selectTable = row;
    				this.selectTable.valid = this.selectTable.valid === 1;
    				this.flag = false;
    			}else{
                    this.selectTable = {};
    				this.flag = true;
    			}
    			this.dialogFormVisible = true;
    		},
    		updateCategory(){
    			const url = window.fdConfig.url.feature.category;
    			const _this = this;
                this.selectTable.valid = this.selectTable.valid ?1:0;
    			this.$http.post(url,this.selectTable
    			).then(function(){
    				this.$notify({
    					title: '成功',
    					message: _this.flag?'添加成功':'修改成功',
    					type: 'success'
    				});
    				_this.dialogFormVisible = false;
    				_this.getCategory();
    			},function(){
                    this.$notify.error({
                        title: '错误',
                        message: '操作错误'
                    });
    			});
    		},handleDelete(row){
                this.$confirm('此操作将永久删除该数据, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    const url = window.fdConfig.url.feature.category+"/"+row.id;
                    const _this = this;
                    this.$http.delete(url).then(function(){
                        this.$notify({
                            title: '成功',
                            message: '删除成功',
                            type: 'success'
                        });
                        _this.getCategory();
                    },function(){
                        this.$notify.error({
                            title: '错误',
                            message: '操作错误'
                        });
                    });
                }).catch(() => {
                });
    		}
    	},
    }
</script>

<style lang="less">
	@import '../../style/mixin';
    .customWidth{
        width: 20%;
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
