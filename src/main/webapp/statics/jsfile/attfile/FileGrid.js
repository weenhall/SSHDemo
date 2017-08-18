/**
 * Created by wen on 2017/8/3.
 */
Ext.define('com.ween.attfile.FileGrid', {
    extend: 'Ext.grid.Panel',
    autoScroll: true,
    requires: ['com.ween.attfile.AttFile'],
    initComponent: function () {
        this.store = Ext.create('Ext.data.Store', {
            model: 'com.ween.attfile.AttFile',
            proxy: {
                type: 'ajax',
                url: '/file/fileList?mainId=1',
                reader: {
                    type: 'json',
                    root: 'rows',
                    totalProperty: 'total'
                }
            },
            autoLoad: true,
            listeners:{
                load:function(store,records){
                    debugger;
                }
            }
        });
        this.columns = [{
            text: '文件名称',
            width: 100,
            dataIndex: 'fileName',
            sortable: true,
            renderer: function (value, meta, record) {
                meta.style = "white-space:normal;";
                return value;
            }
        }, {
            text: '文件大小',
            width: 100,
            dataIndex: 'fileSize',
            sortable: true,
            renderer: function (value, meta, record) {
                meta.style = "white-space:normal;";
                return value;
            }
        }, {
            text: '创建人',
            width: 100,
            dataIndex: 'creator',
            sortable: true,
            renderer: function (value, meta, record) {
                meta.style = "white-space:normal;";
                return value;
            }
        }, {
            text: '创建时间',
            width: 100,
            dataIndex: 'createTime',
            sortable: true,
            renderer: function (value, meta, record) {
                meta.style = "white-space:normal;";
                return value;
            }
        }];
        this.callParent(arguments);
    }
});