<template>
    <div class="fillcontain">
        <head-top></head-top>
        <el-row class="query">
            <el-col :span="8">
                用户名称：
                <el-input
                    placeholder="请输入内容"
                    prefix-icon="el-icon-search"
                    v-model="query.name" style="width:190px;">
                </el-input>
            </el-col>
            <el-col :span="10">
                订单状态：
                <el-select v-model="query.status" placeholder="请选择">
                    <el-option
                        v-for="item in options"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                    </el-option>
                </el-select>
            </el-col>
            <el-col :span="6">
                <el-button type="success"  @click="exportExcel">导出所有</el-button>
                <el-button type="primary"  @click="getOrders">查询</el-button>
                <el-button type="primary"  @click="resetData" plain>重置</el-button>
            </el-col>
        </el-row>
        <!--<el-row class="query">-->
        <!--</el-row>-->
        <div class="table_container">
            <el-table
			    :data="tableData"
			    style="width: 100%">
                <el-table-column
                    type="index"
                    width="100px">
                </el-table-column>
			    <el-table-column
			      label="订单用户"
			      prop="userName"
                  width="150px">
			    </el-table-column>
                <el-table-column
                    label="桌号"
                    prop="tableNumber"
                width="100px">
                </el-table-column>
                <el-table-column
                    label="订单内容"
                    prop="content"
                    show-overflow-tooltip
                >
                </el-table-column>
			    <el-table-column
			      label="总价格"
			      prop="money"
                  width="150px">
			    </el-table-column>
			    <el-table-column
			      label="订单状态"
			      prop="statusValue"
                  width="150px">
			    </el-table-column>
                <el-table-column
                    label="创建时间"
                    prop="createTime"
                    width="200px">
                </el-table-column>
                <el-table-column label="操作" width="200">
                    <template slot-scope="scope">
                        <el-button
                            size="small"
                            @click="handle(scope.row)">详情</el-button>
                        <el-button
                            size="small"
                            type="success"
                            v-if="scope.row.status === 1"
                            @click="updateStatus(scope.row)">出菜完成</el-button>
                        <el-button
                            size="small"
                            type="primary"
                            v-if="scope.row.status === 2"
                            @click="updateStatus(scope.row)">结账完成</el-button>
                    </template>
                </el-table-column>
			</el-table>
            <div class="Pagination" style="text-align: left;margin-top: 10px;">
                <el-pagination
                  @size-change="handleSizeChange"
                  @current-change="handleCurrentChange"
                  :current-page="currentPage"
                  :page-size="15"
                  layout="total, prev, pager, next"
                  :total="count">
                </el-pagination>
            </div>
            <el-dialog title="订单详情" v-model="dialogFormVisible"  >
                <el-form :model="selectTable" >
                    <el-row class="query">
                        <el-col :span="12">
                            <el-form-item label="用户名称：" label-width="100px">
                                <label >{{selectTable.userName}}</label>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="订单时间：" label-width="100px">
                                <label >{{selectTable.createTime}}</label>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="订单价格：" label-width="100px">
                                <label >{{selectTable.money}}</label>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="订单状态：" label-width="100px">
                                <label >{{selectTable.statusValue}}</label>
                            </el-form-item>
                        </el-col>
                        <el-col :span="22">
                            <el-form-item label="订单内容：" label-width="100px">
                                <label style="display:inline-block;width: 400px;">{{selectTable.content}}</label>
                            </el-form-item>
                        </el-col>
                        <el-col :span="22">
                            <el-form-item label="备注：" label-width="100px">
                                <label style="display:inline-block;width: 400px;">{{selectTable.bz}}</label>
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
    			query:{name:'',status:'',type:''},
    			tableData: [],
    			currentRow: null,
    			offset: 0,
    			limit: 15,
    			count: 0,
    			currentPage: 1,
    			selectTable: {},
    			dialogFormVisible: false,
    			options: [{
                    value: '0',
                    label: '已取消'
                },{
    				value: '1',
    				label: '已预约'
    			}, {
    				value: '2',
    				label: '已出菜'
    			}, {
    				value: '3',
    				label: '已结账'
    			}, {
    				value: '4',
    				label: '已评价'
    			}]
    		}
    	},
    	components: {
    		headTop,
    	},
    	created(){
    		this.getOrders();
    	},
    	methods: {
    		handleSizeChange() {
    		},
    		handleCurrentChange(val) {
    			this.currentPage = val;
    			this.offset = (val - 1)*this.limit;
    			this.getOrders()
    		},
    		exportExcel(){
    			location.href = "http://localhost:8080/hg/export/order/1";
    		},
    		resetData(){
    			this.query = {};
    			this.getOrders();
    		},
    		getOrders(){
    			const url = window.fdConfig.url.feature.order;
    			this.query.offset = this.offset;
    			this.query.limit = this.limit;
    			this.query.type = 1;
    			const _this = this;
    			this.$http.get(url,{
    				params:this.query
    			}).then(function(res){
    				var data = res.body;
    				_this.count = data.pageInfo.rowCount;
    				for(var i =0 ;i<data.data.length;i++){
    					data.data[i].statusValue = _this.options[data.data[i].status ].label;
                        data.data[i].money += '元';
                        data.data[i].tableNumber +='号';
    				}
    				_this.tableData = data.data;
    			},function(){
    			});
    		},
    		handle(row) {
    			this.selectTable = Object.assign({},row);
    			this.dialogFormVisible = true;
    		},
            updateStatus(row){
                const url = window.fdConfig.url.feature.orderupdate;
                const _this = this;
                var data = {
                    id:row.id,
                    status:row.status + 1
                }
                this.$http.post(url,data
                ).then(function(){
                    this.$notify({
                        title: '成功',
                        message: '设置成功',
                        type: 'success'
                    });
                    _this.getOrders();
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
    .query{
        margin: 20px 20px 0 20px;
    }
    .query .el-col {
        min-height: 1px;
    }
    .query .el-button {
        width: 100px;
    }
    .table_container{
        padding: 20px;
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
</style>
