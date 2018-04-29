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
    <title>商品管理</title>
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
        <div class="col-sm-3">
            <div class="ibox ibox-body">
                <div class="ibox-title">
                    <h5>选择商品类别</h5>
                </div>
                <div class="ibox-content">
                    <div id="jstree"></div>
                </div>
            </div>
        </div>
        <div class="col-sm-8">
            <div class="ibox">
                <div class="ibox-body">
                    <div class="fixed-table-toolbar">
                        <div class="columns pull-left">
                            <button  type="button"
                                    class="btn  btn-primary" onclick="add()">
                                <i class="fa fa-plus hidden" aria-hidden="true"></i>添加
                            </button>
                            <button type="button"
                                    class="btn  btn-danger" onclick="batchRemove()">
                                <i class="fa fa-trash hidden" aria-hidden="true"></i>删除
                            </button>
                        </div>
                        <div class="columns pull-right">
                            <button class="btn btn-success" onclick="reLoad()">查询</button>
                        </div>

                        <div class="columns pull-right col-md-2 nopadding">
                            <input id="searchName" type="text" class="form-control"
                                   placeholder="商品名称">
                            <input id="goodsType" type="hidden" value="-1"/>
                        </div>
                    </div>
                    <table id="exampleTable" data-mobile-responsive="true">
                    </table>
                </div>
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
<script src="../../js/plugins/validate/jquery.validate.min.js"></script>
<script src="../../js/plugins/validate/jquery.validate.extend.js"></script>
<script src="../../js/plugins/validate/messages_zh.min.js"></script>
<script type="text/javascript" src="../../js/goods/goods.js"></script>

</body>
</html>
