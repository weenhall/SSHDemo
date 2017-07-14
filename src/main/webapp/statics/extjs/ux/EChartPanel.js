Ext.define('Ext.ux.EChartPanel', {
    extend: 'Ext.panel.Panel',
    bodyPadding: 10,
    initComponent: function () {
        this.html = '<div id="' + this.id + '-innerCt"></div>';
        this.callParent(arguments);
        this.on('resize', function (grid, width, height) {
            this.echart.resize();
        }, this);
    },
    afterFirstLayout: function () {
        this.callParent(arguments);
        this.initEChart();
    },
    initEChart: function () {
        var echart = this.echart = echarts.init(document.getElementById(this.id + '-innerCt'));
        echart.setOption(this.option);
    },
    setOption: function (option, notMerge, notRefreshImmediately) {
        this.option = option;
        notMerge = notMerge || false, notRefreshImmediately = notRefreshImmediately || false;
        this.echart.setOption(this.option, notMerge, notRefreshImmediately);
    },
    onDestroy: function () {
        this.echart.dispose();
        delete this.echart;
        this.callParent(arguments);
    }
});