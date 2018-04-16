<%--
  Created by IntelliJ IDEA.
  User: 98019
  Date: 2018/4/10
  Time: 0:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加菜单</title>

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
                        <input id="menuSeries" name="menuSeries" type="hidden" value="${pId}">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">上级菜单：</label>
                            <div class="col-sm-8">
                                <input id="" name="" class="form-control" type="text" value="${pName}" readonly>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">菜单类型：</label>
                            <div class="col-sm-8">
                                <label class="radio-inline">
                                    <input type="radio" name="type" value="0"/> 目录
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="type" value="1"/> 菜单
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="type" value="2"/> 按钮
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">菜单名称：</label>
                            <div class="col-sm-8">
                                <input id="menuName" name="menuName" class="form-control" type="text">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">链接地址：</label>
                            <div class="col-sm-8">
                                <input id="menuUri" name="menuUri" class="form-control" type="text">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">权限标识：</label>
                            <div class="col-sm-8">
                                <input id="menuPower" name="menuPower" class="form-control" type="text">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">图标：</label>
                            <div class="col-sm-6">
                                <input id="menuIco" name="menuIco" class="form-control" type="text"
                                       placeholder="例如：fa fa-circle-o">
                            </div>
                            <input id="ico-btn" class="btn btn-warning" type="button" value="选择图标">
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
<script src="../../../js/sys/menu/add.js"></script>
<script>
    $(function () {
        // layer.closeAll('loading');
    });
</script>
</body>
</html>
