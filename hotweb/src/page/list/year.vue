<template>
    <div>
		<section class="data_section">
			<header class="section_title">数据统计</header>
		</section>
		<tendency :sevenDate='sevenDate' :sevenDay='sevenDay'></tendency>
    </div>
</template>

<script>
	import headTop from '../../components/headTop'
	import tendency from '../../components/tendency'
	import dtime from 'time-formater'
    export default {
    	data(){
    		return {
    			sevenDay: [],
    			sevenDate: [],
    		}
    	},
    	components: {
    		headTop,
    		tendency,
    	},
        created(){
    		this.initData();
    		var array = [];
    		for (var i = 1; i <=12; i++) {
                this.sevenDay.push(i.toString() + '月');
    		}
    		console.log(this.sevenDay)
    	},
        computed: {

        },
    	methods: {
            async initData(){
                const url = window.fdConfig.url.feature.orderYear;
                const _this = this;
                var array = new Array(12);
                for(var i =0 ;i<array.length;i++){
                    array[i] = 0;
                }
                this.$http.get(url).then(function(res){
                    var data = res.body.data;
                    console.log("本年订单，",data);
                    for(var i =0 ;i<data.length;i++){
                        var time =new Date(data[i].createTime);
                        array[time.getMonth()] += 1;
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
