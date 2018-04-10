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
public class ResultVo<T> {

    private boolean success;// 是否成功标志

    private T data;// 成功时返回的数据

    private String error;// 错误信息

    public ResultVo() {}

    // 成功时的构造器
    public ResultVo(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    // 错误时的构造器
    public ResultVo(boolean success, String error) {
        this.success = success;
        this.error = error;
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

    public T getData() {

        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(T data) {

        this.data = data;
    }

    /**
     * @return the error
     */

    public String getError() {

        return error;
    }

    /**
     * @param error the error to set
     */
    public void setError(String error) {

        this.error = error;
    }

}


