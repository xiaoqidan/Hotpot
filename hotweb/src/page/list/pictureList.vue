<template>
    <div class="fillcontain">
        <head-top></head-top>
        <el-row class="query">
            <el-col :span="16">
                <el-button type="primary"  @click="handleEdit(null,false)">新增</el-button>
            </el-col>
            <el-col :span="8">
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
                  label="图片名称"
                  prop="name">
                </el-table-column>
                <el-table-column
                  label="图片类型"
                  prop="typeValue">
                </el-table-column>
                <el-table-column
                  label="图片顺序"
                  prop="sort">
                </el-table-column>
                <el-table-column
                    label="是否显示"
                    prop="validValue">
                </el-table-column>
                <el-table-column label="操作" width="200">
                  <template slot-scope="scope">
                      <el-button
                          size="small"
                          @click="openDeail(scope.row)">详情</el-button>
                    <el-button
                      size="small"
                      @click="handleEdit(scope.row,true)">修改</el-button>
                    <el-button
                      size="small"
                      type="danger"
                      @click="handleDelete(scope.row)">删除</el-button>
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
            <el-dialog title="修改菜品信息" v-model="dialogFormVisible" customClass="customWidth"  >
                <el-form :model="selectTable" >
                    <el-form-item label="图片名字：" label-width="100px">
                        <el-input v-model="selectTable.name"  type="text" :max='15'  style="width: 200px;" show-word-lmit></el-input>
                    </el-form-item>
                    <el-form-item label="图片类型：" label-width="100px">
	                    <el-select v-model="selectTable.type" clearable  >
						    <el-option
						      v-for="item in type"
						      :key="item.id"
						      :label="item.value"
						      :value="item.id">
						    </el-option>
						</el-select>
                    </el-form-item>
                    <el-form-item label="顺序：" label-width="100px">
                        <el-input type="number" v-model="selectTable.sort" style="width: 200px;"></el-input>
                    </el-form-item>
                    <el-form-item label="是否显示：" label-width="100px">
                        <el-switch v-model="selectTable.valid"   ></el-switch>
                    </el-form-item>
                    <el-form-item label="图片：" label-width="100px">
                        <el-upload
                          class="avatar-uploader"
                          :action="'/hg/image/upload'"
                          :show-file-list="false"
                          :on-success="handleServiceAvatarScucess"
                          :before-upload="beforeAvatarUpload">
                          <img v-if="selectTable.location" :src="selectTable.location" class="avatar">
                          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                        </el-upload>
                    </el-form-item>
                </el-form>
              <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="updatePicture">确 定</el-button>
              </div>
            </el-dialog>
            <el-dialog title="图片详情" v-model="dialogDetail"  >
                <el-form :model="selectTable" >
                    <el-row class="query">
                        <el-col :span="12">
                            <el-form-item label="图片名称：" label-width="100px">
                                <label >{{selectTable.name}}</label>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="图片类型：" label-width="100px">
                                <label >{{selectTable.typeValue}}</label>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="图片顺序：" label-width="100px">
                                <label >{{selectTable.sort}}</label>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="是否显示：" label-width="100px">
                                <label >{{selectTable.validValue}}</label>
                            </el-form-item>
                        </el-col>
                        <el-col :span="24">
                            <el-form-item label="图片：" label-width="100px">
                                <img v-if="selectTable.location" :src="selectTable.location" class="avatar2">
                            </el-form-item>
                        </el-col>
                    </el-row>
                </el-form>
            </el-dialog>
        </div>
    </div>
</template>

<script>
    import headTop from '../../components/headTop'
    export default {
    	data(){
    		return {
    			offset: 0,
    			limit: 15,
    			count: 0,
    			tableData: [],
    			currentPage: 1,
    			selectTable: {location:'',name:'',type:'',sort:'',valid:''},
    			dialogFormVisible: false,
                dialogDetail:false,
                type:[{id:0,value:'顶部轮播图'},{id:1,value:'中间插图'}],
    			flag:true
    		}
    	},
    	created(){
    		this.getPicture();
    	},
    	components: {
    		headTop,
    	},
    	methods: {
    		getPicture(){
    			const url = window.fdConfig.url.feature.picture;
                var params={
                    offset:this.offset,
                    limit:this.limit
                };
    			const _this = this;
    			this.$http.get(url,{
                    params:params
                }).then(function(res){
                    var data = res.body.data;
                    for(var i=0 ;i<data.length;i++){
                        data[i].typeValue = this.type[data[i].type].value;
                        data[i].validValue = data[i].valid === 1?'是':'否';
                    }
    				_this.tableData = res.body.data;
                    _this.count = _this.tableData.length;
    			},function(){
    			});
    		},
            openDeail(row){
                this.selectTable = row;
                this.dialogDetail = true;
            },
    		handleSizeChange() {
    		},
    		handleCurrentChange(val) {
    			this.currentPage = val;
    			this.offset = (val - 1)*this.limit;
    			this.getPicture()
    		},
    		handleEdit(row,t) {
    		    if(t){
    		        this.flag = false;
    			    this.selectTable = Object.assign({},row);
    			    this.selectTable.valid = this.selectTable.valid === 1;
    		    }else{
    		        this.flag = true;
    		        this.selectTable = {location:'',name:'',type:'',sort:'',valid:''};
    		    }
    			this.dialogFormVisible = true;
    		},
    		handleServiceAvatarScucess(res, file) {
    			if (res.data ) {
    				this.selectTable.location = res.data;
    				console.log(this.selectTable.location)
    			}else{
    				this.$message.error('上传图片失败！');
    			}
    		},
    		beforeAvatarUpload(file) {
    		    console.log("图片格式:",file.type);
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
    				const url = window.fdConfig.url.feature.picture+"/"+row.id;
    				const _this = this;
    				this.$http.delete(url).then(function(){
    					this.$notify({
    						title: '成功',
    						message: '删除成功',
    						type: 'success'
    					});
    					_this.getPicture();
    				},function(){
    					this.$notify.error({
    						title: '错误',
    						message: '操作错误'
    					});
    				});
    			}).catch(() => {
    			});
    		},
    		updatePicture(){
    			const url = window.fdConfig.url.feature.picture;
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
                    _this.getPicture();
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
    .avatar2 {
        width: 200px;
        height: 200px;
        display: block;
    }
</style>
