package com.email.sys.service.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.email.sys.dao.SysUserDao;
import com.email.sys.entity.SysUser;

@Service
public class ShiroUserRealm extends AuthorizingRealm{

	@Autowired
	SysUserDao sysUserDao;
	/**
	 * 设置凭证匹配器
	 */
	@Override
	public void setCredentialsMatcher(
	    CredentialsMatcher credentialsMatcher) {
		//构建凭证匹配对象
		HashedCredentialsMatcher cMatcher=
		new HashedCredentialsMatcher();
		//设置加密算法
		cMatcher.setHashAlgorithmName("MD5");
		//设置加密次数
		cMatcher.setHashIterations(1);
		super.setCredentialsMatcher(cMatcher);
	}

	

	//完成认证信息的获取与封装
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken upToken =
			(UsernamePasswordToken) token;
		
		String username = upToken.getUsername();
		
		SysUser user =
				sysUserDao.selectUser(username);
		if(user==null)
			throw new UnknownAccountException();
		if(user.getValid()==0)
			throw new LockedAccountException();
		
		ByteSource credentialsSalt =
		ByteSource.Util.bytes(user.getSalt());		
		SimpleAuthenticationInfo info =
				new SimpleAuthenticationInfo(
						user,//principal(身份)
						user.getPassword(),//hashedCredentials
						credentialsSalt,
						getName());//realmName
		return info;
	}



	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
	}
}
