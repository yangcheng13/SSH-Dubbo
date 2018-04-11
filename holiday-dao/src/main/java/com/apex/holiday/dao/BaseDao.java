/*
 * Copyright @ 2018 com.apexsoft holiday-dao 下午2:08:04 All right reserved.
 */

package com.apex.holiday.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;

/**
 * @desc: holiday-dao
 * @author: yangcheng
 * @createTime: 2018年4月11日 下午2:08:04
 * @version: v1.0
 */
public class BaseDao {
    @Autowired
    protected HibernateTemplate hibernateTemplate;
}

