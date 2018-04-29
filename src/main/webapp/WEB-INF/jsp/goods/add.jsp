 <%--
  Created by IntelliJ IDEA.
  User: 98019
  Date: 2018/4/10
  Time: 0:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>添加商品</title>

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
                            <label class="col-sm-3 control-label">商品名称：</label>
                            <div class="col-sm-8">
                                <input id="goodName" name="goodName" class="form-control" type="text">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">商品描述：</label>
                            <div class="col-sm-8">
                                <input id="goodDepict" name="goodDepict" class="form-control" type="text">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">商品价格：</label>
                            <div class="col-sm-8">
                                <input id="goodPrice" name="goodPrice" class="form-control" type="number">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">商品库存：</label>
                            <div class="col-sm-8">
                                <input id="goodStock" name="goodStock" class="form-control" type="number">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">商品类别：</label>
                            <div class="col-sm-8">
                                <c:forEach var="g" items="${goodsType}">

                                    <label class="radio-inline">
                                        <input type="radio" name="goodType" value="${g.typeId}"/> ${g.typeName}
                                    </label>
                                </c:forEach>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">商品图片地址：</label>
                            <div class="col-sm-8">
                                <input id="imgfile" class="form-control" type="file" accept="image/*">
                                <input type="hidden" name="goodImg" id="goodImg">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">商品图片预览：</label>
                            <div class="col-sm-8">
                                <img id="img" style="width: 100px; height: 100px;">
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-8 col-sm-offset-3">
                                <button type="submit" class="btn btn-primary">提交</button>
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
<script src="../../../js/goods/add.js"></script>
<script type="text/javascript">
    $(function () {
        $("input[name='goodType']").get(0
        ).checked=true;
        $("#imgfile").change(function(){
            var data=window.URL.createObjectURL(this.files[0]);
            var filename=this.files[0].name;
            var ready=new FileReader();
            ready.onload=function(){
                // alert(this.result)
                $("#goodImg").val(this.result);
                $("#img").attr("src", this.result);
            }
            ready.readAsDataURL(this.files[0]);
        });
    });
</script>
</body>
</html>
