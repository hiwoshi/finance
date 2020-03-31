package com.finance.util.tradeutil;

/**
 * 编码格式
 *
 * @author jyuan
 */
public enum CharsetEnum {

    UTF8("UTF-8", ""),
    GBK("GBK", ""),
    GB2312("GB2312", "");
    private String code;
    private String remark;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    private CharsetEnum(String code, String remark) {
        this.code = code;
        this.remark = remark;
    }


}
