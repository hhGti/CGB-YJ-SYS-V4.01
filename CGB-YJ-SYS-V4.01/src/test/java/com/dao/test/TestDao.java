package com.dao.test;

import org.junit.Test;

import com.email.sys.dao.SysUserDao;
import com.email.sys.entity.SysUser;

public class TestDao extends TestBase{

	@Test
	public void selectTest() {
		SysUserDao dao = ctx.getBean("sysUserDao",SysUserDao.class);
		SysUser user = dao.selectUser("wangke");
		System.out.println(user);
	}
	
	@Test
	public void updateTest() {
		SysUserDao dao = ctx.getBean("sysUserDao",SysUserDao.class);
		SysUser user = new SysUser();
		user.setEmail("152@163.com");
		user.setId(15);
		user.setUsername("just");
		dao.updateUser(user);
		System.out.println(dao.selectUser("just"));
	}
	
	@Test
	public void insertTest() {
		SysUserDao dao = ctx.getBean("sysUserDao",SysUserDao.class);
		SysUser user = new SysUser();
		user.setMobile("1515151");
		user.setUsername("ttt");
		dao.insertUser(user);
		System.out.println(dao.selectUser("ttt"));
	}
}
