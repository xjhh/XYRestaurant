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
                            <label class="col-sm-3 control-label">桌台类型：</label>
                            <div class="col-sm-8">
                                <label class="radio-inline">
                                    <input type="radio" id="desk2" name="deskType" value="2" checked/>&nbsp;
                                    <label for="desk2">
                                        <img src="/img/desk/desk_2.png" width="60" height="60">二人桌</label><br>
                                    <input type="radio" id="desk4" name="deskType" value="4"/>&nbsp;
                                        <label for="desk4">
                                            <img src="/img/desk/desk_4.png" width="60" height="60">四人桌</label><br>
                                    <input type="radio" id="desk8" name="deskType" value="8"/>&nbsp;
                                        <label for="desk8">
                                            <img src="/img/desk/desk_8.png" width="60" height="60">八人桌</label><br>
                                    <input type="radio" id="desk10" name="deskType" value="10"/>&nbsp;
                                                <label for="desk10">
                                                    <img src="/img/desk/desk_10.png" width="60" height="60">十人桌</label><br>
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">批量添加桌台数量：</label>
                            <div class="col-sm-8">
                                <select id="addSize" class="col-sm-8 btn btn-info" name ="addSize">
                                    <option value="1">1</option>
                                    <option value="2">2</option>
                                    <option value="3">3</option>
                                    <option value="4">4</option>
                                    <option value="5">5</option>
                                    <option value="6">6</option>
                                    <option value="7">7</option>
                                    <option value="8">8</option>
                                    <option value="9">9</option>
                                    <option value="10">10</option>
                                </select>

                                <input id="deskState" name="deskState" value="0" type="hidden">
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
<script type="text/javascript">
    $(function () {
        var prefix = "/desk"
        $(function() {
            validateRule();
            //打开图标列表
        });
        $.validator.setDefaults({
            submitHandler : function() {
                submit01();
            }
        });
        function submit01() {
            $.ajax({
                cache : true,
                type : "post",
                url : prefix + "/insert",
                data : $('#signupForm').serialize(),
                async : false,
                error : function(request) {
                    laryer.alert("Connection error");
                },
                success : function(data) {
                    var obj = JSON.parse(data);
                    if(obj.result) {
                        parent.layer.msg("添加成功");
                        var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                        parent.layer.close(index);
                    }else{
                        parent.layer.msg(obj.error);
                    }
                }
            });
        }

        function validateRule() {
            var icon = "<i class='fa fa-times-circle'></i> ";
            $("#signupForm").validate({
                rules : {
                    typeName : {
                        required : true
                    }
                },
                messages : {
                    typeName : {
                        required : icon + "商品类别描述不能为空"
                    }
                }
            })
        }
    });
</script>
</body>
</html>
