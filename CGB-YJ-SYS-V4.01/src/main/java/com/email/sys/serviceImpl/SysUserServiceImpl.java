package com.email.sys.serviceImpl;

import java.util.List;
import java.util.UUID;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.email.common.exeption.ServiceException;
import com.email.sys.dao.SysUserDao;
import com.email.sys.entity.SysUser;
import com.email.sys.service.SysUserService;

@Service
public class SysUserServiceImpl implements SysUserService{

	@Autowired
	SysUserDao sysUserDao;
	@Override
	public int insertUser(SysUser user) {
		String userName =user.getUsername();
		String password = user.getPassword();
		String email =user.getEmail();
		if("".equals(userName)||"".equals(password)||"".equals(email))
			throw new ServiceException("用户名，密码，或邮箱不能为空");
		String salt=UUID.randomUUID().toString();
		user.setSalt(salt);
		
		SimpleHash sHash=
			    new SimpleHash("MD5",user.getPassword(), salt);
				user.setPassword(sHash.toString());
				
		
		int rows =sysUserDao.insertUser(user);
		return rows;
	}

	@Override
	public int updateUser(SysUser user) {
		String userName =user.getUsername();
		//String password = user.getPassword();
		String email =user.getEmail();
		if("".equals(userName)||"".equals(email))
			throw new ServiceException("用户名或邮箱不能为空");
		SysUser u = sysUserDao.selectUserById(user.getId());
		SysUser u1 = sysUserDao.selectUser(user.getUsername());
		if((!user.getUsername().equals(u.getUsername()))&&u1!=null)
			throw new ServiceException("用户名不合法");
		int rows = sysUserDao.updateUser(user);
		return rows;
	}

	@Override
	public SysUser selectUser(String userName) {
		if("".equals(userName))
			throw new ServiceException("用户名不能为空");
		return sysUserDao.selectUser(userName);
	}

	@Override
	public int updatePw(Integer id,  String opw, String npw1, String npw2) {
		if(id==null)
			throw new ServiceException("id为空");
		if(!(npw1.equals(npw2)))
			throw new ServiceException("两次密码不一致");
		if("".equals(opw)||"".equals(npw1)||"".equals(npw2))
			throw new ServiceException("任何一个密码都不能为空");
		
		SysUser user = sysUserDao.selectUserById(id);
		String salt=user.getSalt();
		SimpleHash sHash = new SimpleHash("MD5",opw,salt);
		if(!(user.getPassword().equals(sHash.toHex()))) {
			System.out.println(user.getPassword());
		System.out.println(sHash.toHex());
		throw new ServiceException("旧密码不正确");
		}
		
		
		salt = UUID.randomUUID().toString();
		sHash = new SimpleHash("MD5",npw1,salt);
		sysUserDao.updatePw(id, salt, sHash.toHex());
		return 0;
	}

	@Override
	public List<SysUser> selectUsers(String name) {
		return sysUserDao.selectUsers(name);
	}

	@Override
	public SysUser selectUserById(Integer id) {
		if(id==null)
			throw new ServiceException("没有id");
		
		return sysUserDao.selectUserById(id);
	}
	
	

}
