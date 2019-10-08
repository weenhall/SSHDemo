<%--
  Created by IntelliJ IDEA.
  User: wen
  Date: 2017/12/28
  Time: 14:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html;charset=UTF-8">
    <meta charset="UTF-8">
    <script>
        var ctx = '${pageContext.request.contextPath}'
    </script>
    <title>HomePage</title>
    <link href="../../statics/fontawesome/css/fontawesome-all.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/statics/jquery/jquery-1.9.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/statics/bootstrap/js/bootstrap.min.js"></script>
    <title>Login</title>
    <style>
        body {
            font-family: 'microsoft yahei', Arial, sans-serif;
            margin: 0;
            padding: 0;
            height: 100%;
            background:#3B4E59;
            overflow: hidden;
        }

        .failed {
            color: crimson;
        }

        .login {
            margin: 0 auto;
            padding: 15%;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="login">
        <div class="modal-body row login-input">
            <form action="/user/login" method="post" class="form col-md-12 center-block form-group">
                <div class="row">
                    <div class="form-group col-md-6 col-md-offset-3">
                        <div class="input-group">
                            <span class="input-group-addon login-input-icon ">

                                <span class="glyphicon glyphicon-user"></span>
                            </span>
                            <input type="text" id="username" name="username" class="form-control login-input-edit "
                                   placeholder="请输入用户名" autocomplete="off">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-6 col-md-offset-3">
                        <div class="input-group">
                            <span class="input-group-addon login-input-icon">
                                 <span class="glyphicon glyphicon-lock"></span>
                            </span>
                            <input type="password" id="password" name="password" class="form-control login-input-edit"
                                   placeholder="请输入密码" autocomplete="off">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-6 col-md-offset-3 failed">${errorMsg}</div>
                </div>
                <div class="row">
                    <div class="form-group col-md-6 col-md-offset-3">
                        <button class="btn btn-sm btn-info col-sm-2" type="submit">登录</button>
                        <a href="https://github.com/login/oauth/authorize?client_id=46c91882fa768f05b4e7&state=0&redirect_uri=http://127.0.0.1:8080/oauthCallback"
                           class="btn btn-sm col-sm-2"><i class="fab fa-github-square fa-2x"></i></a>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
