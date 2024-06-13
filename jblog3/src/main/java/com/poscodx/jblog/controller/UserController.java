package com.poscodx.jblog.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.poscodx.jblog.service.UserService;
import com.poscodx.jblog.vo.UserVo;




@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/join",method=RequestMethod.GET)
	public String join(@ModelAttribute UserVo vo) {
		System.out.println("user join get");
		return "user/join";
		
	}
	
	@RequestMapping(value="/join",method=RequestMethod.POST)
	public String join(@ModelAttribute @Valid UserVo vo, BindingResult result, Model model) {
		System.out.println("user join post");
		if(result.hasErrors()) {
			

			model.addAllAttributes(result.getModel());
			return "user/join";
		}
		
		userService.join(vo);
		
		
		return "redirect:/user/joinsuccess";
		
	}
	
	@RequestMapping(value="/joinsuccess",method=RequestMethod.GET)
	public String joinsuccess() {
		return "user/joinsuccess";
		
	}
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login() {
		return "user/login";
	}

}
