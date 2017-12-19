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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/bootstrap/css/bootstrap.min.css" >
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/loading.css">
    <script src="${pageContext.request.contextPath}/statics/jquery/jquery-1.9.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/statics/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/statics/bootstrap/js/bootstrap-paginator.js"></script>
    <script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/statics/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
<div class="container">
    <h1 align="center">this is a table</h1>
    <div class="form-inline my-2 my-lg-0">
        <input class="form-control mr-sm-2" type="search" id="searchfield" name="content" placeholder="Search in here"
               aria-label="Search">
        <button class="btn btn-outline-success my-2 my-sm-0" type="button" onclick="searchFun()">Search</button>
    </div>
    <table class="table table-hover table-bordered" id="dataShow">
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
    <div><p id="emptyText"></p></div>
    <ul class="pagination"></ul>
    <form action="/learn/importDemo" method="post" enctype="multipart/form-data">
        <div class="form-group">
            <label for="exampleInputFile">文件导入</label>
            <input type="file" id="exampleInputFile" name="file">
            <button type="submit" class="btn btn-success">上传</button>
        </div>
    </form>
</div>
</body>
</html>
<script>
    var options = {
        size: "normal",
        alignment: "center",
        currentPage: 1,
        numberOfPages: 5,//按钮显示数量
        totalPages: 5, //总页数
        bootstrapMajorVersion: 3,//如果是bootstrap3版本需要加此标识，并且设置包含分页内容的DOM元素为UL,如果是bootstrap2版本，则DOM包含元素是DIV
        itemTexts: function (type, page, current) {
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
        onPageClicked: function (event, originalEvent, type, page) {
        }
    };
    $(".pagination").bootstrapPaginator(options);

    function reload(data) {
        $("#dataShow tr:not(:first)").remove();
        for (var i = 0; i < data.length; i++) {
            var row = '<tr><td>' + data[i]['uemail'] + '</td><td>' + data[i]['username'] + '</td><td>' + data[i]['nickname'] + '</td><td>' + data[i]['password'] + '</td><td>' + data[i]['phonenum'] + '</td><td>' + data[i]['cardname'] + '</td><td><input type="text" class="Wdate" placeholder="如：1990-01-01" onClick="WdatePicker() "></td></tr>';
            $('#dataShow').append(row);
        }
    }

    function searchFun() {
        var content = $('#searchfield').val();
        $.ajax({
            type: 'GET',
            url: "/learn/getUsersByName",
            data: {
                content: content
            },
            dataType: "json",
            success: function (response) {
                if (response.success) {
                    debugger;
                    reload(response.attributes.data);
                } else {
                    alert(response.message);
                }
            }
        });
    }

</script>
