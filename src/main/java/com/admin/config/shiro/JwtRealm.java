package com.admin.config.shiro;

import com.admin.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class JwtRealm extends AuthorizingRealm {
    @Autowired
    SysUserService sysUserService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.debug("com.admin.config.shiro.JwtRealm::doGetAuthorizationInfo");
        String username = principalCollection.toString();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(sysUserService.queryRoles(username));
        authorizationInfo.setStringPermissions(sysUserService.queryPermissions(username));
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String credentials = (String) token.getCredentials();
        String username = null;
        try {
            boolean verify = JwtUtil.verify(credentials);
            if (!verify) {
                throw new AuthenticationException();
            }
            username = JwtUtil.getClaim(credentials, JwtUtil.ACCOUNT);
        } catch (Exception e) {
            throw new AuthenticationException(e.getMessage());
        }

        return new SimpleAuthenticationInfo(
                username,
                credentials,
                getName()
        );
    }
}
