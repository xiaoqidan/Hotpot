<template>
    <div>
		<section class="data_section">
			<header class="section_title">数据统计</header>
			<el-row  style="margin-bottom: 10px;">
                <el-col :span="7"></el-col>
                <el-col :span="8"><el-button type="primary"  @click="upMonth">上个月</el-button></el-col>
                <el-col :span="9"><el-button type="primary"  @click="downMonth">下个月</el-button></el-col>
			</el-row>
		</section>
		<tendency :sevenDate='sevenDate' :sevenDay='sevenDay'></tendency>
    </div>
</template>

<script>
	import headTop from '../../components/headTop'
	import tendency from '../../components/tendency'
	import dtime from 'time-formater'
	import {userCount, orderCount, getUserCount, getOrderCount, adminDayCount, adminCount} from '@/api/getData'
    export default {
        data(){
            return {
                sevenDay: [],
                sevenDate: [],
                day:'',
                today:'',
            }
        },
        components: {
            headTop,
            tendency,
        },
        created(){
            const today = new Date();
            this.today = today;
            console.log("当前日期：",dtime(today).format('YYYY-MM'));
            this.day = this.getMonthDay(today.getFullYear(),today.getMonth());
            this.initData(dtime(new Date()).format('YYYY-MM'));
            var array = [];
            for (let i = 1; i <= this.day; i++) {
                this.sevenDay.push(i.toString() + '号');
            }
            console.log(this.sevenDay)
        },
        computed: {

        },
        methods: {
             getMonthDay(year, month) {
                let days = new Date(year, month + 1, 0).getDate();
                return days
            },
            upMonth(){
                var fyears = this.today.getFullYear()
                    ,fmoths = this.today.getMonth()
                    ,fdate_sgy=new Date(fyears , fmoths-1 , this.today.getDate());//上个月data
                this.today = fdate_sgy;
                console.log(fdate_sgy);
                this.day = this.getMonthDay(fdate_sgy.getFullYear(),fdate_sgy.getMonth());
                this.sevenDay = [];
                for (let i = 1; i <= this.day; i++) {
                    this.sevenDay.push(i.toString() + '号');
                }
                fdate_sgy = dtime(this.today).format('YYYY-MM');
                this.initData(fdate_sgy);
            },
            downMonth(){
                var fyears = this.today.getFullYear()
                    ,fmoths = this.today.getMonth()
                    ,fdate_sgy=new Date(fyears , fmoths+1 , this.today.getDate());//上个月data
                this.today = fdate_sgy;
                this.day = this.getMonthDay(fdate_sgy.getFullYear(),fdate_sgy.getMonth());
                this.sevenDay = [];
                for (let i = 1; i <= this.day; i++) {
                    this.sevenDay.push(i.toString() + '号');
                }
                fdate_sgy = dtime(this.today).format('YYYY-MM');
                this.initData(fdate_sgy);
            },
             initData(day){
                const url = window.fdConfig.url.feature.orderMonth;
                const _this = this;
                const param = {
                    date:day
                };
                var array = new Array(this.day);
                for(var i =0 ;i<array.length;i++){
                    array[i] = 0;
                }
                this.$http.get(url,{
                    params:param
                }).then(function(res){
                    var data = res.body.data;
                    console.log("本月订单，",data);
                    for(var i =0 ;i<data.length;i++){
                        var time =new Date(data[i].createTime);
                        array[time.getDate()-1] += 1;
                    }
                    _this.sevenDate = array;
                    console.log(_this.sevenDate);
                },function(){
                });
            },
        }
    }
</script>

<style lang="less">
	@import '../../style/mixin';
    .el-col{
        border:1px solid transparent
    }
	.data_section{
		padding: 20px;
		margin-bottom: 40px;
		.section_title{
			text-align: center;
			font-size: 30px;
			margin-bottom: 10px;
		}
		.data_list{
			text-align: center;
			font-size: 14px;
			color: #666;
            border-radius: 6px;
            background: #E5E9F2;
            .data_num{
                color: #333;
                font-size: 26px;

            }
            .head{
                border-radius: 6px;
                font-size: 22px;
                padding: 4px 0;
                color: #fff;
                display: inline-block;
            }
        }
        .today_head{
            background: #FF9800;
        }
        .all_head{
            background: #20A0FF;
        }
	}
    .wan{
        .sc(16px, #333)
    }
</style>
