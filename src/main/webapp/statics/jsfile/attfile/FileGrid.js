/**
 * Created by wen on 2017/8/3.
 */
Ext.define('com.ween.attfile.FileGrid', {
    extend: 'Ext.grid.Panel',
    autoScroll: true,
    requires: ['com.ween.attfile.AttFile','Ext.ux.datetime.DateTimeField'],
    viewConfig:{
      enableTextSelection:true
    },
    cellTip:true,
    listeners:{
        afterRender: function () {
           // this.callParent(arguments);
            if (!this.cellTip) {
                return;
            }
            var view = this.getView();
            this.tip = new Ext.ToolTip({
                target: view.el,
                delegate: '.x-grid-cell-inner',
                trackMouse: true,
                renderTo: document.body,
                listeners: {
                    beforeshow: function updateTipBody(tip) {
                        if (Ext.isEmpty(tip.triggerElement.innerText)) {
                            return false;
                        }
                        tip.update(tip.triggerElement.innerText);
                    }
                }
            });
        }
    },
    initComponent: function () {
        this.store = Ext.create('Ext.data.Store', {
            model: 'com.ween.attfile.AttFile',
            proxy: {
                type: 'ajax',
                url: '/file/fileList?rootId=1',
                reader: {
                    type: 'json',
                    root: 'rows',
                    totalProperty: 'total'
                }
            },
            autoLoad: true,
            listeners:{
                load:function(store,records){
                    //debugger;
                }
            }
        });
        var btn=Ext.create('Ext.Button', {
            text: 'Click me',
            glyph:'xf011@FontAwesome',
            handler: function() {
                alert('You clicked the button!');
            }
        });
        this.columns = [{
            text: '文件名称',
            width: 100,
            dataIndex: 'fileName',
            sortable: true,
            renderer: function (value, meta, record) {
                meta.style = "white-space:normal;";
                //meta.tdAttr='data-qtip="' + Ext.String.htmlEncode(value) + '"';
                return value;
            }
        }, {
            text: '文件大小',
            width: 100,
            dataIndex: 'fileSize',
            sortable: true,
            renderer: function (value, meta, record) {
                meta.style = "white-space:normal;";
                var fileSize=value/(1048576);
                return fileSize.toFixed(2)+'M';
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
                return Ext.Date.format(new Date(value),'Y-m-d');
            }
        },{
            xtype:'actioncolumn',
            text:'操作',
            items:[
                {
                    iconCls:'adobe_read',
                    tooltip:'查看',
                    handler:function(grid,rowIndex){

                    },
                    isDisabled:function(view,rowIndex,colIndex,item,record){

                    }
                },{
                    iconCls:'delete_fill_16',
                    tooltip:'删除',
                    handler:function(grid,rowIndex){
                        var record=grid.getStore().getAt(rowIndex);
                        Ext.MessageBox.confirm('提示','确定要删除该记录吗?',function(btn){
                            if(btn=='yes'){
                                Ext.Ajax.request({
                                   url:'/file/deleteFileById',
                                    params:{
                                        id:record.get('id')
                                    },
                                    scope:this,
                                    success: function (response) {
                                        var text = Ext.decode(response.responseText);
                                        Ext.Msg.alert('提示', text.message);
                                        if(text.success){
                                            grid.getStore().remove(record);
                                        }
                                    }
                                });
                            }
                        },this);
                    },
                    isDisabled:function(view,rowIndex,colIndex,item,record){

                    }
                }
            ]

        }];
        this.tbar = [{
            text: '添加附件',
            glyph:0xf055,
            handler: function () {
                //var form=Ext.create('Ext.form.Panel', {
                //    width: 300,
                //    bodyPadding: 10,
                //    items: [{
                //        xtype: 'datetimefield',
                //        name: 'name',
                //        fieldLabel: 'Name',
                //        allowBlank: false  // requires a non-empty value
                //    }, {
                //        xtype: 'textfield',
                //        name: 'email',
                //        fieldLabel: 'Email Address'
                //    }]
                //});
                var me=this;
                var form=Ext.create('Ext.form.Panel',{
                    bodyPadding:'20 10 10 10',
                    items:[{
                        xtype:'filefield',
                        name:'files',
                        fieldLabel:'文件',
                        labelWidth:50,
                        msgTarget:'side',
                        allowBlank:false,
                        anchor:'100%',
                        buttonConfig:{
                            text:'选择',
                            id:'chooseBtn'
                        }
                    },{
                        xtype:'hidden',
                        name:'rootId',
                        value:1
                    }
                    ],
                    buttons:[
                        {
                            text:'上传',
                            handler: function () {
                                if(form.isValid()) {
                                    form.submit({
                                        url:'/file/upload',
                                        waitMsg:'上传中...',
                                        success:function(form,action){
                                            Ext.Msg.alert('提示',action.result.message);
                                            me.store.reload();
                                        }
                                    });
                                }else {
                                    Ext.Msg.alert('提示','未选择任何文件');
                                }
                            }
                        },{
                            text:'取消',
                            handler:function(){
                                upWin.close();
                            }
                        }
                    ]
                });
                var upWin=Ext.create('Ext.window.Window', {
                    title: '添加附件',
                    height: 200,
                    width: 400,
                    layout: 'fit',
                    items: form
                }).show();
            },
            scope: this
        }];
        this.callParent(arguments);
    }
});