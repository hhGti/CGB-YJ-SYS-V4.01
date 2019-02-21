package com.db.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@Controller
public class SysController {
	@RequestMapping("doIndexUI")
	public String doIndexUI() {
		return "index";
	}
}
