/*
 * Copyright @ 2018 com.apexsoft holiday-api 下午12:00:04 All right reserved.
 */
package com.apex.holiday.dto;

/**
 * @desc: holiday-api
 * @author: yangcheng
 * @createTime: 2018年4月8日 下午12:00:04
 * @version: v1.0
 */
public class ResultVo {

    private boolean success;// 是否成功标志

    private String data;// 成功时返回的数据

    public ResultVo() {}

    // 成功时的构造器
    public ResultVo(boolean success, String data) {
        this.success = success;
        this.data = data;
    }


    /**
     * @return the success
     */

    public boolean isSuccess() {

        return success;
    }

    /**
     * @param success the success to set
     */
    public void setSuccess(boolean success) {

        this.success = success;
    }

    /**
     * @return the data
     */

    public String getData() {

        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(String data) {

        this.data = data;
    }



}


