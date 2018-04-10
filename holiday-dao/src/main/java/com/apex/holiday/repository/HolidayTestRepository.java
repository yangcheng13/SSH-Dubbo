package com.apex.holiday.repository;

import java.io.Serializable;
import org.springframework.stereotype.Repository;
import com.apex.holiday.domain.HolidayTest;

/**
 * @desc: holiday-dao
 * @author: yangcheng
 * @createTime: 2018年4月2日 下午3:55:55
 * @version: v1.0
 */
@Repository("holidayTestRepository")
public class HolidayTestRepository
        extends DomainRepositoryImpl<HolidayTest, Serializable> {

}
