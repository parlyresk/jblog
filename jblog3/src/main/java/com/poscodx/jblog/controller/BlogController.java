package com.poscodx.jblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


//블로그 정보 가져와야 함,카테고리,포스트 포함
@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {
	
	//id/categoryNo/PostNo 순으로 와야함 optional 처리 해주기
	@RequestMapping({"","/{categoryNo}","/{categoryNo}/{postNo}"})
	public String index(
		@PathVariable("id") String id,
		@PathVariable("categoryNo") Long categoryNo,
		@PathVariable("postNo") Long postNo){
		
		return "blog/main";
	}
	
	// @Auth
	@RequestMapping("/admin/basic")
	public String adminBasic() {
		return "blog/admin-basic";
	}
	
	// @Auth
	@RequestMapping("/admin/category")
	public String adminCategory() {
		return "blog/admin-category";
	}
	
	// @Auth
	@RequestMapping("/admin/write")
	public String adminWrite() {
		return "blog/admin-write";
	}
}
