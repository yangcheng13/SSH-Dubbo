package com.apex.holiday.repository;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @desc: holiday-dao
 * @author: yangcheng
 * @createTime: 2018年4月2日 下午3:54:27
 * @version: v1.0
 * @param <T> T
 * @param <PK> PK
 */
@Repository
public class DomainRepositoryImpl<T, PK extends Serializable>
        implements IDomainRepository<T, Serializable> {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getCurrentSession() {
        return this.sessionFactory.openSession();
    }

    @Override
    public void save(T entity) {
        Session session = this.getCurrentSession();
        session.save(entity);
        closeSession(session);
    }

    @Override
    public void update(T entity) {
        Session session = this.getCurrentSession();
        session.update(entity);
        closeSession(session);
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<T> find(String hql, Object[] param) {
        Session sess = this.getCurrentSession();
        List<T> list = null;
        if (param == null) {
            list = sess.createQuery(hql).list();
        } else {
            Query query = this.getCurrentSession().createQuery(hql);
            if (param != null && param.length > 0) {
                for (int i = 0; i < param.length; i++) {
                    Object obj = param[i];
                    if (obj instanceof Collection<?>) {
                        query.setParameterList(String.valueOf(i),
                                (Collection<?>) obj);
                    } else if (obj instanceof Object[]) {
                        query.setParameterList(String.valueOf(i),
                                (Object[]) obj);
                    } else {
                        query.setParameter(String.valueOf(i), obj);
                    }
                }
            }
            list = query.list();
        }
        return list;
    }

    @Override
    public void delete(Serializable pk) {
        Session session = this.getCurrentSession();
        session.delete(pk);
        closeSession(session);
    }

    protected void closeSession(Session session) {
        session.flush();
        session.clear();
        session.close();
    }

}
