<%--
  Created by IntelliJ IDEA.
  User: wen
  Date: 2017/11/15
  Time: 15:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="/jquery.min.js"></script>
    <script type="text/javascript" src="/pageoffice.js"  id="po_js_main"></script>
</head>
<script>
    window.onload=function(){
        POBrowser.openWindow('pageWord','width=1200px;height=800px;');
    };
//    function opp(){
//        POBrowser.openWindow('pageWord','width=1200px;height=800px;');
//    }
//   setTimeout('opp()',5000);
</script>
<body>
<%--<a href="javascript:POBrowser.openWindow('pageWord','width=1200px;height=800px;');">POBrowser方式在线打开文档（以Word为例）</a>--%>
</body>
</html>
