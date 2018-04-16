<%--
  Created by IntelliJ IDEA.
  User: 98019
  Date: 2018/4/15
  Time: 23:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改密码</title>

</head>
<body>
<div class="wrapper wrapper-content animated fadeInRight">

    <div class="row">
        <div class="col-sm-12">

            <div class="ibox-content">
                <form class="form-horizontal m-t" id="signupForm">
                    <input id="managerId" name="managerId" value="${manager.managerId}"
                           type="hidden">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">输入密码：</label>
                        <div class="col-sm-8">
                            <input id="managerPassword" name="managerPassword" class="form-control"
                                   type="password" value="${manager.managerPassword}">
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
    <!-- 全局js -->
    <script src="../../../js/jquery-1.11.0.min.js"></script>
    <script src="../../../js/bootstrap.min.js"></script>

    <!-- 自定义js -->
    <script src="../../../js/content.js?v=1.0.0"></script>

    <!-- jQuery Validation plugin javascript-->
    <script src="../../../js/plugins/validate/jquery.validate.min.js"></script>
    <script src="../../../js/plugins/validate/messages_zh.min.js"></script>


    <!-- script src="/js/demo/form-validate-demo.js"></script> -->


    <script type="text/javascript">
        $(document).ready(function() {
            validateRule();
            //setCheckedRoles();
        });
        $.validator.setDefaults({
            submitHandler : function() {
                update();
            }
        });
        function update() {
            $.ajax({
                cache : true,
                type : "POST",
                url : "/sys/user/adminResetPwd",
                data : $('#signupForm').serialize(),// 你的formid
                async : false,
                error : function(request) {
                    parent.layer.msg("系统错误，联系管理员");
                },
                success : function(data) {
                    var obj = JSON.parse(data);
                    if(obj.result) {
                        parent.layer.msg("修改成功");
                        parent.reLoad();
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

                    password : {
                        required : true,
                        minlength : 6
                    }
                },
                messages : {
                    password : {
                        required : icon + "请输入您的密码",
                        minlength : icon + "密码必须6个字符以上"
                    }
                }
            })
        }
    </script>
</div>
</body>
</html>
