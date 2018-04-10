package com.apex.holiday.dto;

import java.io.Serializable;

/**
 * @desc: holiday-api
 * @author: yangcheng
 * @createTime: 2018年4月2日 下午2:43:47
 * @version: v1.0
 */
public class HolidayTestDto implements Serializable {

    private static final long serialVersionUID = 1L;
    private String appId;
    private Long addTime = System.currentTimeMillis();
    private String city;
    private String price;

    /**
     * @return the appId
     */

    public String getAppId() {

        return appId;
    }



    /**
     * @param appId the appId to set
     */
    public void setAppId(String appId) {

        this.appId = appId;
    }



    /**
     * @return the addTime
     */

    public Long getAddTime() {

        return addTime;
    }



    /**
     * @param addTime the addTime to set
     */
    public void setAddTime(Long addTime) {

        this.addTime = addTime;
    }



    /**
     * @return the city
     */

    public String getCity() {

        return city;
    }



    /**
     * @param city the city to set
     */
    public void setCity(String city) {

        this.city = city;
    }



    @Override
    public String toString() {
        return "HolidayTestDto [appId=" + appId + ", addTime=" + addTime
                + ", city=" + city + "]";
    }

    /**
     * @return the price
     */

    public String getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(String price) {
        this.price = price;
    }

}
