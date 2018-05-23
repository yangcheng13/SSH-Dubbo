package com.apex.holiday.shiro;

import java.util.Set;
import javax.annotation.Resource;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import com.apex.holiday.domain.Tuser;
import com.apex.holiday.service.UserService;


/**  
 * @desc: holiday-provider  
 * @author: yangcheng  
 * @createTime: 2018年4月26日 下午4:36:50    
 * @version: v1.0    
 */    
public class MyRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;

    /*  
     * 用于的权限的认证。
     *(non-Javadoc)  
     * @see org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection)  
     */  
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = principalCollection.getPrimaryPrincipal().toString() ;
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo() ;
        Set<String> roleName = userService.findRoles(username) ;
        Set<String> permissions = userService.findPermissions(username) ;
        info.setRoles(roleName);
        info.setStringPermissions(permissions);
        return info;
    }

    /*  
     * 首先执行这个登录验证
     *(non-Javadoc)  
     * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)  
     */  
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        //获取用户账号
        String username = token.getPrincipal().toString() ;
        Tuser user = userService.findUserByUsername(username) ;
        if (user != null){
            //将查询到的用户账号和密码存放到 authenticationInfo用于后面的权限判断。第三个参数随便放一个就行了。
            AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUserName(),user.getPassword(),
                    this.getName()) ;
            return authenticationInfo ;
        }else{
            return  null ;
        }
    }
}
