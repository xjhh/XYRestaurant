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
    <title>桌台管理</title>
    <link rel="shortcut icon" href="favicon.ico">
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/font-awesome.min.css" rel="stylesheet">
    <link href="../css/animate.css" rel="stylesheet">
    <link href="../css/layui.css" rel="stylesheet">
    <link href="../css/style.css" rel="stylesheet">

    <style>
        .aactive {
            color: #13b5b7 !important;
        }
        .hactive:hover{
            color: #13b5b7 !important;
        }
        .desk_info{
            float: left;
            width: 59px;
        }
        .emptyList{
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
                        <button type="button" class="layui-btn" id="test1" v-on:click="add()">
                            <i class="fa fa-cloud"></i>添加餐桌
                        </button>
                        <div class="hr-line-dashed"></div>
                        <ul class="folder-list" style="padding: 0">
                            <li><a href="javascript:void(0)" class="file-control hactive" :class="{'aactive':type === -1}"
                                   v-on:click="changeType(-1)"><i class="fa fa-folder"></i>所有文件</a></li>

                            <li><a href="javascript:void(0)" class="file-control hactive" :class="{'aactive':type === 2}"
                                   v-on:click="changeType(2)"><i class="fa fa-folder"></i>二人桌</a></li>

                            <li><a href="javascript:void(0)" class="file-control hactive" :class="{'aactive':type === 4}"
                                   v-on:click="changeType(4)" ><i class="fa fa-folder"></i>四人桌</a>
                            </li>

                            <li><a href="javascript:void(0)" v-on:click="changeType(8)" :class="{'aactive':type === 8}"
                                   class="file-control hactive"><i class="fa fa-folder"></i>八人桌</a></li>

                            <li><a href="javascript:void(0)" v-on:click="changeType(10)" :class="{'aactive':type === 10}"
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
                                        <li class="desk_info"  style="color: #ffffff;  text-align: center;">{{row.deskType}}人桌</li>
                                        <li class="desk_info"  style="color: #ffffff; text-align: end; font-size: 19px;">

                                            <b v-if="row.deskState === 0">
                                                无客
                                            </b>
                                            <b v-else-if="row.deskState === 1">
                                                有客
                                            </b>
                                        </li>
                                    </ul>
                                </div>
                                &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp;<button
                                    class="btn btn-warning btn-xs" @click="update(row.deskId)">修改状态
                            </button>&nbsp; &nbsp; &nbsp; &nbsp;<button class="btn btn-danger btn-xs"
                                                                        @click="remove(row.deskId)">删除
                            </button>
                            </a>
                        </div>
                    </div>
                    <div id="incomeNum"></div>
                </div>
            </div>
            <%--<div>--%>
                <%--<ul id="page"></ul>--%>
            <%--</div>--%>
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
            limit: 1,
            offset: 0,
            total: 0,
            type: '-1',
            typeName:"",
            emptyList: "false",
            activeColor: 'green',
            rows: '',
        },
        methods: {
            getData: function () {
                $.getJSON("/desk/list", {
                    limit: this.limit,
                    offset: this.offset,
                    type: this.type
                }, function (r) {
                    app.emptyList = false;
                    if(r.total == 0){
                        app.emptyList = true;
                    }
                    app.typeName = app.type+" 人桌";
                    if(app.type == -1){
                        app.typeName = "";
                    }
                    app.total = r.total;
                    app.rows = r.rows;
                    app.page();
                });
            },
            // page: function () {
            //     var options = {
            //         currentPage: app.offset / 12 + 1, //当前页
            //         totalPages: app.total / (12 + 1) + 1, //总页数
            //         numberofPages: 4, //显示的页数
            //         bootstrapMajorVersion: 3,
            //         alignment: 'center',
            //         size: 'large',
            //         shouldShowPage: true,
            //         itemTexts: function (type, page, current) { //修改显示文字
            //             switch (type) {
            //                 case "first":
            //                     return "首页";
            //                 case "prev":
            //                     return "上一页";
            //                 case "next":
            //                     return "下一页";
            //                 case "last":
            //                     return "尾页";
            //                 case "page":
            //                     return page;
            //             }
            //         },
            //         onPageClicked: function (event, originalEvent, type, page) {
            //             app.offset = (page - 1) * 12;
            //             app.getData();
            //         }
            //     };
            //     $('#page').bootstrapPaginator(options);
            // },
            add:function(){

                layer.open({
                    type: 2,
                    title: '添加桌台',
                    maxmin: true,
                    shadeClose: false, // 点击遮罩关闭层
                    area: ['800px', '520px'],
                    content: 'add'
                });
            },
            update: function (id) {
                layer.confirm('确定要修改选中的桌台的状态？', {
                    btn: ['确定', '取消']
                }, function () {
                    $.ajax({
                        url: "/desk/editStatus",
                        type: "post",
                        data: {
                            'id': id
                        },
                        success: function (r) {
                            var obj = JSON.parse(r);
                            if (obj.result) {
                                layer.msg("修改成功");
                            } else {
                                layer.msg(obj.error);
                            }
                            app.getData();
                        }
                    });
                })
            },
            remove: function (id) {
                layer.confirm('确定要删除选中的桌台？', {
                    btn: ['确定', '取消']
                }, function () {
                    $.ajax({
                        url: "/desk/remove",
                        type: "post",
                        data: {
                            'id': id
                        },
                        success: function (r) {
                            var obj = JSON.parse(r);
                            if (obj.result) {
                                layer.msg("删除成功");
                            } else {
                                layer.msg(obj.error);
                            }
                            app.getData();
                        }
                    });
                })
            },
            changeType: function (i) {
                this.type = i;
                this.offset = 0;
                this.getData();
            }
        },
        created: function () {
            this.changeType('')
        }
    });
</script>
</body>
</html>
