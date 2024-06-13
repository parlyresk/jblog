package com.poscodx.jblog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poscodx.jblog.security.Auth;
import com.poscodx.jblog.service.BlogService;
import com.poscodx.jblog.vo.BlogVo;
import com.poscodx.jblog.vo.CategoryVo;
import com.poscodx.jblog.vo.PostVo;


//블로그 정보 가져와야 함,카테고리,포스트 포함
@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {
	@Autowired
	private BlogService blogService;
	
	// url이 id/categoryNo/PostNo 순으로 와야함 optional 처리 해주기
	@RequestMapping({"","/{categoryNo}","/{categoryNo}/{postNo}"})
	public String index(
		@PathVariable("id") String id,
		@PathVariable(name = "categoryNo", required = false) Long categoryNo,
        @PathVariable(name = "postNo", required = false) Long postNo,
        Model model){
		
		if (categoryNo == null) {
            categoryNo = 1L;
        }

        if (postNo == null) {
            postNo = 1L;
        }
        BlogVo blog=blogService.getBlog(id);
        
        List<CategoryVo> categories = blogService.getCategoriesByBlogId(id);
        List<PostVo> posts = blogService.getPostsByCategoryNo(categoryNo);

        CategoryVo category = blogService.getCategory(categoryNo);
        PostVo post = blogService.getPost(postNo);
        
        model.addAttribute("blog",blog);
        model.addAttribute("categories", categories);
        model.addAttribute("posts", posts);
        model.addAttribute("category", category);
        model.addAttribute("post", post);
		
		return "blog/main";
	}
	
	@Auth
	@RequestMapping("/admin/basic")
	public String adminBasic(@PathVariable("id") String id,Model model) {
		
		BlogVo blog=blogService.getBlog(id);
		model.addAttribute("blog",blog);
		
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
