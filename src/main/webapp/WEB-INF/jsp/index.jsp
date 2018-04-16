<%--
  Created by IntelliJ IDEA.
  User: 98019
  Date: 2018/3/25
  Time: 14:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>湘乐餐厅管理系统</title>
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="../css/index.css"/>
    <link rel="stylesheet" href="../css/font-awesome.min.css"/>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <script type="application/javascript">
        $(function () {
            $(".login").click(function () {
                $.ajax({
                    type:"post",
                    url:"/xiangyue/logins",           //判断密码是否正确
                    data:{"managerUsername":$("#managerUsername").val(), "managerPassword":$("#managerPassword").val(),"managerPower":$("#managerPower").val()},
                    datatype:"text",
                    success:function (data) {
                        var obj = JSON.parse(data);
                        if(obj.result) {
                            window.location.href = "/xiangyue/main"
                        }else{
                            alert(obj.error);
                        }
                    }
                })
            });
        })
    </script>
</head>
<body>
<div id="main">
    <h4 class="banner">
        <span class="flag"></span>
        <span class="flag">湘</span>
        <span class="flag">乐</span>
        <span class="flag">餐</span>
        <span class="flag">厅</span>
        <span class="flag">管</span>
        <span class="flag">理</span>
        <span class="flag">系</span>
        <span class="flag">统</span>
        <span class="flag">!</span>
        <span class="flag"></span>
        <span class="string">
			    <svg width="800" height="230" viewBox="0 0 800 230">
			      <path fill="none" d="M0,0 C100,100 700,200 800,100"/>
			    </svg>
			  </span>
    </h4>

    <div class="container">
        <div class="row">
            <div class="col-md-offset-3 col-md-6">
                <form class="form-horizontal">
                    <span class="heading">用户登录</span>
                    <div class="form-group">
                        <input type="text" class="form-control" id="managerUsername" placeholder="用户名">
                        <i class="fa fa-user"></i>
                    </div>
                    <div class="form-group help">
                        <input type="password" class="form-control" id="managerPassword" placeholder="密码">
                        <i class="fa fa-lock"></i>
                    </div>
                    <select id="managerPower">
                        <option value="0">系统管理员</option>
                    </select>
                    <div class="form-group">
                        <button type="button"  class="btn btn-default login" >立刻登录</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<canvas id="bgCanvas">
</canvas>
<script type="text/javascript" src="../js/index.js"></script>
<script type="text/javascript" src="../js/first_page.js"></script>
</body>
</html>
