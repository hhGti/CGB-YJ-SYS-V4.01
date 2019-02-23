package com.email.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

	@RequestMapping("doIndexUI")
	public String doIndexUI() {
		return "index";
	}
	
	@RequestMapping("doMailBoxUI")
	public String doMailBoxUI() {
		return "mailbox";
	}
	
	@RequestMapping("doMailBoxComposeUI")
	public String doMailBoxComposeUI() {
		return "mailbox-compose";
	}
	
	@RequestMapping("doMailBoxMessageUI")
	public String doMailBoxMessageUI() {
		return "mailbox-message";
	}
	
	@RequestMapping("doMailBoxTemplatesUI")
	public String doMailBoxTemplatesUI() {
		return "mailbox-templates";
	}
	
	@RequestMapping("doEditForm")
	public String doEditForm() {
		return "editForm";
	}
	
	@RequestMapping("doRightCommon")
	public String doRightCommon() {
		return "rightCommon";
	}
	
	@RequestMapping("doLoginUI")
	public String doLoginUI() {
		return "login";
	}

}
