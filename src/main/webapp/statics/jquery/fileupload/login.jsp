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
    <title>Login Page</title>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <script src="/statics/jquery/jquery-1.9.1.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script language="javascript" type="text/javascript" src="../../statics/My97DatePicker/WdatePicker.js"></script>
    <style type="text/css">
        #code
        {
            font-family:Arial;
            font-style:italic;
            font-weight:bold;
            border:0;
            letter-spacing:2px;
            color:blue;
        }
    </style>
    <script>
        function createCode(){
            var code='';
            var codelen=4;
            var random=new Array(0,1,2,3,4,5,6,7,8,9,'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R',
                    'S','T','U','V','W','X','Y','Z');
            for(var i=0;i<codelen;i++){
                var index=Math.floor(Math.random()*36);
                code+=random[index];
            }
            document.getElementById('codes').innerText(code);
        }
    </script>
</head>
<body>
<!--使用固定布局-->
<div class="container">
    <div>
        <input type="text" id="codes"/>
        <input type="button" id="code" onclick="createCode()">
    </div>
</div>
</body>
</html>
