package com.ween.entity;

/**
 * Created by wen on 2017/8/31.
 */
public class QueryParam {
    private String preName;//预处理名称
    private String preValue;//预处理值
    private String preType;//预处理类型like,=

    public String getPreName() {
        return preName;
    }

    public void setPreName(String preName) {
        this.preName = preName;
    }

    public String getPreValue() {
        return preValue;
    }

    public void setPreValue(String preValue) {
        this.preValue = preValue;
    }

    public String getPreType() {
        return preType;
    }

    public void setPreType(String preType) {
        this.preType = preType;
    }
}
