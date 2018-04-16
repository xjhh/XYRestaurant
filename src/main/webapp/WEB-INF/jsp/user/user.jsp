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
    <title>用户管理</title>
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
                    <h5>选择部门</h5>
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
                            <button shiro:hasPermission="sys:user:add" type="button"
                                    class="btn  btn-primary" onclick="add()">
                                <i class="fa fa-plus hidden" aria-hidden="true"></i>添加
                            </button>
                            <button shiro:hasPermission="sys:user:batchRemove" type="button"
                                    class="btn  btn-danger" onclick="batchRemove()">
                                <i class="fa fa-trash hidden" aria-hidden="true"></i>删除
                            </button>
                        </div>
                        <div class="columns pull-right">
                            <button class="btn btn-success" onclick="reLoad()">查询</button>
                        </div>

                        <div class="columns pull-right col-md-2 nopadding">
                            <input id="searchName" type="text" class="form-control"
                                   placeholder="姓名">
                        </div>
                    </div>
                    <table id="exampleTable" data-mobile-responsive="true">
                    </table>
                </div>
            </div>
        </div>
    </div>
    <!--shiro控制bootstraptable行内按钮看见性 来自bootdo的创新方案 -->
    <div>
        <script type="text/javascript">
            var s_edit_h = 'hidden';
            var s_remove_h = 'hidden';
            var s_resetPwd_h = 'hidden';
        </script>
    </div>
    <div shiro:hasPermission="sys:user:edit">
        <script type="text/javascript">
            s_edit_h = '';
        </script>
    </div>
    <div shiro:hasPermission="sys:user:remove">
        <script type="text/javascript">
            var s_remove_h = '';
        </script>
    </div>
    <div shiro:hasPermission="sys:user:resetPwd">
        <script type="text/javascript">
            var s_resetPwd_h = '';
        </script>
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
<script type="text/javascript" src="../../js/plugins/distpicker/distpicker.data.min.js"></script>
<script type="text/javascript" src="../../js/plugins/distpicker/distpicker.min.js"></script>
<%--<script type="text/javascript" src="../../js/sys/user/gg-bootdo.js"></script>--%>
<!--校验插件-->
<script src="../../js/plugins/validate/jquery.validate.min.js"></script>
<script src="../../js/plugins/validate/jquery.validate.extend.js"></script>
<script src="../../js/plugins/validate/messages_zh.min.js"></script>
<script type="text/javascript" src="../../js/sys/user/user.js"></script>

</body>
</html>
