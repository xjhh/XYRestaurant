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
    <title>点菜</title>
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
<div class="wrapper wrapper-content " style="height: 100%;">
    <H1>当前消费桌台是： ${deskNumber}</H1>
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
                            <h1>当前已选<span class="goodSize" style="color: red">0</span>件商品<button class="btn btn-success" onclick="see(0)">查看</button></h1>
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
        <div class="col-sm-1" style="margin-top: 433px;text-align: center" onclick="see(1)">

            <img src="../../img/ic_next.png" >
            <h5 style="color: red;text-align: center">下一步</h5>
            <form id="buyForm">
                <input id="memberCard" name="memberCard" value="0" type="hidden">
                <input id="deskId" name="deskId" value="${deskId}" type="hidden">
                <input id="goodId" name="goodId" type="hidden">
            </form>
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
<script type="text/javascript" src="../../js/addConsume/buy.js"></script>

</body>
</html>
