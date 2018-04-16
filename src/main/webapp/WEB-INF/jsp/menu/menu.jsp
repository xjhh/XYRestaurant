<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="shortcut icon" href="favicon.ico">
    <link href="../../css/bootstrap.min.css" rel="stylesheet">
    <link href="../../css/font-awesome.css" rel="stylesheet">
    <link href="../../css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
    <link href="../../css/plugins/jsTree/style.min.css" rel="stylesheet">
    <link href="../../css/plugins/jqTreeGrid/jquery.treegrid.css" rel="stylesheet">
    <!--summernote css -->
    <link href="../../css/plugins/summernote/summernote-0.8.8.css" rel="stylesheet">
    <link href="../../css/animate.css" rel="stylesheet">
    <link href="../../css/plugins/chosen/chosen.css" rel="stylesheet">
    <link href="../../css/style.css" rel="stylesheet">
</head>
<body>
<div class="wrapper wrapper-content ">
    <div class="col-sm-12">
        <div class="ibox">
            <div class="ibox-body">
                <div id="exampleToolbar" role="group" class="t-bar">
                    <button type="button" class="btn btn-primary" title="在根节点下添加菜单" onclick="add('0')">
                        <i class="fa fa-plus" aria-hidden="true"></i>添加
                    </button>
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
<!--summernote-->
<script src="../../js/plugins/summernote/summernote.js"></script>
<script src="../../js/plugins/summernote/summernote-zh-CN.min.js"></script>
<script src="../../js/ajax-util.js"></script>
<script type="text/javascript" src="../../js/sys/menu/menu.js"></script>
</body>
</html>
