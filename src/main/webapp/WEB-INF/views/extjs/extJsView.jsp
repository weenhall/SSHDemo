<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>${param["title"]}</title>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
    <link href="/statics/extjs/resources/css/ext-all-neptune.css" rel="stylesheet" type="text/css"/>
    <link href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="/statics/css/app.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/statics/extjs/ext-all-debug.js"></script>
    <script type="text/javascript" src="/statics/extjs/locale/ext-lang-zh_CN.js"></script>
    <%
        response.addHeader("Access-Control-Allow-Origin", "*");
    %>
</head>
<body>
<script>
    Ext.onReady(function () {
        Ext.Loader.setPath('Ext.ux','/statics/extjs/ux');
        Ext.Loader.setPath('com.ween','/statics/jsfile');
        Ext.setGlyphFontFamily('FontAwesome');//简化ExtJS glyph的值
        <%--Ext.tip.QuickTipManager.init();--%>
        <%--new Ext.container.Viewport({--%>
            <%--layout: 'border',--%>
            <%--items: [Ext.create('${param["viewClass"]}', Ext.apply({--%>
                <%--region: 'center',--%>
                <%--border: false--%>
            <%--}, ${param["config"]}))]--%>
        <%--});--%>
        Ext.tip.QuickTipManager.init();
        new Ext.container.Viewport({
            layout: 'border',
            items: [Ext.create('com.ween.attfile.FileGrid', Ext.apply({
                region: 'center',
                border: false
            }, ${param["config"]}))]
        });
    });

</script>
</body>
</html>
