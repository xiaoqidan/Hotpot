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
            <el-col :span="8">
                分数：
                <el-select v-model="query.type" placeholder="请选择">
                    <el-option
                        v-for="item in options"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                    </el-option>
                </el-select>
            </el-col>
            <el-col :span="8">
                是否回复：
                <el-select v-model="query.status" placeholder="请选择">
                    <el-option
                        v-for="item in backs"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                    </el-option>
                </el-select>
            </el-col>
        </el-row>
        <el-row class="query">
            <el-col :span="16">
            </el-col>
            <el-col :span="8">
                <el-button type="primary"  @click="getComment">查询</el-button>
                <el-button type="primary"  @click="resetData" plain>重置</el-button>
            </el-col>
        </el-row>
        <div class="table_container">
            <el-table
                :data="tableData"
                highlight-current-row
                style="width: 100%">
                <el-table-column
                  type="index"
                  width="100px">
                </el-table-column>
                <el-table-column
                    property="name"
                    label="用户姓名">
                </el-table-column>
                <el-table-column
                    property="star"
                    label="分数">
                </el-table-column>
                <el-table-column
                    property="content"
                    show-overflow-tooltip
                    label="内容">
                </el-table-column>
                <el-table-column
                    property="createTime"
                    label="日期">
                </el-table-column>
                <el-table-column label="操作" >
                    <template slot-scope="scope">
                        <el-button
                            size="small"
                            v-if="scope.row.back"
                            @click="handleXq(false, scope.row)">详情</el-button>
                        <el-button
                            size="small"
                            type="danger"
                            v-if="!scope.row.back"
                            @click="handleXq(true, scope.row)">回复</el-button>
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
            <el-dialog title="评论信息" v-model="dialogFormVisible">
                <el-form :inline="true"  :model="selectTable">
                    <el-row class="query">
                        <el-col :span="12">
                            <el-form-item label="用户名称：" label-width="100px">
                                <label >{{selectTable.name}}</label>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="分数" label-width="100px">
                                <label >{{selectTable.star}}</label>
                            </el-form-item>
                        </el-col>
                        <el-col :span="22">
                            <el-form-item label="评论标签：" label-width="100px">
                                <label :title="selectTable.typeValue" style="display:inline-block;width: 400px;">{{selectTable.typeValue}}</label>
                            </el-form-item>
                        </el-col>
                        <el-col :span="22">
                            <el-form-item label="评论内容：" label-width="100px">
                                <label :title="selectTable.content" style="display:inline-block;width: 400px;">{{selectTable.content}}</label>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="评论时间：" label-width="100px">
                                <label >{{selectTable.createTime}}</label>
                            </el-form-item>
                        </el-col>
                        <el-col :span="24" >
                            <el-form-item v-if="!falg" label="回复内容：" label-width="100px">
                                <label :title="selectTable.back" style="display:inline-block;width: 400px;">{{selectTable.back}}</label>
                            </el-form-item>
                            <el-form-item label="回复：" label-width="100px"  v-if="falg">
                                <el-input v-model="selectTable.back"  type="textarea" :rows="3" :max='300' style="width: 400px;"  show-word-lmit></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                </el-form>
                <div slot="footer" class="dialog-footer" v-if="falg">
                    <el-button @click="dialogFormVisible = false">取 消</el-button>
                    <el-button type="primary" @click="updateBack">确 定</el-button>
                </div>
            </el-dialog>
        </div>
    </div>
</template>

<script>
    import headTop from '../../components/headTop'
    export default {
    	data(){
    		return {
    			query:{name:'',type:'',status:''},
    			tableData: [],
    			selectTable:{},
    			dialogFormVisible:false,
    			currentRow: null,
    			offset: 0,
    			limit: 15,
    			count: 0,
    			currentPage: 1,
                falg:true,
                type:["","服务态度好","排队时间短","效率高","门店环境好","很专业"],
    			options: [{
    				value: '1',
    				label: '1分'
    			}, {
    				value: '2',
    				label: '2分'
    			}, {
    				value: '3',
    				label: '3分'
    			}, {
    				value: '4',
    				label: '4分'
    			}, {
    				value: '5',
    				label: '5分'
    			}],
                backs:[{value: 1, label: '已回复'},{value: 0, label: '待回复'}]
    		}
    	},
    	components: {
    		headTop,
    	},
    	created(){
    		this.getComment();
    	},
    	methods: {
    		handleSizeChange() {
    		},
    		handleCurrentChange(val) {
    			this.currentPage = val;
    			this.offset = (val - 1)*this.limit;
    			this.getComment();
    		},
    		handleXq(falg, row) {
    			this.selectTable = Object.assign({},row);
    			this.dialogFormVisible = true;
    			this.falg = falg;
    		},
    		resetData(){
    			this.query = {};
    			this.getComment();
    		},
    		getComment(){
    			this.query.offset = this.offset;
    			this.query.limit = this.limit;
    			const url = window.fdConfig.url.feature.comment;
    			const _this = this;
    			this.$http.get(url,{
    				params:_this.query
    			}).then(function(res){
    				const data = res.body;
    				_this.count = data.pageInfo.rowCount;
    				const comments =  data.data;
    				console.log(comments);
    				for (var i = 0;i<comments.length;i++){
                        comments[i].typeValue = "";
                        comments[i].star += '分';
                        var types = comments[i].type.split(';');
                        for(var j =0 ;j<types.length -1;j++) {
                            comments[i].typeValue += _this.type[types[j]] + "  ";
                        }
    				}
    				this.tableData = comments;
    			},function(){
    			});
    		},
            updateBack(){
                const url = window.fdConfig.url.feature.commentBack;
                const _this = this;
                var data = {
                    id:this.selectTable.id,
                    back:this.selectTable.back
                }
                this.$http.post(url,data
                ).then(function(){
                    this.$notify({
                        title: '成功',
                        message: '回复成功',
                        type: 'success'
                    });
                    _this.dialogFormVisible = false;
                    _this.getComment();
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
    .table_container{
        padding: 20px;
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
</style>
