<%--
  Created by IntelliJ IDEA.
  User: wen
  Date: 2017/7/5
  Time: 11:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home</title>
    <link href="/statics/extjs/resources/css/ext-all-neptune.css" rel="stylesheet" type="text/css"/>
    <link href="/statics/css/app.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/statics/extjs/ext-all-debug.js"></script>
    <script type="text/javascript" src="/statics/extjs/locale/ext-lang-zh_CN.js"></script>
</head>
<body>
<script>
    Ext.onReady(function () {
        var treeStore = Ext.create('Ext.data.TreeStore', {
            root: {
                expanded: true,
                children: [{
                    text: "Menu Item A",
                    leaf: true
                }, {
                    text: "Menu Item B",
                    leaf: true
                }, {
                    text: "Menu Item C",
                    leaf: true
                }, {
                    text: "Menu Item D",
                    leaf: true
                }]
            }
        });
        var northPanel = Ext.create('Ext.container.Container', {
            border: false,
            cls: 'top_menu',
            layout: 'hbox',
            items: [{
                xtype: 'container',
                border: false,
                width: 300,
                html: '<div class="app-header-title">XX系统</div>'
            }],
            region: 'north'
        });
        var westPanel = Ext.create('Ext.tree.Panel', {
            title: 'Application Menu',
            region: 'west',
            margins: '0 5 0 0',
            width: 200,
            store: treeStore,
            rootVisible: false,
            listeners: {
                itemclick: function (tree, record, item, index, e, options) {
                    var nodeText = record.data.text,
                            tabPanel = viewport.items.get(1),
                            tabBar = tabPanel.getTabBar(),
                            tabIndex;
                    for (var i = 0; i < tabBar.items.length; i++) {
                        if (tabBar.items.get(i).getText() === nodeText) {
                            tabIndex = i;
                        }
                    }
                    if (Ext.isEmpty(tabIndex)) {
                        tabPanel.add({
                            title: record.data.text,
                            bodyPadding: 10,
                            html: record.data.text,
                            closable: true
                        });
                        tabIndex = tabPanel.items.length - 1;
                    }
                    tabPanel.setActiveTab(tabIndex);
                }
            }
        });
        //westPanel


        var viewport = Ext.create('Ext.container.Viewport', {
            layout: 'border',
            items: [westPanel, {
                xtype: 'tabpanel',
                region: 'center',
                autoScroll: true,
                items: [{
                    xtype: 'panel',
                    region: 'center',
                    title: '首页',
                    forceFit:true,
                    html: 'index page',
                    layout: 'border',
                    items: {
                        xtype: 'htmleditor',
                        enableColors: false,
                        enableAlignments: false,
                        region:'center'
                    }
                }]
            }, northPanel]
        });//viewport

    });
</script>
</body>
</html>
