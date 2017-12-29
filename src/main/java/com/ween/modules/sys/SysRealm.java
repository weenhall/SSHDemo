package com.ween.modules.sys;

import com.ween.modules.sys.entity.User;
import com.ween.modules.sys.shiro.session.SessionDAO;
import com.ween.modules.sys.utils.UserUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by wen on 2017/12/28.
 */
@Transactional(readOnly = true)
public class SysRealm extends AuthorizingRealm{

    @Autowired
    private SessionDAO sessionDao;

    private Logger logger= LoggerFactory.getLogger(getClass());

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
       String userName=principals.getPrimaryPrincipal().toString();

        //查询数据库用户权限并授权
        List<String> permissions=new ArrayList<>();
        permissions.add("user:add");
        permissions.add("user:update");
        permissions.add("user:find");
        permissions.add("user:delete");

        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        info.addStringPermissions(permissions);
        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authToken){
        UsernamePasswordToken token= (UsernamePasswordToken) authToken;

        int activeSessionSize=sessionDao.getActiveSessions(false).size();
        if(logger.isDebugEnabled()){
            logger.debug("login submit,active session size: {}, username: {}",activeSessionSize,token.getUsername());
        }
        User users=UserUtils.getUsersByUemail(token.getUsername());
        if(users==null){
            throw new UnknownAccountException();
        }
        return new SimpleAuthenticationInfo(users,users.getPassword(),getName());
    }

    @Override
    protected void checkPermission(Permission permission, AuthorizationInfo info) {
        authorizationValidate(permission);
        super.checkPermission(permission, info);
    }

    @Override
    protected boolean[] isPermitted(List<Permission> permissions, AuthorizationInfo info) {
        if (permissions != null && !permissions.isEmpty()) {
            for (Permission permission : permissions) {
                authorizationValidate(permission);
            }
        }
        return super.isPermitted(permissions, info);
    }

    @Override
    public boolean isPermitted(PrincipalCollection principals, Permission permission) {
        authorizationValidate(permission);
        return super.isPermitted(principals, permission);
    }

    @Override
    protected boolean isPermittedAll(Collection<Permission> permissions, AuthorizationInfo info) {
        if (permissions != null && !permissions.isEmpty()) {
            for (Permission permission : permissions) {
                authorizationValidate(permission);
            }
        }
        return super.isPermittedAll(permissions, info);
    }

    /**
     * 授权验证方法
     *
     * @param permission
     */
    private void authorizationValidate(Permission permission) {
        // 模块授权预留接口
    }

    /**
     * 设定密码校验的Hash算法与迭代次数
     */
//    @PostConstruct
//    public void initCredentialsMatcher() {
//        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(HASH_ALGORITHM);
//        matcher.setHashIterations(HASH_INTERATIONS);
//        setCredentialsMatcher(matcher);
//    }

    /**
     * 清空用户关联权限认证，待下次使用时重新加载
     */
    public void clearCachedAuthorizationInfo(Principal principal) {
        SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
        clearCachedAuthorizationInfo(principals);
    }

    /**
     * 清空所有关联认证
     *
     * @Deprecated 不需要清空，授权缓存保存到session中
     */
    @Deprecated
    public void clearAllCachedAuthorizationInfo() {
        Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
        if (cache != null) {
            for (Object key : cache.keys()) {
                cache.remove(key);
            }
        }
    }
}
