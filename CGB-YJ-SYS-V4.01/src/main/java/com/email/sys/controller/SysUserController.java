package com.email.sys.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.email.common.vo.JsonResult;
import com.email.sys.entity.SysUser;
import com.email.sys.service.SysUserService;

@RequestMapping("/user/")
@Controller
public class SysUserController {

	@Autowired
	SysUserService sysUserService;
	
	@RequestMapping("doMyMessage")
	@ResponseBody
	public JsonResult doMyMessage(String userName) {
		return new JsonResult(sysUserService.selectUser(userName));
	}
	
	@RequestMapping("doInsertUser")
	@ResponseBody
	public JsonResult doInsertUser(SysUser user) {
		System.out.println(user);
		//?params={"username":12,"password":000,"email":152@163}
		int rows = sysUserService.insertUser(user);
		return new JsonResult("新增了"+rows+"条记录");
	}
	
	
	@RequestMapping("doUpdateUser")
	@ResponseBody
	public JsonResult doUpdateUser(SysUser user) {
		int rows = sysUserService.updateUser(user);
		return new JsonResult("修改了"+rows+"条记录");
	}
	
	@RequestMapping("doUpdatePw")
	@ResponseBody
	public JsonResult doUpdatePw(Integer id,String opw,String npw1,String npw2) {
		return new JsonResult(sysUserService.updatePw(id, opw, npw1, npw2));
	}
	
	@RequestMapping("doSelectUsers")
	@ResponseBody
	public JsonResult doSelectUsers(String name) {
		return new JsonResult(sysUserService.selectUsers(name));
	}
	
	@RequestMapping("doLoadPerson")
	@ResponseBody
	public JsonResult doLoadPerson() {
		SysUser user = (SysUser)SecurityUtils.getSubject().getPrincipal();
		return new JsonResult(sysUserService.selectUserById(user.getId()));
	}
	
		@RequestMapping("doLogin")
	   @ResponseBody
	   public JsonResult doLogin(String username,String password){
		   //1.获取Subject对象
		   Subject subject=SecurityUtils.getSubject();
		   //2.通过Subject提交用户信息,交给shiro框架进行认证操作
		   //2.1对用户进行封装
		   UsernamePasswordToken token=
		   new UsernamePasswordToken(
				   username,//身份信息
				   password);//凭证信息
		   //2.2对用户信息进行身份认证
		   subject.login(token);
		   //分析:
		   //1)token会传给shiro的SecurityManager
		   //2)SecurityManager将token传递给认证管理器
		   //3)认证管理器会将token传递给realm
		   System.out.println(username);
		   System.out.println(password);
		   return new JsonResult("login ok");
	   }
}
