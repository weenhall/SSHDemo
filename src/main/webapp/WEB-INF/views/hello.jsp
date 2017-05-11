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
    <link href="/statics/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script src="/statics/jquery/jquery-1.9.1.min.js"></script>
    <script src="/statics/bootstrap/js/bootstrap.min.js"></script>
    <script language="javascript" type="text/javascript" src="../../statics/My97DatePicker/WdatePicker.js"></script>
    <style>

    </style>
</head>
<body>
<h1 align="center">Say Hello</h1>
<table class="table table-hover table-bordered" style="width: 50% ;margin-left: 25%">
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
<div class="pagination">
    <ul>
        <li><a href="#">Prev</a></li>
        <li><a href="#">1</a></li>
        <li><a href="#">2</a></li>
        <li><a href="#">3</a></li>
        <li><a href="#">4</a></li>
        <li><a href="#">5</a></li>
        <li><a href="#">Next</a></li>
    </ul>
</div>
</body>
</html>
