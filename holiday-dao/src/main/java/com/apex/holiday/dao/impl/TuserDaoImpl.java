/*
 * Copyright @ 2018 com.apexsoft holiday-dao 下午2:19:11 All right reserved.
 */
package com.apex.holiday.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import com.apex.holiday.dao.BaseDao;
import com.apex.holiday.dao.TuserDao;
import com.apex.holiday.domain.Tuser;

/**
 * @desc: holiday-dao
 * @author: yangcheng
 * @createTime: 2018年4月11日 下午2:19:11
 * @version: v1.0
 */
@Repository
public class TuserDaoImpl extends BaseDao implements TuserDao {

    /*  
     *(non-Javadoc)  
     * @see com.apex.holiday.dao.TuserDao#findUserByUsername(java.lang.String)  
     */  
    @Override
    @SuppressWarnings("unchecked")
    public Tuser findUserByUsername(String userName) {
        String sql = "select * from t_user where userName='#{userName}'";
        sql = sql.replace("#{userName}", userName);
        SQLQuery sq = this.hibernateTemplate.getSessionFactory()
                .getCurrentSession().createSQLQuery(sql).addEntity(Tuser.class);
        List<Tuser> lst = (List<Tuser>)sq.list();
        if(!CollectionUtils.isEmpty(lst)){
            return lst.get(0);
        }
        return null;
            
    }

    /*  
     *(non-Javadoc)  
     * @see com.apex.holiday.dao.TuserDao#findRoles(java.lang.String)  
     */  
    @SuppressWarnings("unchecked")
    @Override
    public Set<String> findRoles(String userName) {
        String sql = "select r.roleName from t_user u,t_role r where u.roleId=r.id and u.userName='#{userName}'";
        sql = sql.replace("#{userName}", userName);
        SQLQuery sq = this.hibernateTemplate.getSessionFactory().getCurrentSession().createSQLQuery(sql);
        List<Object> lst = (List<Object>)sq.list();
        Set<String> roles = new HashSet<String>();
        if(!CollectionUtils.isEmpty(lst)){
            for (Object o : lst) {
                roles.add((String)o);
            }
            return roles;
        }
        return null;
    }

    /*  
     *(non-Javadoc)  
     * @see com.apex.holiday.dao.TuserDao#findPermissions(java.lang.String)  
     */  
    @SuppressWarnings("unchecked")
    @Override
    public Set<String> findPermissions(String userName) {
        String sql = " select p.permissionName from t_user u,t_role r,t_permission p where u.roleId=r.id and p.roleId=r.id and u.userName='#{userName}'";
        sql = sql.replace("#{userName}", userName);
        SQLQuery sq = this.hibernateTemplate.getSessionFactory().getCurrentSession().createSQLQuery(sql);
        List<Object> lst = (List<Object>)sq.list();
        Set<String> permissions = new HashSet<String>();
        if(!CollectionUtils.isEmpty(lst)){
            for (Object o : lst) {
                permissions.add((String)o);
            }
            return permissions;
        }
        return null;
    }

}

