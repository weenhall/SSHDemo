/**
 * Created by wen on 2017/8/3.
 */
Ext.define('com.ween.attfile.AttFile', {
    extend: 'Ext.data.Model',
    fields: ['id','mainId','fileType','fileName','creator','fileSize','content','createTime']
});