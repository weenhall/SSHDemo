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
    <script src="${pageContext.request.contextPath}/statics/easyui/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/statics/easyui/jquery.easyui.min.js"></script>
    <script src="${pageContext.request.contextPath}/statics/easyui/locale/easyui-lang-zh_CN.js"></script>
    <link rel="stylesheet" media="screen" href="${pageContext.request.contextPath}/statics/easyui/themes/default/easyui.css">
    <link rel="stylesheet" media="screen" href="${pageContext.request.contextPath}/statics/easyui/themes/icon.css">
</head>
<body class="easyui-layout">
<!--  页面上方区域     -->
<div region="north" split="true"
     style="height:60px;font-size: 26px;text-align: center;padding: 8px;background-color: #D1EEEE">easyui-layout
    <button id="addTab">add tab</button>
</div>

<!--  页面左边区域    -->
<div region="west" style="width:180px" title="Hbase查询功能列表" split="true">
    <!-- 树形结构的功能列表 -->
    <ul id="tree"></ul>
</div>he

<!--  页面中间内容（主面板）区域     -->
<div region="center">
    <div id="tabpanel" class="easyui-tabs" fit="true" border="false">
        <div title="首页">
            <table id="datagrid" class="easyui-datagrid" title="用户信息" style="width: 100%;height: 100%" data-options="singleSelect:true,fitColumns:true,url:'dataGridList',rownumbers:true,autoRowHeight:false,pagination:true">
                <thead>
                <tr>
                    <th data-options="field:'id',sortable:true">ID</th>
                    <th data-options="field:'username'">用户名</th>
                    <th data-options="field:'personname'">姓名</th>
                    <th data-options="field:'password'" >密码</th>
                    <th data-options="field:'idenCard'">证件号码</th>
                    <th data-options="field:'address'">地址</th>
                </tr>
                </thead>
            </table>
        </div>
    </div>
</div>
</body>
<script>
    function initPanel(){
        //动态树形菜单数据
        var treeData = [{
            text: "Hbase查询功能列表",
            children: [{
                text: "具体数据查询",
                children: [{
                    text: "单一商品价格库存",
                    attributes: {
                        url: '<iframe width="100%" height="100%" frameborder="0"  src="https://www.baidu.com" style="width:100%;height:100%;margin:0px 0px;"></iframe>'
                    }
                }, {
                    text: "单一商品价格库存2",
                    attributes: {
                        url: ''

                    }
                }
                ]
            }, {
                text: "数据量查询",
                children: [{
                    text: "总量统计",
                    attributes: {
                        url: '<iframe width="100%" height="100%" frameborder="0"  src="https://www.baidu.com" style="width:100%;height:100%;margin:0px 0px;"></iframe>'
                    }
                }, {
                    text: "总量统计2",
                    attributes: {
                        url: ''
                    }
                }
                ]
            }
            ]
        }];

        //实例化树形菜单
        $("#tree").tree({
            data: treeData,
            lines: true,
            onClick: function (node) {
                if (node.attributes) {
                    Open(node.text, node.attributes.url);
                }
            }
        });
        //在右边center区域打开菜单，新增tab
        function Open(text, url) {
            if ($("#tabpanel").tabs('exists', text)) {
                $('#tabpanel').tabs('select', text);
            } else {
                $('#tabpanel').tabs('add', {
                    title: text,
                    closable: true,
                    content: url
                });
            }
        }

        //绑定tabs的右键菜单
        $("#tabpanel").tabs({
            onContextMenu: function (e, title) {
                e.preventDefault();
                $('#tabsMenu').menu('show', {
                    left: e.pageX,
                    top: e.pageY
                }).data("tabTitle", title);
            }
        });

        //实例化menu的onClick事件
        $("#tabsMenu").menu({
            onClick: function (item) {
                CloseTab(this, item.name);
            }
        });

        //几个关闭事件的实现
        function CloseTab(menu, type) {
            var curTabTitle = $(menu).data("tabTitle");
            var tabs = $("#tabpanel");

            if (type === "close") {
                tabs.tabs("close", curTabTitle);
                return;
            }

            var allTabs = tabs.tabs("tabs");
            var closeTabsTitle = [];

            $.each(allTabs, function () {
                var opt = $(this).panel("options");
                if (opt.closable && opt.title != curTabTitle && type === "Other") {
                    closeTabsTitle.push(opt.title);
                } else if (opt.closable && type === "All") {
                    closeTabsTitle.push(opt.title);
                }
            });

            for (var i = 0; i < closeTabsTitle.length; i++) {
                tabs.tabs("close", closeTabsTitle[i]);
            }
        }
    }
    $(function () {
        initPanel();
        $('#addTab').click(function () {
            $('#tabpanel').tabs('add', {
                title: 'new tab',
                closable: true,
                content: 'a new body'
            });
        });
    });
</script>
</html>
