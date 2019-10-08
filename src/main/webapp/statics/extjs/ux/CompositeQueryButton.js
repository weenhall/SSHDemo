/**
 * 组合查询
 * @author Bom Wu
 * @version 20160311
 */
Ext.define('Ext.ux.CompositeQueryButton', {
    extend: 'Ext.button.Button',
    text: '组合条件查询',
    iconCls: 'icon-search24',
    scale: 'medium',
    grid: null,
    fields: [],
    initComponent: function () {
        if (!this.grid) {
            this.callParent(arguments);
            return;
        }
        this.fields=new Array();
        var stores = {
            'string': Ext.create('Ext.data.ArrayStore', {
                id: 'string',
                fields: ['id', 'value', 'widget'],
                data: [['=', '等于', 'textfield'], ['like', '包含（like）', 'textfield'], ['<>', '不等于', 'textfield']]
            }),
            'integer': Ext.create('Ext.data.ArrayStore', {
                id: 'integer',
                fields: ['id', 'value', 'widget'],
                data: [['=', '等于', 'numberfield'], ['>', '大于', 'numberfield'], ['>=', '大于等于', 'numberfield'], ['<', '小于', 'numberfield'], ['<=', '小于等于', 'numberfield'], ['<>', '不等于', 'numberfield']]
            }),
            'date': Ext.create('Ext.data.ArrayStore', {
                id: 'date',
                fields: ['id', 'value', 'widget'],
                data: [['=', '等于', 'numberfield'], ['>', '大于', 'numberfield'], ['>=', '大于等于', 'numberfield'], ['<', '小于', 'numberfield'], ['<=', '小于等于', 'numberfield'], ['<>', '不等于', 'numberfield']]
            }),
            'combo': Ext.create('Ext.data.ArrayStore', {
                id: 'combo',
                fields: ['id', 'value', 'widget'],
                data: [['=', '等于', 'combobox']]
            })
        };
        this.handler = this.onClick;
        var columns = this.grid.columns;
        var items = [];

        function getField(filter, dataIndex) {
            var defaultField = {
                xtype: 'textfield',
                name: dataIndex + '-value',
                hideLabel: true,
                flex: 6,
                listeners: {
                    'change': function (field, newValue) {
                        field.ownerCt.down('checkbox').setValue(!Ext.isEmpty(newValue));
                    }
                }
            };
            if (filter.field) {
                Ext.apply(defaultField, filter.field);
            }
            switch (filter.type) {
                case 'string':
                    return defaultField;
                case 'integer':
                    defaultField.xtype = 'numberfield';
                    return defaultField;
                case 'date':
                    Ext.apply(defaultField, {
                        xtype: 'datefield',
                        format: 'Y-m-d'
                    });
                    return defaultField;
                default:
                    return defaultField;
            }
        }

        for (var i = 0; i < columns.length; i++) {
            var col = columns[i];
            if (!col.filter) {
                continue;
            }
            var dataIndex = col.filter.dataIndex || col.dataIndex;
            items.push({
                xtype: 'fieldcontainer',
                hideLabel: true,
                layout: 'hbox',
                items: [{
                    xtype: 'checkbox',
                    name: dataIndex + '-checkbox',
                    margin: '0 5 0 0',
                    boxLabel: col.text,
                    valueType: col.filter.type,
                    flex: 3
                }, {
                    xtype: 'combo',
                    name: dataIndex + '-combo',
                    queryMode: 'local',
                    hideLabel: true,
                    store: stores[col.filter.type],
                    editable: false,
                    displayField: 'value',
                    triggerAction: 'all',
                    valueField: 'id',
                    value: '=',
                    margin: '0 5 0 0',
                    flex: 2
                }, getField(col.filter, dataIndex)]
            });
            this.fields.push({name: dataIndex});
        }
        var form = this.form = Ext.create('Ext.form.Panel', {
            items: items,
            border: false,
            padding: '10px',
            autoScroll: true
        });
        var win = this.win = Ext.create('Ext.Window', {
            title: '组合条件查询',
            layout: 'fit',
            items: [form],
            width: 500,
            height: 400,
            closeAction: 'hide',
            tbar: [{
                xtype: 'button',
                text: '查询',
                handler: this.onQuery,
                scope: this
            }, {
                xtype: 'button',
                text: '清除',
                handler: this.onClear,
                scope: this
            }, '->', '（被选中的条件才会起作用）']
        });
        this.callParent(arguments);
    },
    onClick: function () {
        if (!this.grid) {
            return;
        }
        this.win.show();
    }
    ,
    onQuery: function () {
        var fields = this.fields;
        var conditions = [];
        for (var i = 0; i < fields.length; i++) {
            var field = fields[i];
            var checkbox = this.form.getForm().findField(field.name + '-checkbox');
            if (!checkbox.getValue()) {
                continue;
            }
            var valueField = this.form.getForm().findField(field.name + '-value');
            var operator = this.form.getForm().findField(field.name + '-combo').getValue();
            conditions.push({
                name: field.name,
                operator: operator,
                type: checkbox.valueType,
                value: (valueField instanceof Ext.form.field.Date)
                    ? Ext.util.Format.date(valueField.getValue(), 'Y-m-d')
                    : valueField.getValue()
            });
        }
        var proxy = this.grid.getStore().getProxy();
        proxy.extraParams = proxy.extraParams || {};
        proxy.extraParams['compositeQueries'] = Ext.encode(conditions);
        this.grid.getStore().reload();
    },
    onClear: function () {
        this.form.getForm().reset();
        delete this.grid.getStore().getProxy().extraParams.compositeQueries;
        this.grid.getStore().reload();
    },
    beforeDestroy: function () {
        Ext.destroy(this.fields, this.form, this.win);
        this.callParent(arguments);
    }
});