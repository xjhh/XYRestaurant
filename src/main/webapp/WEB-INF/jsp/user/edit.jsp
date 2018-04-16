<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>菜单修改</title>
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
                            <label class="col-sm-3 control-label">用户编号：</label>
                            <div class="col-sm-8">
                                <input id="managerId" name="managerId" class="form-control" type="text"
                                       value="${manager.managerId}" readonly>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">用户名：</label>
                            <div class="col-sm-8">
                                <input id="managerUsername" name="managerUsername" value="${manager.managerUsername}"
                                       class="form-control" type="text">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">密码：</label>
                            <div class="col-sm-8">
                                <input id="managerPassword" type="managerPassword" name="managerPassword"
                                       value="${manager.managerPassword}" class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">权限：</label>
                            <div class="col-sm-8">
                                <c:forEach var="p" items="${powers}">
                                    <label class="radio-inline">
                                        <input type="radio" name="managerPower" value="${p.powerId}"/> ${p.powerDepict}
                                    </label>
                                </c:forEach>
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
<script src="../../../js/sys/user/edit.js"></script>
<script>
    $(function () {
        $("input[name='managerPower'][value='${power.managerPower}']").checked=true;
        layer.closeAll('loading');
    });
</script>
</body>
</html>
