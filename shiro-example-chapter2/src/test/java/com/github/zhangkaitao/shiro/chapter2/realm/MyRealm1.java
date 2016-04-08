package com.github.zhangkaitao.shiro.chapter2.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-25
 * <p>Version: 1.0
 */
public class MyRealm1 implements Realm {

    public String getName() {
        return "myrealm1";
    }

    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken; ////仅支持UsernamePasswordToken类型的Token  
    }

    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String username = (String)token.getPrincipal();  //userName
        String password = new String((char[])token.getCredentials()); //password
        if(!"zhang".equals(username)) {
            throw new UnknownAccountException(); //account error
        }
        if(!"123".equals(password)) {
            throw new IncorrectCredentialsException(); //password error
        }
      //如果身份认证验证成功，返回一个AuthenticationInfo实现；  
        return new SimpleAuthenticationInfo(username, password, getName());
    }
}
