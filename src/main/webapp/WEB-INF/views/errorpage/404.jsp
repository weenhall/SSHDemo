<%--
  Created by IntelliJ IDEA.
  User: wen
  Date: 2017/3/15
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>404错误 - 您所访问的页面未找到 </title>
    <meta content="text/html; charset=utf-8" http-equiv="Content-Type"/>
    <!-- content="5，为5秒后就返回主页，可根据需要修改或者删除这段代码 -->
    <meta http-equiv="refresh" content="5;url=/index.jsp">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/statics/404/error.css" media="screen"/>
    <script  type="text/javascript">
        var t=4;
        setInterval("refer()",1000);
        function refer(){
            document.getElementById('show').innerHTML=""+t;
            t--;
        }
    </script>
</head>
<body>
<div id="container"><img class="png" src="${pageContext.request.contextPath}/statics/404/404.png"/> <img class="png msg" src="${pageContext.request.contextPath}/statics/404/404_msg.png"/>
    <p><span id="show">5</span>秒后将返回首页！</p>
    <p><a href="${pageContext.request.contextPath}/index.jsp"><img class="png" src="${pageContext.request.contextPath}/statics/404/404_to_index.png"/></a></p>
</div>
<div id="cloud" class="png"></div>
<pre style="DISPLAY: none"></pre>
</body>
</html>
