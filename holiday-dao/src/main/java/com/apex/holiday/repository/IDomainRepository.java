package com.apex.holiday.repository;

import java.io.Serializable;
import java.util.List;

/**
 * @desc: holiday-dao
 * @author: yangcheng
 * @createTime: 2018年4月2日 下午3:56:12
 * @version: v1.0
 * @param <T> T
 * @param <PK> PK
 */
interface IDomainRepository<T, PK extends Serializable> {

    /**
     * @author: yangcheng
     * @createTime: 2018年4月2日 下午3:56:40
     * @param entity void
     */
    void save(T entity);

    /**
     * @author: yangcheng
     * @createTime: 2018年4月2日 下午3:56:42
     * @param pk void
     */
    void delete(PK pk);

    /**
     * @author: yangcheng
     * @createTime: 2018年4月2日 下午3:56:45
     * @param hql String
     * @param param Object[]
     * @return List<T>
     */
    List<T> find(String hql, Object[] param);

    /**
     * @author: yangcheng
     * @createTime: 2018年4月8日 下午5:10:06
     * @param entity void
     */
    void update(T entity);
}
