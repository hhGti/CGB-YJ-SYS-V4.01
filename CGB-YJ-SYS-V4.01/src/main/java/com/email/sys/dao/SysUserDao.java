package com.email.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.email.sys.entity.SysUser;

public interface SysUserDao {

	int insertUser(SysUser user);
	
	int updateUser(SysUser user);
	
	SysUser selectUser(String userName);
	
	int updatePw(@Param("id")Integer id,@Param("salt")String salt,@Param("npw")String npw);
	
	SysUser selectUserById(Integer id);
	
	List<SysUser> selectUsers(@Param("username")String username);
	
}
