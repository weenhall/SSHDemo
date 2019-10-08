function getAjaxResult2(responseText) {
    if (responseText[0] == '<') return responseText.substring(76, responseText.length - 9);
    else return responseText;
}
function getAjaxResult1(response) {
    if (Ext.isIE6 || Ext.isIE7 || Ext.isIE8 || Ext.isIE9) {
        var node = Ext.DomQuery.selectNode('string', response.responseXML);
        if (node)
            return node.text;
        else
            return getAjaxResult2(response.responseText);
    } else {
        var ret = Ext.DomQuery.selectNode('string', response.responseXML);
        if (ret)
            return ret.textContent;
        else
            return getAjaxResult2(response.responseText);
    }
}
window.getAjaxResult = function (response) {
    var result = getAjaxResult1(response);
    return result.replace('\\', '/');
}
Ext.define('Ext.ux.XmlJsonAjaxProxy', {
    extend: 'Ext.data.proxy.Ajax',
    initComponent: function () {

        this.callParent(arguments);
    },
    extractResponseData: function (response) {
        response.responseText = getAjaxResult(response);
        return response;
    }
});