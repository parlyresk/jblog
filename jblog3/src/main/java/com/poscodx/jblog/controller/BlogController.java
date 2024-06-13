package com.poscodx.jblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


//블로그 정보 가져와야 함,카테고리,포스트 포함
@Controller
public class BlogController {
	
	//id/categoryNo/PostNo 순으로 와야함
	@RequestMapping({"/{id}","/{id}/{categoryNo}","/{id}/{categoryNo}/{postNo}"})
	public String index(
		@PathVariable("id") String id,
		@PathVariable("categoryNo") Long categoryNo,
		@PathVariable("postNo") Long postNo){
		
		return "blog/main";
	}
}
