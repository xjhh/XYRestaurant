<%--
  Created by IntelliJ IDEA.
  User: 98019
  Date: 2018/4/29
  Time: 20:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>点菜</title>
    <link rel="shortcut icon" href="favicon.ico">
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/font-awesome.min.css" rel="stylesheet">
    <link href="../css/animate.css" rel="stylesheet">
    <link href="../css/layui.css" rel="stylesheet">
    <link href="../css/style.css" rel="stylesheet">

    <style>
        .aactive {
            color: white !important;
            background-color: red;
        }

        .hactive:hover {
            color: white !important;
            background-color: #13b5b7;
        }

        .desk_info {
            float: left;
            width: 59px;
        }

        .emptyList {
            padding-top: 20px;
            padding-bottom: 20px;
            text-align: center;
            background-color: antiquewhite;
            color: brown;
            font-weight: bolder;
            font-size: 24px;
        }
    </style>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content" id="app">
    <div class="row">
        <div class="col-sm-3">
            <div class="ibox float-e-margins">
                <div class="ibox-content">
                    <div class="file-manager">
                        <input id="searchStatus2" name="searchStatus" type="radio" value="-1" checked
                               v-on:click="changeStatus()">
                        <label for="searchStatus2" class="layui-btn" style="background-color: #0e9aef"
                               v-on:click="changeStatus()">
                            <i class="fa fa-cloud"></i>所有
                        </label>
                        <input id="searchStatus0" name="searchStatus" type="radio" value="0"
                               v-on:click="changeStatus()">
                        <label for="searchStatus0" class="layui-btn" v-on:click="changeStatus()">
                            <i class="fa fa-cloud"></i>无客
                        </label>
                        <input id="searchStatus1" name="searchStatus" type="radio" value="1"
                               v-on:click="changeStatus()">
                        <label for="searchStatus1" class="layui-btn " style="background-color: red"
                               v-on:click="changeStatus()">
                            <i class="fa fa-cloud"></i>有客
                        </label>
                        <div class="hr-line-dashed"></div>
                        <ul class="folder-list" style="padding: 0">
                            <li><a href="javascript:void(0)" class="file-control hactive"
                                   :class="{'aactive':type === -1}"
                                   v-on:click="changeType(-1)"><i class="fa fa-folder"></i>所有文件</a></li>

                            <li><a href="javascript:void(0)" class="file-control hactive"
                                   :class="{'aactive':type === 2}"
                                   v-on:click="changeType(2)"><i class="fa fa-folder"></i>二人桌</a></li>

                            <li><a href="javascript:void(0)" class="file-control hactive"
                                   :class="{'aactive':type === 4}"
                                   v-on:click="changeType(4)"><i class="fa fa-folder"></i>四人桌</a>
                            </li>

                            <li><a href="javascript:void(0)" v-on:click="changeType(8)" :class="{'aactive':type === 8}"
                                   class="file-control hactive"><i class="fa fa-folder"></i>八人桌</a></li>

                            <li><a href="javascript:void(0)" v-on:click="changeType(10)"
                                   :class="{'aactive':type === 10}"
                                   class="file-control hactive"><i class="fa fa-folder"></i>十人桌</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-sm-9 emptyList" v-show="emptyList">
            <p>没有{{typeName}}类型的桌台</p>
        </div>
        <div class="col-sm-9 animated fadeInRight">
            <div class="row">
                <div class="col-sm-12">
                    <div class="file-box" v-for="row in rows">
                        <div class="file">
                            <a href="#">
                                <span class="corner"></span>
                                <div class="image">
                                    <img alt="image" class="img-responsive" :src="row.url">
                                </div>
                                <div class="file-name" :style="{backgroundColor: row.color}">
                                    <%--<br/>--%>
                                    <ul>
                                        <li class="desk_info" style="color: #ffffff;">编号：{{row.deskNumber}}</li>
                                        <li class="desk_info" style="color: #ffffff;  text-align: center;">
                                            {{row.deskType}}人桌
                                        </li>
                                        <li class="desk_info" style="color: #ffffff; text-align: end; font-size: 19px;">

                                            <b v-if="row.deskState === 0">
                                                无客
                                            </b>
                                            <b v-else-if="row.deskState === 1">
                                                有客
                                            </b>
                                        </li>
                                    </ul>
                                </div>
                                <button v-if="row.deskState === 0"
                                        class="btn btn-danger btn-xs" style="width: 100%;" @click="buy(row.deskId)">
                                    点菜
                                </button>
                                <div v-else-if="row.deskState === 1">
                                <span class="btn btn-danger btn-xs" style="width: 50%;background-color: darkslategray;"
                                      @click="update(row.deskId)">
                                    修改消费菜单
                                </span>
                                    <span class="btn btn-danger btn-xs" style="width: 50%;position: absolute;"
                                          @click="clearing(row.deskId)">
                                    结算
                                </span>
                                </div>
                            </a>
                        </div>
                    </div>
                    <div id="incomeNum"></div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 全局js -->
<script src="../js/jquery-1.11.0.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script src="../js/bootstrap-paginator.min.js"></script>

<script src="../js/content.js?v=1.0.0"></script>
<script src="../js/layui.js"></script>
<script src="../js/plugins/clipboard/clipboard.min.js"></script>
<script src="../js/plugins/layer/layer.min.js"></script>
<script src="../js/vue.min.js"></script>

<script>
    var app = new Vue({
        el: '#app',
        data: {
            total: 0,
            type: '-1',
            typeName: "",
            emptyList: "false",
            activeColor: 'green',
            rows: '',
        },
        methods: {
            getDeskData: function () {
                $.getJSON("/addConsume/deskList", {
                    status: $(":radio[name = 'searchStatus']:checked").val(),
                    type: this.type
                }, function (r) {
                    app.emptyList = false;
                    if (r.total == 0) {
                        app.emptyList = true;
                    }
                    app.typeName = app.type + " 人桌";
                    if (app.type == -1) {
                        app.typeName = "";
                    }
                    app.total = r.total;
                    app.rows = r.rows;
                    // app.page();
                });
            },
            buy: function (id) {

                layer.open({
                    type: 2,
                    title: '点菜',
                    maxmin: true,
                    shadeClose: false, // 点击遮罩关闭层
                    area: ['1500px', '720px'],
                    content: '/addConsume/buy/' + id
                });
            },
            update: function (id) {
                layer.open({
                    type: 2,
                    title: '点菜',
                    maxmin: true,
                    traditional: true,
                    shadeClose: false, // 点击遮罩关闭层
                    area: ['1030px', '600px'],
                    content: 'update/' + id
                });
            },
            clearing: function (id) {
                layer.open({
                    type: 2,
                    title: '记录详情',
                    maxmin: true,
                    shadeClose: false, // 点击遮罩关闭层
                    area: ['800px', '520px'],
                    content: '/consume/see/' + id + "/true"  // iframe的url
                });
            },
            changeType: function (i) {
                this.type = i;
                this.getDeskData();
            },
            changeStatus: function () {
                this.getDeskData();
            }
        },
        created: function () {
            this.changeType('')
        }
    });


    function re() {
        app.getDeskData()
    }

</script>
</body>
</html>
