<template>
    <div class="line1">
        <div id="year" class="" style="width: 90%;height:450px;"></div>
    </div>
</template>

<script>
    import echarts from 'echarts/lib/echarts';
    // 引入柱状图
    import 'echarts/lib/chart/bar';
    import 'echarts/lib/chart/line';
    import 'echarts/lib/component/title';
    import 'echarts/lib/component/legend';
    import 'echarts/lib/component/toolbox';
    import 'echarts/lib/component/markPoint';
    import 'echarts/lib/component/tooltip';
    export default {
        mounted(){
            this.myChart = echarts.init(document.getElementById('year'));
            this.initData();
        },
        props: ['sevenDate', 'sevenDay'],
        methods: {
            initData(){
                const option = {
                    title : {
                        text: '订单量',
                    },
                    toolbox: {
                        show : true,
                        feature : {
                            // mark : {show: true},
                            // dataView : {show: true, readOnly: false},
                            magicType : {show: true, type: ['line', 'bar']},
                            restore : {show: true},
                            saveAsImage : {show: true}
                        }
                    },
                    xAxis: {
                        type: 'category',
                        data: this.sevenDay
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [{
                        data: this.sevenDate,
                        type: 'bar',
                        markPoint : {
                          data : [
                              {type : 'max', name: '最大值'},
                              {type : 'min', name: '最小值'}
                              ]
                        },
                        markLine : {
                            data : [
                                {type : 'average', name: '平均值'}
                            ]
                        }
                    }]
                };
                this.myChart.setOption(option);
            }
        },
        watch: {
            sevenDate: function (){
                this.initData()
            },
            sevenDay: function (){
                this.initData()
            }
        }
    }
</script>

<style lang="less">
	@import '../style/mixin';
    .line1{
        display: flex;
        justify-content: center;
    }
</style>
