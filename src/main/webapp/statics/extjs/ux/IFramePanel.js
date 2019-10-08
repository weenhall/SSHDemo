Ext.define("Ext.ux.IFramePanel", {
    extend: 'Ext.panel.Panel',
    alias: 'widget.iframepanel',
    url: null,
    initComponent: function () {
        if (this.url == null) {
            this.html = 'url未定义。';
        } else {
            this.html = '<iframe id="iframe-' + this.id + '" src="' + this.url
                + '" scrolling="auto" frameborder="0" width="100%" height="100%"' +
                ' onload="javascript:Ext.getCmp(\'' + this.id + '\').fireEvent(\'load\');"></iframe>';
        }
        this.callParent(arguments);
        this.addEvents('load');
        this.on('close', function () {
            setTimeout(function () {
                if (Ext.isIE) {
                    CollectGarbage();
                }
            }, 2000);
        });
    }
});