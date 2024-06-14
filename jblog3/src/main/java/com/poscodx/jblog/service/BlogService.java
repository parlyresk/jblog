package com.poscodx.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscodx.jblog.repository.BlogRepository;
import com.poscodx.jblog.repository.CategoryRepository;
import com.poscodx.jblog.repository.PostRepository;
import com.poscodx.jblog.vo.BlogVo;
import com.poscodx.jblog.vo.CategoryVo;
import com.poscodx.jblog.vo.PostVo;

@Service
public class BlogService {
	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
    private PostRepository postRepository;

	public List<CategoryVo> getCategoriesByBlogId(String id) {
		
		return categoryRepository.getCategoriesByBlogId(id);
	}

	public List<PostVo> getPostsByCategoryNo(Long categoryNo) {
		
		return postRepository.getPostsByCategoryNo(categoryNo);
	}

	public CategoryVo getCategory(Long categoryNo) {
		
		return categoryRepository.getCategory(categoryNo);
	}

	public PostVo getPost(Long postNo) {
		
		return postRepository.getPost(postNo);
	}

	public BlogVo getBlog(String id) {
		
		return blogRepository.getBlog(id);
	}

	public List<CategoryVo> getCategoriesWithPostCountByBlogId(String id) {
		
		return categoryRepository.getCategoriesWithPostCountByBlogId(id);
	}

	public void addCategory(CategoryVo categoryVo) {
		categoryRepository.insert(categoryVo);
		
	}

	public void deleteCategory(Long no) {
		
		
        postRepository.deleteByCategoryNo(no);
        
        
        categoryRepository.delete(no);
		
	}

	public void addPost(PostVo postVo) {
		postRepository.insert(postVo);
		
	}

	public Long getMaxCategoryNoByBlogId(String id) {
		
		return categoryRepository.getMaxCategoryNoByBlogId(id);
	}

	public Long getMaxPostNoByCategoryNo(Long categoryNo) {
		
		return postRepository.getMaxPostNoByCategoryNo(categoryNo);
	}

}
