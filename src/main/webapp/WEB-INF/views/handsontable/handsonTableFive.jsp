<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!--国产浏览器开启高速模式，目前仅360支持-->
    <meta name="renderer" content="webkit">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>handsonTableFour</title>
    <meta name="description" content="handsontable4">
    <meta name="keywords" content="table spreedsheet">
    <meta name="author" content="helloworld">
    <meta name="robots" content="index,follow">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-touch-fullscreen" content="yes">
    <link rel="shorcut" href="path/to/favicon.ico">

    <script src="${pageContext.request.contextPath}/statics/jquery/jquery-1.9.1.min.js"></script>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/statics/bootstrap/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/statics/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/statics/handsontable5/dist/handsontable.full.min.js"></script>
    <link rel="stylesheet" media="screen"
          href="${pageContext.request.contextPath}/statics/handsontable5/dist/handsontable.full.min.css">
    <style type="text/css">
        #example{
            margin: 0 auto;
        }
        .page-header{
            margin: 10px 0 10px 0;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="row text-center">
        <h2>handsonTable5</h2>
    </div>
    <h5 class="page-header"></h5>
    <div id="example"></div>
</div>
</body>
<script>
    var data = [
        ["", "Ford", "Tesla", "Toyota", "Honda"],
        ["2017", 10, 11, 12, 13],
        ["2018", 20, 11, 14, 13],
        ["2019", 30, 15, 12, 13]
    ];

    var container=document.getElementById('example');
    var hot=new Handsontable(container,{
        data:data,
        rowHeaders:true,
        colHeaders:true,
        afterInit:function () {
            <!--align handsontable-->
            $('#example').css('width',$('#example .wtHider').css('width'));
        }
    });
</script>
</html>
