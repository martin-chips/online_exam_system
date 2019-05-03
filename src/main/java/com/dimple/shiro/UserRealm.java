package com.dimple.shiro;

import com.dimple.entity.SysUser;
import com.dimple.service.SysUserService;
import com.dimple.utils.Constants;
import com.dimple.utils.Encodes;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.io.Serializable;

/**
 * @className: UserRealm
 * @description:
 * @auther: Dimple
 * @date: 04/29/19
 * @version: 1.0
 */
@Slf4j
public class UserRealm extends AuthorizingRealm {
    @Autowired
    SysUserService sysUserService;

    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        ShiroUser shiroUser = (ShiroUser) principalCollection.getPrimaryPrincipal();
        String userType = shiroUser.getUserType();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        if ("1".equals(userType)) {
            info.addRole("admin");
        } else if ("2".equals(userType)) {
            info.addRole("teacher");
        } else if ("3".equals(userType)) {
            info.addRole("student");
        }
        return info;
    }

    /**
     * 认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;

        String username = (String) usernamePasswordToken.getPrincipal();
        SysUser userDB = sysUserService.findSysUserByLoginName(username);
        //未找到账号
        if (userDB == null) {
            throw new UnknownAccountException();
        }
        if (Boolean.TRUE.equals(userDB.getLocked())) {
            throw new LockedAccountException(); //帐号锁定
        }
        byte[] salt = Encodes.decodeHex(userDB.getSalt());
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                new ShiroUser(userDB.getId(), userDB.getLoginName(), userDB.getNickName(), userDB.getIcon(), userDB.getUserType()),
                userDB.getPassword(), //密码
                ByteSource.Util.bytes(salt),
                getName()  //realm name
        );
        return authenticationInfo;
    }


    /**
     * 设定Password校验的Hash算法与迭代次数.
     */
    @PostConstruct
    public void initCredentialsMatcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(Constants.HASH_ALGORITHM);
        matcher.setHashIterations(Constants.HASH_INTERATIONS);
        setCredentialsMatcher(matcher);
    }


    /**
     * 清理缓存权限
     */
    public void clearCachedAuthorizationInfo() {
        this.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
    }

    public void removeUserAuthorizationInfoCache(String username) {
        SimplePrincipalCollection pc = new SimplePrincipalCollection();
        pc.add(username, super.getName());
        super.clearCachedAuthorizationInfo(pc);
    }


    /**
     * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
     */
    @Data
    public static class ShiroUser implements Serializable {
        private static final long serialVersionUID = -1373760761780840081L;
        public Integer id;
        public String loginName;
        public String nickName;
        public String icon;
        public String userType;

        public ShiroUser(Integer id, String loginName, String nickName, String icon, String userType) {
            this.id = id;
            this.loginName = loginName;
            this.nickName = nickName;
            this.icon = icon;
            this.userType = userType;
        }
    }
}
