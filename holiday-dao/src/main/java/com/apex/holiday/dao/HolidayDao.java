/*
 * Copyright @ 2018 com.apexsoft holiday-dao 下午2:16:50 All right reserved.
 */
package com.apex.holiday.dao;

import java.util.List;
import com.apex.holiday.domain.HolidayTest;

/**
 * @desc: holiday-dao
 * @author: yangcheng
 * @createTime: 2018年4月11日 下午2:16:50
 * @version: v1.0
 */
public interface HolidayDao {
    /**
     * @author: yangcheng
     * @createTime: 2018年4月2日 下午3:56:40
     * @param entity void
     */
    void save(HolidayTest entity);

    /**
     * @author: yangcheng
     * @createTime: 2018年4月2日 下午3:56:42
     * @param pk void
     */
    void deleteById(String id);

    /**
     * @author: yangcheng
     * @createTime: 2018年4月2日 下午3:56:45
     * @return List<HolidayTest>
     */
    List<HolidayTest> loadAll();

    /**
     * @author: yangcheng
     * @createTime: 2018年4月8日 下午5:10:06
     * @param entity void
     */
    void update(HolidayTest entity);

}

