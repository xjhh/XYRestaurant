<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>消费记录</title>
    <meta name="description" content="">
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/font-awesome.css" rel="stylesheet">
    <link href="/css/plugins/summernote/summernote-0.8.8.css" rel="stylesheet">
    <link href="/css/animate.css" rel="stylesheet">
    <style>
        td {
            width: 100px;
            height: 40px;
            text-align: center;
            background-color: #dddddd;
        }

        .th {
            background-color: #cccccc;
        }
        .r{
            /*height: 40px;*/
            text-align: right;
        }
        .l{
            text-align: left;
        }
        .ls{
            height: 40px;
            text-align: left;
        }
    </style>
</head>
<body>
<div class="wrapper wrapper-content ">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-content" style="text-align: center">
                        <div class="form-group">
                            <label class="col-sm-5 control-label r">会员消费记录ID：</label>
                            <label class="col-sm-7 ls"> ${consume.consumeId}</label>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-5 control-label r">消费人员：</label>
                            <label class="col-sm-7 ls">
                                <c:if test="${consume.memberCard == 0}">非会员客户</c:if>
                                <c:if test="${consume.memberCard != 0}">会员编号：  ${consume.memberCard}   会员名称：  ${consume.memberName}</c:if>
                            </label>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-5 control-label r">消费桌台编号：</label>
                            <label class="col-sm-7 ls">${consume.deskNumber}</label>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-5 control-label r">商品详情：</label>
                            <label class="col-sm-7 l" >
                                <table>
                                    <tr>
                                        <td class="th">编号</td>
                                        <td class="th">名称</td>
                                        <td class="th">单价</td>
                                        <td class="th">数量</td>
                                    </tr>
                                    <c:forEach var="g" items="${goods}">
                                        <tr>
                                            <td>${g.goodNumber}</td>
                                            <td>${g.goodName}</td>
                                            <td>${g.goodPrice}</td>
                                            <td>${g.goodBuySize}</td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </label>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-5 control-label r">消费时间：</label>
                            <label class="col-sm-7 ls">
                                ${consume.consumeTime}
                            </label>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-5 control-label r">消费金额：</label>
                            <label class="col-sm-7 ls">${consume.consumeTotal}</label>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-5 control-label r">消费状态：</label>
                            <label class="col-sm-7 ls">
                                <c:if test="${consume.consumeStatus == 0}">待结账</c:if>
                                <c:if test="${consume.consumeStatus == 1}">已完成</c:if>
                            </label>
                        </div>
                        <c:if test="${isClearing}">
                            <div class="form-group" style="text-align: center" onclick='clearConsume()'>
                                <img src="/img/ic_next.png">
                                <input type="hidden" name="consumeId" id="consumeId" value="${consume.consumeId}">
                            </div>

                        </c:if>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="/js/jquery-1.11.0.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
<script src="/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
<script src="/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="/js/plugins/validate/jquery.validate.min.js"></script>
<script src="/js/plugins/validate/messages_zh.min.js"></script>
<script src="/js/plugins/chosen/chosen.jquery.js"></script>
<script src="/js/plugins/layer/layer.js"></script>
<script src="/js/content.js"></script>
<!--summernote-->
<script src="/js/plugins/summernote/summernote.js"></script>
<script src="/js/plugins/summernote/summernote-zh-CN.min.js"></script>
<script src="/js/ajax-util.js"></script>
<script>

    function clearConsume() {
        $.ajax({
            type: "GET",
            url: "/addConsume/clearing/" +$("#consumeId").val() ,
            success: function (data) {
                var obj = JSON.parse(data);
                if (obj.result) {
                    parent.layer.msg("结算成功")
                    var index = parent.layer.getFrameIndex(window.name);
                    parent.re();
                    parent.layer.close(index);
                } else {
                    parent.layer.msg(obj.error);
                }
            }
        });
    }
</script>

</body>
</html>
