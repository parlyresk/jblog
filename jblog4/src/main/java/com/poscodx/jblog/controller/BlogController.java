package com.poscodx.jblog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.poscodx.jblog.security.Auth;
import com.poscodx.jblog.service.BlogService;
import com.poscodx.jblog.service.FileUploadService;
import com.poscodx.jblog.vo.BlogVo;
import com.poscodx.jblog.vo.CategoryVo;
import com.poscodx.jblog.vo.PostVo;

//블로그 정보 가져와야 함,카테고리,포스트 포함
@Controller
@RequestMapping("/{id:(?!assets|upload-images).*}")
public class BlogController {
	@Autowired
	private BlogService blogService;
	
	@Autowired
    private FileUploadService fileUploadService;

	// url이 id/categoryNo/PostNo 순으로 와야함 optional 처리 해주기
	@RequestMapping({ "", "/{categoryNo}", "/{categoryNo}/{postNo}" })
	public String index(@PathVariable("id") String id,
			@PathVariable(name = "categoryNo", required = false) Optional<Long> categoryNo,
			@PathVariable(name = "postNo", required = false) Optional<Long> postNo, Model model) {
		
		Long categoryNoValue = categoryNo.orElse(blogService.getMaxCategoryNoByBlogId(id));
        Long postNoValue = postNo.orElse(blogService.getMaxPostNoByCategoryNo(categoryNoValue));
		

		
		
		BlogVo blog = blogService.getBlog(id);

		List<CategoryVo> categories = blogService.getCategoriesByBlogId(id);
		List<PostVo> posts = blogService.getPostsByCategoryNo(categoryNoValue);

		CategoryVo category = blogService.getCategory(categoryNoValue);
		PostVo post = blogService.getPost(postNoValue);

		model.addAttribute("blog", blog);
		model.addAttribute("categories", categories);
		model.addAttribute("posts", posts);
		model.addAttribute("category", category);
		model.addAttribute("post", post);

		return "blog/main";
	}

	@Auth
	@RequestMapping(value = "/admin/basic", method = RequestMethod.GET)
	public String adminBasic(@PathVariable("id") String id, Model model) {

		BlogVo blog = blogService.getBlog(id);
		model.addAttribute("blog", blog);

		return "blog/admin-basic";
	}
	
	@Auth
    @RequestMapping(value = "/admin/basic/update", method = RequestMethod.POST)
    public String update(@PathVariable("id") String id, @ModelAttribute BlogVo blogVo, @RequestParam("logoFile") MultipartFile file) {
		if (file.isEmpty()) {
            
            BlogVo blog = blogService.getBlog(id);
            blogVo.setLogo(blog.getLogo());
        } else {
            String logo = fileUploadService.restore(file);
            if (logo != null) {
                blogVo.setLogo(logo);
            }
        }
        

        blogService.updateBlog(blogVo);
        return "redirect:/{id}/admin/basic";
    }

	@Auth
	@RequestMapping(value = "/admin/category", method = RequestMethod.GET)
	public String adminCategory(@PathVariable("id") String id, Model model) {

		BlogVo blog = blogService.getBlog(id);

		List<CategoryVo> categories = blogService.getCategoriesWithPostCountByBlogId(id);

		model.addAttribute("blog", blog);
		model.addAttribute("categories", categories);

		return "blog/admin-category";
	}

	@Auth
	@RequestMapping(value = "/admin/category/add", method = RequestMethod.POST)
	public String addCategory(@PathVariable("id") String id, CategoryVo categoryVo) {

		categoryVo.setId(id);

		blogService.addCategory(categoryVo);
		return "redirect:/{id}/admin/category";

	}

	@Auth
	@RequestMapping("/admin/category/delete/{no}")
	public String deleteCategory(@PathVariable("id") String id, @PathVariable("no") Long no) {
		blogService.deleteCategory(no);
		return "redirect:/{id}/admin/category";
	}

	@Auth
	@RequestMapping(value = "/admin/write", method = RequestMethod.GET)
	public String adminWrite(@PathVariable("id") String id, Model model) {

		BlogVo blog = blogService.getBlog(id);
		List<CategoryVo> categories = blogService.getCategoriesByBlogId(id);

		model.addAttribute("blog", blog);
		model.addAttribute("categories", categories);

		return "blog/admin-write";
	}

	@Auth
	@RequestMapping(value = "/admin/write", method = RequestMethod.POST)
	public String addPost(@PathVariable("id") String id, @ModelAttribute PostVo postVo) {
		blogService.addPost(postVo);
		return "redirect:/{id}/admin/write";
	}
}
