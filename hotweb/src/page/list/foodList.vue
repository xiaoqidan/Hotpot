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
                是否显示：
                <el-select v-model="query.valid" placeholder="请选择">
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
                <el-button type="primary"  @click="handleEdit(null,false)">新增</el-button>
                <el-button type="success"  @click="exportExcel">导出</el-button>
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
                  prop="priceValue">
                </el-table-column>
                <el-table-column
                    label="菜品份量"
                    prop="sizeValue">
                </el-table-column>
                <el-table-column
                    label="销量"
                    prop="number">
                </el-table-column>
                <el-table-column
                    label="是否显示"
                    prop="validValue">
                </el-table-column>
                <el-table-column label="操作" width="200">
                  <template slot-scope="scope">
                      <el-button
                          size="small"
                          @click="openDetail(scope.row)">详情</el-button>
                    <el-button
                      size="small"
                      type="primary"
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
                    <el-form-item label="菜品名称：" label-width="100px">
                        <el-input v-model="selectTable.name"  type="text" :max='15'  style="width: 200px;" show-word-lmit></el-input>
                    </el-form-item>
                    <el-form-item label="菜品价格：" label-width="100px">
                        <el-input type="number" v-model="selectTable.price" style="width: 200px;"></el-input>
                    </el-form-item>
                    <el-form-item label="菜品份量：" label-width="100px">
                        <el-input type="number" v-model="selectTable.size" style="width: 200px;"></el-input>
                    </el-form-item>
                    <el-form-item label="菜品单位：" label-width="100px">
                        <el-input type="text" :max='5' show-word-limit v-model="selectTable.unit" style="width: 200px;"></el-input>
                    </el-form-item>
                    <el-form-item label="是否显示：" label-width="100px">
                        <el-switch v-model="selectTable.valid" value=""  ></el-switch>
                    </el-form-item>
                    <el-form-item label="菜品分类：" label-width="100px">
	                    <el-select v-model="selectTable.categoryName" clearable  >
						    <el-option
						      v-for="item in categorys"
						      :key="item.id"
						      :label="item.name"
						      :value="item.id">
						    </el-option>
						</el-select>
                    </el-form-item>
                    <el-form-item label="菜品图片" label-width="100px">
                        <el-upload
                          class="avatar-uploader"
                          :action="'/hg/image/upload'"
                          :show-file-list="false"
                          :on-success="handleServiceAvatarScucess"
                          :before-upload="beforeAvatarUpload">
                          <img v-if="selectTable.imgUrl" :src="selectTable.imgUrl" class="avatar">
                          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                        </el-upload>
                    </el-form-item>
                </el-form>
              <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="updateFood">确 定</el-button>
              </div>
            </el-dialog>
            <el-dialog title="订单详情" v-model="dialogDetailVisible"  >
                <el-form :model="selectTable" >
                    <el-row class="query">
                        <el-col :span="12">
                            <el-form-item label="菜品名称：" label-width="100px">
                                <label >{{selectTable.name}}</label>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="菜品价格：" label-width="100px">
                                <label >{{selectTable.priceValue}}</label>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="菜品份量：" label-width="100px">
                                <label >{{selectTable.sizeValue}}</label>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="是否显示：" label-width="100px">
                                <label >{{selectTable.validValue}}</label>
                            </el-form-item>
                        </el-col>
                        <el-col :span="24">
                            <el-form-item label="菜品分类：" label-width="100px">
                                <label >{{selectTable.categoryName}}</label>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="菜品图片：" label-width="100px">
                                <img  :src="selectTable.imgUrl" class="avatar">
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
    			query:{name:'',type:'',valid:''},
    			categorys:[],
    			offset: 0,
    			limit: 15,
    			count: 0,
    			tableData: [],
    			currentPage: 1,
    			selectTable: {imgUrl:'',categoryId:''},
    			dialogFormVisible: false,
                dialogDetailVisible:false,
                type:[{id:0,value:'不显示'},{id:1,value:'显示'}],
    			flag:true
    		}
    	},
        mounted(){
            this.initCategory();
        },
    	created(){
    		this.getFoods();
    	},
    	components: {
    		headTop,
    	},
    	methods: {
    		initCategory(){
    			const url = window.fdConfig.url.feature.category+"?type=0";
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
    				limit:this.limit,
                    type:0
    			};
    			if(this.query.type !== ''){params.categoryId = this.query.type;}
    			if(this.query.name !== ''){params.name = this.query.name;}
                if(this.query.valid !== ''){params.valid = this.query.valid;}
    			const _this = this;
    			this.$http.get(url,{
    				params:params
    			}).then(function(res){
    				var data = res.body;
    				for(var i =0 ;i<data.data.length;i++){
    				    data.data[i].priceValue = data.data[i].price.toString() +'元';
                        data.data[i].sizeValue = data.data[i].size.toString() +'/' + data.data[i].unit;
    				    data.data[i].validValue = data.data[i].valid === 1?'是':'否';
                    }
    				_this.count = data.pageInfo.rowCount;
    				_this.tableData = data.data;
    			},function(){
    			});
    		},
    		resetData(){
    			this.query = {};
    			this.getFoods();
    		},
    		exportExcel(){
    			location.href = "http://localhost:8080/hg/export/food/0";
    		},
    		handleSizeChange() {
    		},
    		handleCurrentChange(val) {
    			this.currentPage = val;
    			this.offset = (val - 1)*this.limit;
    			this.getFoods()
    		},
            openDetail(row){
                this.selectTable = row;
                this.dialogDetailVisible = true;
            },
    		handleEdit(row,t) {
    		    if(t){
    		        this.flag = false;
    			    this.selectTable = Object.assign({},row);
    			    this.selectTable.valid = this.selectTable.valid === 1;
    		    }else{
    		        this.flag = true;
    		        this.selectTable = {imgUrl:'',categoryId:'',type:0,valid:true};
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
    			if(typeof(this.selectTable.categoryName) !== 'string'){
                    this.selectTable.categoryId = this.selectTable.categoryName;
                }
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
