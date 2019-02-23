package com.email.sys.service;

import java.util.List;

import com.email.sys.entity.SysUser;

public interface SysUserService {

	int insertUser(SysUser user);
	
	int updateUser(SysUser user);
	
	SysUser selectUser(String userName);
	
	SysUser selectUserById(Integer id);
	
	int updatePw(Integer id,String opw,String npw1,String npw2);
	
	List<SysUser> selectUsers(String username);
}
