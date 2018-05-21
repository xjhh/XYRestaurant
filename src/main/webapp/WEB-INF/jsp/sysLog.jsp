<%--
  Created by IntelliJ IDEA.
  User: 98019
  Date: 2018/4/15
  Time: 14:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>系統日志</title>
    <meta name="description" content="">
    <link href="../../css/bootstrap.min.css" rel="stylesheet">
    <link href="../../css/font-awesome.css" rel="stylesheet">
    <link href="../../css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
    <link href="../../css/plugins/jsTree/style.min.css" rel="stylesheet">
    <link href="../../css/plugins/jqTreeGrid/jquery.treegrid.css" rel="stylesheet">
    <link href="../../css/plugins/summernote/summernote-0.8.8.css" rel="stylesheet">
    <link href="../../css/animate.css" rel="stylesheet">
    <link href="../../css/plugins/chosen/chosen.css" rel="stylesheet">
    <link href="../../css/style.css" rel="stylesheet">
</head>
<body>
<div class="wrapper wrapper-content ">
    <div class="row">
        <div class="ibox">
            <div class="ibox-body">
                <div class="fixed-table-toolbar">
                    <div class="columns pull-right">
                        <button class="btn btn-success" onclick="reLoad()">查询</button>
                    </div>

                    <div class="columns pull-right col-md-2 nopadding">
                        <input id="s_id" type="text" class="form-control"
                               placeholder="用戶ID">
                        <input id="power" type="hidden" value="-1"/>
                    </div>
                </div>
                <table id="exampleTable" data-mobile-responsive="true">
                </table>
            </div>
        </div>
    </div>
</div>
<script src="../../js/jquery-1.11.0.min.js"></script>
<script src="../../js/bootstrap.min.js"></script>
<script src="../../js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
<script src="../../js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
<script src="../../js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="../../js/plugins/validate/jquery.validate.min.js"></script>
<script src="../../js/plugins/validate/messages_zh.min.js"></script>
<script src="../../js/plugins/jsTree/jstree.min.js"></script>
<script src="../../js/plugins/jqTreeGrid/jquery.treegrid.min.js"></script>
<script src="../../js/plugins/jqTreeGrid/jquery.treegrid.extension.js"></script>
<script src="../../js/plugins/jqTreeGrid/jquery.treegrid.bootstrap3.js"></script>
<script src="../../js/plugins/chosen/chosen.jquery.js"></script>
<script src="../../js/plugins/layer/layer.js"></script>
<script src="../../js/content.js"></script>
<script src="../../js/ajax-util.js"></script>
<script>
    var prefix = "/sys/log"
    $(function () {
        load();

    });
    $('#exampleTable').on('load-success.bs.table', function (e, data) {
        if (data.total && !data.rows.length) {
            $('#exampleTable').bootstrapTable('selectPage').bootstrapTable('refresh');
        }
    });

    function load() {
        $('#exampleTable')
            .bootstrapTable(
                {
                    method: 'get', // 服务器数据的请求方式 get or post
                    url: prefix + "/list", // 服务器数据的加载地址
                    toolbar: '#exampleToolbar',
                    striped: true, // 设置为true会有隔行变色效果
                    // 服务器返回的数据类型
                    pagination: true, // 设置为true会在底部显示分页条
                    pageSize: 7, // 如果设置了分页，每页数据条数
                    pageNumber: 1,
                    queryParams: function (params) {
                        return {
                            // 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                            id: $('#s_id').val(),
                            // type:$('#power').val()
                        };
                    },
                    columns: [
                        {
                            field: 'logId',
                            align: 'center',
                            title: '序号'
                        },
                        {
                            field: 'logUserId',
                            align: 'center',
                            title: '操作员ID'
                        },
                        {
                            field: 'logOperate',
                            align: 'center',
                            title: '操作描述'
                        },
                        {
                            field: 'logMethodFunction',
                            align: 'center',
                            title: '方法名'
                        },
                        {
                            field: 'logParameter',
                            align: 'center',
                            title: '参数'
                        },
                        {
                            field: 'logErrorMsg',
                            align: 'center',
                            title: '错误日志'
                        },
                        {
                            field: 'logTerminal',
                            align: 'center',
                            title: '终端'
                        },
                        {
                            field: 'logStatus',
                            align: 'center',
                            title: '状态',
                            formatter: function (item, index) {
                                if (item == 0) {
                                    return '<span class="label label-primary">成功</span>';
                                }
                                if (item == 1) {
                                    return '<span class="label label-warning">失败</span>';
                                }
                            }
                        },
                        {
                            field: 'logTime',
                            align: 'center',
                            title: '时间'
                        }
                        ]
                });
    }

    function reLoad() {
        $('#exampleTable').bootstrapTable('refresh');
    }

</script>

</body>
</html>
