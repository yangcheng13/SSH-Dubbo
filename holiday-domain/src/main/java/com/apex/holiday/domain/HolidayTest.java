package com.apex.holiday.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

/**
 * 公共使用的基础城市类，缓存中保存的数据code对应本表的code
 */
@Entity
@Table(name = "holiday_test")
@DynamicInsert(true)
@DynamicUpdate(true)
public class HolidayTest {

    /**
     * NUM_60
     */
    public static final int NUM_60 = 60;

    /**
     * 应用id，主键，使用32位唯一值
     */
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "app_id")
    private String appId;

    /**
     * 创建时间
     */
    @Column(name = "add_time", updatable = false)
    private Long addTime = System.currentTimeMillis();

    /**
     * 城市全称 如北京市
     */
    @Column(name = "city", nullable = false, length = NUM_60)
    private String city;

    /**
     * price
     */
    @Column(name = "price", nullable = false, length = NUM_60)
    private String price;

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

}
