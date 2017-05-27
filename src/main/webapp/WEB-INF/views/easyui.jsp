<%--
  Created by IntelliJ IDEA.
  User: wen
  Date: 2017/3/17
  Time: 9:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EasyUI-Learn</title>
    <meta charset="UTF-8">
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <script src="/statics/easyui/jquery.min.js"></script>
    <script src="/statics/easyui/jquery.easyui.min.js"></script>
    <script src="/statics/easyui/locale/easyui-lang-zh_CN.js"></script>
    <link rel="stylesheet" media="screen" href="/statics/easyui/themes/default/easyui.css">
    <link rel="stylesheet" media="screen" href="/statics/easyui/themes/icon.css">
</head>
<body>
<div style="width:100%;height:10%;">
    <p>Home Page</p>
</div>
<div id="wholePanel" class="easyui-layout" style="width:100%;height:90%;">
    <div data-options="region:'west',title:'目录',split:true" style="width:100px;">
        <ul id="treePanel" class="easyui-tree">
            <li>
                <span>Root</span>
                <ul>
                    <li>
                        <span>节点1</span>
                        <ul>
                            <li><span><a href="#">1-11</a></span></li>
                            <li><span>1-12</span></li>
                            <li><span>1-13</span></li>
                        </ul>
                    </li>
                    <li><span>1-2</span></li>
                    <li><span>1-3</span></li>
                </ul>
            </li>
        </ul>
    </div>
    <div data-options="region:'center',title:'面板'" style="padding:5px;background:#eee;"></div>
</div>
</body>
</html>
