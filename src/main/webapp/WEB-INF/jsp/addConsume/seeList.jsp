<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>已选商品列表</title>
    <meta name="description" content="">
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/font-awesome.css" rel="stylesheet">
    <link href="/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
    <link href="/css/plugins/jsTree/style.min.css" rel="stylesheet">
    <link href="/css/plugins/jqTreeGrid/jquery.treegrid.css" rel="stylesheet">
    <link href="/css/plugins/summernote/summernote-0.8.8.css" rel="stylesheet">
    <link href="/css/animate.css" rel="stylesheet">
    <link href="/css/plugins/chosen/chosen.css" rel="stylesheet">
    <link href="/css/style.css" rel="stylesheet">
</head>
<body>
<div class="wrapper wrapper-content" style="height: 100%">
    <div class="row">
        <div class="col-sm-11">
            <div class="ibox">
                <div class="ibox-body">
                    <h3>当前已选<span class="goodSize" style="color: red"></span> &nbsp;&nbsp;&nbsp;&nbsp;
                        总金额：<span style="color: red" class="total"></span> </h3>
                    <table id="exampleTable" data-mobile-responsive="true">
                        </table>
                </div>
            </div>
        </div>
        <c:if test="${isNextShow == 1}">
            <div class="col-sm-1" style="margin-top: 200px;text-align: center" onclick="parent.buy()">
                <img src="/img/ic_next.png">
                <h5 style="color: red;text-align: center">确定点菜</h5>
            </div>
        </c:if>
        <c:if test="${isNextShow == 2}">
            <div class="col-sm-1" style="margin-top: 200px;text-align: center" onclick="updateGoods()">
                <img src="/img/ic_next.png">
                <input type="hidden" name="consumeId" id="consumeId" value="${consume.consumeId}">
                <input type="hidden" name="goodId" id="goodId" value="${consume.goodId}">
                <input type="hidden" name="seeType" id="seeType" value="${isNextShow}">
            </div>
        </c:if>
    </div>
</div>
<script src="/js/jquery-1.11.0.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
<script src="/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
<script src="/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="/js/plugins/validate/jquery.validate.min.js"></script>
<script src="/js/plugins/validate/messages_zh.min.js"></script>
<script src="/js/plugins/jsTree/jstree.min.js"></script>
<script src="/js/plugins/jqTreeGrid/jquery.treegrid.min.js"></script>
<script src="/js/plugins/jqTreeGrid/jquery.treegrid.extension.js"></script>
<script src="/js/plugins/jqTreeGrid/jquery.treegrid.bootstrap3.js"></script>
<script src="/js/plugins/chosen/chosen.jquery.js"></script>
<script src="/js/plugins/layer/layer.js"></script>
<script src="/js/content.js"></script>
<!--summernot/-->
<script src="/js/plugins/summernote/summernote.js"></script>
<script src="/js/plugins/summernote/summernote-zh-CN.min.js"></script>
<script src="/js/ajax-util.js"></script>
<script src="/js/plugins/validate/jquery.validate.min.js"></script>
<script src="/js/plugins/validate/jquery.validate.extend.js"></script>
<script src="/js/plugins/validate/messages_zh.min.js"></script>
<script type="text/javascript" src="/js/addConsume/seeList.js"></script>

<script>
    $(function () {
        layer.closeAll('loading');
    });
</script>
</body>
</html>
