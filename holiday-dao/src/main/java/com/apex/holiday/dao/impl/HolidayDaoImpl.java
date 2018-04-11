/*
 * Copyright @ 2018 com.apexsoft holiday-dao 下午2:19:11 All right reserved.
 */
package com.apex.holiday.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.apex.holiday.dao.BaseDao;
import com.apex.holiday.dao.HolidayDao;
import com.apex.holiday.domain.HolidayTest;

/**
 * @desc: holiday-dao
 * @author: yangcheng
 * @createTime: 2018年4月11日 下午2:19:11
 * @version: v1.0
 */
@Repository
public class HolidayDaoImpl extends BaseDao implements HolidayDao {

    /*
     * (non-Javadoc)
     * 
     * @see com.apex.holiday.dao.HolidayDao#save(com.apex.holiday.domain.HolidayTest)
     */
    @Override
    public void save(HolidayTest entity) {
        this.hibernateTemplate.save(entity);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.apex.holiday.dao.HolidayDao#deleteById(java.lang.String)
     */
    @Override
    public void deleteById(String id) {
        HolidayTest vo = hibernateTemplate.get(HolidayTest.class, id);
        hibernateTemplate.delete(vo);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.apex.holiday.dao.HolidayDao#loadAll()
     */
    @Override
    public List<HolidayTest> loadAll() {
        return hibernateTemplate.loadAll(HolidayTest.class);

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.apex.holiday.dao.HolidayDao#update(com.apex.holiday.domain.HolidayTest)
     */
    @Override
    public void update(HolidayTest entity) {
        hibernateTemplate.update(entity);
    }

}

