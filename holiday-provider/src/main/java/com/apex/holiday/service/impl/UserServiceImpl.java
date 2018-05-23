package com.apex.holiday.service.impl;

import java.util.Set;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.apex.holiday.dao.TuserDao;
import com.apex.holiday.domain.Tuser;
import com.apex.holiday.service.UserService;

/**  
 * @desc: holiday-provider  
 * @author: yangcheng  
 * @createTime: 2018年4月26日 下午4:34:59    
 * @version: v1.0    
 */    
@Service("t_userService")
@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
public class UserServiceImpl implements UserService {

    @Resource
    private TuserDao tuserDao ;

    /*  
     *(non-Javadoc)  
     * @see com.apex.holiday.service.UserService#findUserByUsername(java.lang.String)  
     */  
    @Override
    public Tuser findUserByUsername(String username) {
        Tuser t_user = tuserDao.findUserByUsername(username);
        return t_user;
    }

    /*  
     *(non-Javadoc)  
     * @see com.apex.holiday.service.UserService#findRoles(java.lang.String)  
     */  
    @Override
    public Set<String> findRoles(String username) {
        return tuserDao.findRoles(username);
    }

    /*  
     *(non-Javadoc)  
     * @see com.apex.holiday.service.UserService#findPermissions(java.lang.String)  
     */  
    @Override
    public Set<String> findPermissions(String username) {
        return tuserDao.findPermissions(username);
    }
}
