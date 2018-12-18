/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.util.enums;

/**
 * 日期格式
 * 
 * @author forest
 */
public enum DateFormatEnum {

    YYYYMMDDHHMMSSSSS("yyyyMMddHHmmssSSS"), YYYYMMDD("yyyyMMdd"), YYYYMMDDHHMMSS("yyyyMMddHHmmss");

    private String dateFormat;

    /**
     * @param dateFormat
     */
    private DateFormatEnum(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

}
