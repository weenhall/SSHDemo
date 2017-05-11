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
    <title>Title</title>
    <meta charset="UTF-8">
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <script src="/statics/jquery/jquery-1.9.1.min.js"></script>
    <script src="/statics/bootstrap/js/bootstrap.min.js"></script>
    <script src="/statics/handsontable/dist/handsontable.full.min.js"></script>
    <link rel="stylesheet" media="screen" href="/statics/handsontable/dist/handsontable.full.min.css">
</head>
<body>
<div style="margin-left: 40%">
    <div><h1>handsontable表格示例</h1></div>
    <div id="example"></div>
</div>
</body>
<script>
    var column = [
        {
            data: 'lot1',
            type: 'text',
            editor: 'select',
            selectOptions:['Kia','Nissan','Toyota','Honda']
        },
        {data: 'lot2', type: 'text'},
        {data: 'lot3', type: 'dropdown',source:['黑色','白色','红色','黄色']},
        {data: 'lot4', type: 'text'},
        {data: 'lot5', type: 'text',editor:'date',dateFormat: 'YYYY/MM/DD'}
    ];
    var data = [
        {lot1: "车型", lot2: "销量", lot3: "颜色", lot4: "起售价", lot5: "销售时间"},
        {lot1: "kia", lot2: 11, lot3: "黑色", lot4: 11000, lot5: 11100},
        {lot1: "Ford", lot2: 10, lot3: "白色", lot4: 56000, lot5: 58000},
        {lot1: "Volvo", lot2: 20, lot3: "白色", lot4: 78000, lot5: 80000},
        {lot1: "Toyota", lot2: 30, lot3: "黑色", lot4: 80000, lot5: 82000},
        {lot1: "Honda", lot2: 30, lot3: "黄色", lot4: 67000, lot5: 70000}
    ];
    var container = document.getElementById('example');
    var hot = new Handsontable(container, {
        data: data,
        columns: column,
        //自定义表头colHeaders:['悦达起亚','福特','沃尔沃','丰田','本田'],
        columnSorting: true,
        rowHeaders: true,
        colHeaders: true,
        contextMenu:true,
        fillHandle:true
    });
    function setSum(){

    }
    function afterChange(changes,source){

    }
    function beforeChange(changes,source){

    }
    Handsontable.hooks.add('beforeChange',beforeChange,hot);
    Handsontable.hooks.add('afterChange',afterChange,hot);
    hot.updateSettings({
        cells:function(row,col,prop){
            var cellProperties={};
            debugger;
        }
    });
</script>
</html>
