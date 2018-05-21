<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>编辑商品类别</title>
    <link rel="shortcut icon" href="favicon.ico">
    <link href="../../../css/bootstrap.min.css" rel="stylesheet">
    <link href="../../../css/font-awesome.css" rel="stylesheet">
    <link href="../../../css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
    <link href="../../../css/plugins/jsTree/style.min.css" rel="stylesheet">
    <link href="../../../css/plugins/jqTreeGrid/jquery.treegrid.css" rel="stylesheet">
    <!--summernote css -->
    <link href="../../../css/plugins/summernote/summernote-0.8.8.css" rel="stylesheet">
    <link href="../../../css/animate.css" rel="stylesheet">
    <link href="../../../css/plugins/chosen/chosen.css" rel="stylesheet">
    <link href="../../../css/style.css" rel="stylesheet">
</head>
<body>
<div class="wrapper wrapper-content">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-content">
                    <form class="form-horizontal m-t" id="signupForm">

                        <div class="form-group">
                            <label class="col-sm-3 control-label">采购商品名称：</label>
                            <div class="col-sm-8">
                                <input id="purchaseName" name="purchaseName" class="form-control"  value="${p.purchaseName}" type="text">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">采购商品单价：</label>
                            <div class="col-sm-8">
                                <input id="purchasePrice" name="purchasePrice" oninput="clearNoNum(this)" value="${p.purchasePrice}" class="form-control" type="text">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">采购商品数量：</label>
                            <div class="col-sm-8">
                                <input id="purchaseNumber" name="purchaseNumber" oninput="updateProductNumber()" value="${p.purchaseNumber}" class="form-control" type="number">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">采购地址：</label>
                            <div class="col-sm-8">
                                <input id="purchaseAddr" name="purchaseAddr" class="form-control"  value="${p.purchaseAddr}" type="text">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">采购总价：</label>
                            <div class="col-sm-8">
                                <input id="purchaseTotal" name="purchaseTotal" class="form-control"  value="${p.purchaseTotal}" type="text" readonly>
                            </div>
                        </div>
                        <input id="purchaseUser" name="purchaseUser" value="${user.managerId}" type="hidden" >
                        <input id="purchaseId" name="purchaseId" value="${p.purchaseId}" type="hidden" >
                        <input id="purchaseTime" name="purchaseTime" value="${p.purchaseTime}" type="hidden" >
                        <div class="form-group">
                            <div class="col-sm-8 col-sm-offset-3">
                                <button type="submit" class="btn btn-info">提交</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="../../../js/jquery-1.11.0.min.js"></script>
<script src="../../../js/bootstrap.min.js"></script>
<script src="../../../js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
<script src="../../../js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
<script src="../../../js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="../../../js/plugins/validate/jquery.validate.min.js"></script>
<script src="../../../js/plugins/validate/messages_zh.min.js"></script>
<script src="../../../js/plugins/jsTree/jstree.min.js"></script>
<script src="../../../js/plugins/jqTreeGrid/jquery.treegrid.min.js"></script>
<script src="../../../js/plugins/jqTreeGrid/jquery.treegrid.extension.js"></script>
<script src="../../../js/plugins/jqTreeGrid/jquery.treegrid.bootstrap3.js"></script>
<script src="../../../js/plugins/chosen/chosen.jquery.js"></script>
<script src="../../../js/plugins/layer/layer.js"></script>
<script src="../../../js/content.js"></script>
<!--summernote-->
<script src="../../../js/plugins/summernote/summernote.js"></script>
<script src="../../../js/plugins/summernote/summernote-zh-CN.min.js"></script>
<script src="../../../js/ajax-util.js"></script>
<script src="../../../js/purchase/edit.js"></script>
<script>
    $(function () {
        layer.closeAll('loading');
    });
    function clearNoNum(obj){
        //先把非数字的都替换掉，除了数字和.
        obj.value = obj.value.replace(/[^\d.]/g,"");
        //必须保证第一个为数字而不是.
        obj.value = obj.value.replace(/^\./g,"");
        //保证只有出现一个.而没有多个.
        obj.value = obj.value.replace(/\.{2,}/g,".");
        //保证.只出现一次，而不能出现两次以上
        obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
        //保证 数字整数位不大于8位
        if(100000<=parseFloat(obj.value))
            obj.value = "";
        else{
            updateProductNumber();
        }
    }

    function updateProductNumber() {
        var price = $("#purchasePrice").val();
        var num  =$("#purchaseNumber").val();
        $("#purchaseTotal").val((parseFloat(price) * parseFloat(num)).toFixed(2));

    }
</script>
</body>
</html>
