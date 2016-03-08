package cn.demoncat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/back")
public class MyController {

	@RequestMapping("/first")
	public String index(){
		System.out.println("i am coming");
		return "back";
	}
}
