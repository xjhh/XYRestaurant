<%--
  Created by IntelliJ IDEA.
  User: 98019
  Date: 2018/4/4
  Time: 0:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>湘乐餐厅管理系统</title>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/font-awesome.min.css" rel="stylesheet">
    <!-- 全局js -->
    <script src="../js/jquery-1.11.0.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <!--侧栏-->
    <script src="../js/jquery.metisMenu.js"></script>

    <link rel="stylesheet" href="../css/main.css"/>
    <link rel="stylesheet" type="text/css" href="../css/style.css"/>

</head>

<body class="fixed-sidebar full-height-layout gray-bg" style="overflow: hidden">
<!--左侧导航开始-->
<div id="mainLeft">
    <nav class="navbar-default navbar-static-side main-left" role="navigation">
        <div class="sidebar-collapse">
            <ul class="nav" id="side-menu">
                <li class="nav-header">
                    <div style="position: relative;top: 50%;">
                        <h3 class="title">
                            湘乐餐厅后台管理系统
                        </h3>
                    </div>
                </li>
                <li>
                    <a href="#"> <i class="fa fa-home"></i> <span class="nav-label">主页</span> <span
                            class="fa arrow"></span>
                    </a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a id="index001" class="J_menuItem" href="ind.html" data-index="0">了解BootDo</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="#"> <i class="fa fa fa-bar-chart-o"></i>
                        <span class="nav-label">基础信息</span> <span class="fa arrow "></span>
                    </a>
                    <ul class="nav nav-second-level ">
                        <li>
                            <a class="J_menuItem " href="# " data-index="1">系统管理</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="#"> <i class="fa fa fa-bar-chart-o"></i>
                        <span class="nav-label">基础信息</span> <span class="fa arrow "></span>
                    </a>
                    <ul class="nav nav-second-level ">
                        <li>
                            <a class="J_menuItem " href="# ">系统管理</a>
                        </li>
                    </ul>
                </li>
                <c:forEach items="${menu}" var="m">
                    <li>
                        <a href="#">
                            <i class="${m.menuIco}"></i>
                            <span class="nav-label">${m.menuName}</span>
                            <span class="fa arrow"></span>
                        </a>
                        <ul class="nav nav-second-level">
                            <c:forEach items="${m.menuChilds}" var="menuChild">
                                <li>
                                    <a class="J_menuItem" href="${menuChild.menuUri}">${menuChild.menuName}</a>
                                </li>
                            </c:forEach>
                        </ul>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </nav>

</div>
<div id="page-wrapper" class="gray-bg dashbard-1 main-right" style="position: absolute;">
    <div class="row border-bottom">
        <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">

                <a class="navbar-minimalize minimalize-styl-2 btn btn-default " href="#" title="收起菜单"><i
                        class="fa fa-bars"></i> </a>

            </div>
            <ul class="nav navbar-top-links navbar-right">
                <li class="hidden-xs">
                    <a @click="personal" href="#"><i class="fa fa-id-card"></i> 个人</a>
                </li>
                <li class="dropdown hidden-xs">
                    <a class="right-sidebar-toggle" aria-expanded="false"> <i class="fa fa-tasks"></i> 主题
                    </a>
                </li>
            </ul>
        </nav>
    </div>
    <div class="row content-tabs">
        <button class="roll-nav roll-left J_tabLeft">
            <i class="fa fa-backward"></i>
        </button>
        <nav class="page-tabs J_menuTabs">
            <div class="page-tabs-content">
                <a href="javascript:;" class="active J_menuTab" data-id="index_v1.html">首页</a>
            </div>
        </nav>
        <button class="roll-nav roll-right J_tabRight">
            <i class="fa fa-forward"></i>
        </button>
        <div class="btn-group roll-nav roll-right">
            <button class="dropdown J_tabClose" data-toggle="dropdown">
                关闭操作<span class="caret"></span>
            </button>
            <ul role="menu" class="dropdown-menu dropdown-menu-right">
                <li class="J_tabShowActive">
                    <a>定位当前选项卡</a>
                </li>
                <li class="divider"></li>
                <li class="J_tabCloseAll">
                    <a>关闭全部选项卡</a>
                </li>
                <li class="J_tabCloseOther">
                    <a>关闭其他选项卡</a>
                </li>
            </ul>
        </div>
        <a href="/logout" class="roll-nav roll-right J_tabExit"><i class="fa fa fa-sign-out"></i> 退出</a>
    </div>

    <div class="row J_mainContent" id="content-main" style="position: absolute; width: 100%;">
        <iframe class="J_iframe" name="iframe0" width="100%" height="100%" src="" frameborder="0" data-id="ind.html"
                seamless></iframe>

    </div>

</div>

<canvas id="bgCanvas"></canvas>
<script type="text/javascript" src="../js/index.js"></script>
<script type="text/javascript " src="../js/main.js"></script>
<script type="text/javascript" src="../js/contabs.js"></script>
</body>

</html>
