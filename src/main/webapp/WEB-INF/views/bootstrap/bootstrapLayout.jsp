<%--
  Created by IntelliJ IDEA.
  User: wen
  Date: 2017/3/14
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>HomePage</title>
    <link href="${pageContext.request.contextPath}/statics/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/statics/jquery/jquery-1.9.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/statics/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/statics/bootstrap/js/bootstrap-paginator.js"></script>
    <script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/statics/My97DatePicker/WdatePicker.js"></script>
    <style type="text/css">

    </style>
</head>
<body>
<div class="container">
    <h1 align="center">Say Hello</h1>
    <table class="table table-hover table-bordered">
        <tr class="success">
            <th>邮箱</th>
            <th>用户名</th>
            <th>昵称</th>
            <th>密码</th>
            <th>手机</th>
            <th>身份证</th>
            <th>记录时间</th>
        </tr>
        <c:forEach items="${msg}" var="person" varStatus="status">
            <tr>
                <td>${person.uemail}</td>
                <td>${person.username}</td>
                <td>${person.nickname}</td>
                <td>${person.password}</td>
                <td>${person.phonenum}</td>
                <td>${person.cardnum}</td>
                <td><input type="text" class="Wdate" placeholder="如：1990-01-01" onClick="WdatePicker() "></td>
            </tr>
        </c:forEach>
    </table>
    <ul class="pagination"></ul>
    <p>hello${zth}</p>
</div>
</body>
</html>
<script>
    var options = {
        currentPage:1, //设置当前页，默认起始页为第一页
        totalPages:'5', //总页数
        numberOfPages:5, //设置控件显示的页码数,跟后台计算出来的总页数没多大关系
        bootstrapMajorVersion:3,//如果是bootstrap3版本需要加此标识，并且设置包含分页内容的DOM元素为UL,如果是bootstrap2版本，则DOM包含元素是DIV
        useBootstrapTooltip:'true',//是否显示tip提示框
        itemTexts:function(type,page, current){//文字翻译
            switch (type) {
                case "first":
                    return "首页";
                case "prev":
                    return "上一页";
                case "next":
                    return "下一页";
                case "last":
                    return "尾页";
                case "page":
                    return page;
            }
        },
        onPageClicked:function(event,originalEvent,type,page){
//            $.ajax({
//                type: 'get',
//                url: "/home/getAllUsers",
//                data: {
//                    currentPage:page,
//                    page:2
//                },
//                dataType: "json",
//                success: function (response) {
//
//                }
//            });

        }
    };
    $(document).ready(function () {
        $(".pagination").bootstrapPaginator(options);
    });
</script>
