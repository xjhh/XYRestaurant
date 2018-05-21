<%--
  Created by IntelliJ IDEA.
  User: 98019
  Date: 2018/5/18
  Time: 0:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>餐厅收益</title>
    <link rel="shortcut icon" href="favicon.ico">
    <link href="/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="/css/font-awesome.css?v=4.4.0" rel="stylesheet">

    <link href="/css/animate.css" rel="stylesheet">
    <link href="/css/style.css?v=4.1.0" rel="stylesheet">
</head>
<body>
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="columns pull-left">
        <button type="button"
                class="btn  btn-primary" onclick="charts(0)">
            <i class="fa fa-plus hidden" aria-hidden="true"></i>折线图
        </button>
        <button type="button"
                class="btn  btn-danger" onclick="charts(1)">
            <i class="fa fa-trash hidden" aria-hidden="true"></i>柱状图
        </button>
    </div>
    <div id="chart" style="width:100%;height: 750px"></div>
</div>
<script src="/js/jquery-1.11.0.min.js"></script>
<script src="/js/bootstrap.min.js"></script>

<!-- ECharts -->
<script src="/js/plugins/echarts/echarts-all.js"></script>

<script type="text/javascript">
    $(function () {
        charts(0)
    })
    function charts(type) {
        $.getJSON("/income/getData", function (data) {
            var myChart = echarts.init(document.getElementById("chart"));   //这里不允许使用JQuery简化
            var lineoption = {
                title: {
                    text: '湘乐餐厅收益情况'
                },
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    data: ['纯收入', '支出', '收益']
                },
                toolbox: {
                    show: true,
                    feature: {
                        magicType: {show: true, type: ['stack', 'tiled']},
                        saveAsImage: {show: true}
                    }
                },
                xAxis: {
                    type: 'category',
                    boundaryGap: false,
                    data: data.times
                },
                yAxis: {
                    type: 'value'
                },
                dataZoom: {
                    show: true,
                },
                series: [
                    {
                        name: '纯收入',
                        type: 'line',
                        smooth: true,
                        data: data.nets
                    },
                    {
                        name: '支出',
                        type: 'line',
                        smooth: true,
                        data: data.expends
                    },
                    {
                        name: '收益',
                        type: 'line',
                        smooth: true,
                        data: data.incomes
                    }]
            }
            var baroption = {
                title : {
                    text: '湘乐餐厅收益情况'
                },
                tooltip : {
                    trigger: 'axis'
                },
                legend: {
                    data: ['纯收入', '支出', '收益']
                },
                grid:{
                    x:30,
                    x2:40,
                    y2:24
                },
                calculable : true,
                xAxis : [
                    {
                        type : 'category',
                        data: data.times
                    }
                ],
                yAxis : [
                    {
                        type : 'value'
                    }
                ],
                dataZoom: {
                    show: true,
                },
                series : [
                    {
                        name: '纯收入',
                        type: 'bar',
                        smooth: true,
                        data: data.nets
                    },
                    {
                        name: '支出',
                        type: 'bar',
                        smooth: true,
                        data: data.expends
                    },
                    {
                        name: '收益',
                        type: 'bar',
                        smooth: true,
                        data: data.incomes
                    }]
            };
            if(type == 0){
                myChart.setOption(lineoption);
            }else{
                myChart.setOption(baroption);
            }
        });
    }
</script>
</body>
</html>
