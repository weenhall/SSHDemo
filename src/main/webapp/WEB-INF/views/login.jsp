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
    <link href="${pageContext.request.contextPath}/statics/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/statics/jquery/jquery-1.9.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/statics/bootstrap/js/bootstrap.min.js"></script>
    <title>Login</title>
</head>
<body>
<div style="width:100%;text-align:center;">
    <div style="width:1005px; margin:0 auto; position:relative;">
        <table width="1005" cellspacing="0" cellpadding="0" border="0" align="center">
            <tbody>
            <tr>
                <td class="login_form">
                    <form action="/user/login" method="post">
                        <div class="loginform">
                            <div>
                                <label class="column">用户名</label>
                                <input name="username" id="username" maxlength="100" type="text">
                            </div>
                            <div>
                                <label class="column">密码</label>
                                <input name="password" id="password" maxlength="100" type="password">
                            </div>
                            <div>${errorMsg}</div>
                        </div>

                        <div class="loginFormBtn">
                            <button id="loginBtn" class="btn btn-main btn-login" tabindex="6" type="submit"
                            >登&nbsp;&nbsp;录
                            </button>
                        </div>
                    </form>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
