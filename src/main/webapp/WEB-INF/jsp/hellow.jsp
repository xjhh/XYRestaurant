<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 98019
  Date: 2018/3/11
  Time: 18:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hellow Word</title>
    <style>

    </style>
</head>
<body>
    <div class="body" align="center" style="position: absolute;top: 34%;left: 34%;color: #0a6aa1">
        <h1>湘乐餐厅管理系统</h1>
        <h2>欢迎${powerName}:${user.managerUsername}进入湘乐餐厅管理系统</h2>
        <c:choose>
            <c:when test="${user.managerPower == 0}">
                <h3>你为系统管理员，有权管理本系统的各种功能</h3>
            </c:when>
            <c:when test="${user.managerPower == 1}">
                <h3>你为餐厅管理员，能管理本餐厅的大小事务</h3>
            </c:when>
            <c:otherwise>
                <h3>你为采购员，能管理餐厅商品采购。</h3>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>
