package com.apex.holiday.service;

import java.util.Set;
import com.apex.holiday.domain.Tuser;
/**  
 * @desc: holiday-provider  
 * @author: yangcheng  
 * @createTime: 2018年4月26日 下午4:35:44    
 * @version: v1.0    
 */    
public interface UserService {
    
    /**  
     * @author: yangcheng 
     * @createTime: 2018年4月26日 下午4:35:59    
     * @param username String
     * @return Tuser  
     */  
    Tuser findUserByUsername(String username);

    /**  
     * @author: yangcheng 
     * @createTime: 2018年4月26日 下午4:36:10    
     * @param username String
     * @return Set<String>  
     */  
    Set<String> findRoles(String username);

    /**  
     * @author: yangcheng 
     * @createTime: 2018年4月26日 下午4:36:16    
     * @param username String
     * @return Set<String>  
     */  
    Set<String> findPermissions(String username);
}
