package com.apex.holiday.dao;

import java.util.Set;
import com.apex.holiday.domain.Tuser;


/**  
 * @desc: holiday-dao  
 * @author: yangcheng  
 * @createTime: 2018年4月26日 下午4:32:29    
 * @version: v1.0    
 */    
public interface TuserDao {
    /**  
     * @author: yangcheng 
     * @createTime: 2018年4月26日 下午4:32:34    
     * @param userName String
     * @return Tuser  
     */  
    Tuser findUserByUsername(String userName);

    /**  
     * @author: yangcheng 
     * @createTime: 2018年4月26日 下午4:32:38    
     * @param userName String
     * @return Set<String>  
     */  
    Set<String> findRoles(String userName);

    /**  
     * @author: yangcheng 
     * @createTime: 2018年4月26日 下午4:32:42    
     * @param userName String
     * @return Set<String>  
     */  
    Set<String> findPermissions(String userName);
}
