package com.poscodx.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poscodx.jblog.repository.BlogRepository;
import com.poscodx.jblog.repository.CategoryRepository;
import com.poscodx.jblog.repository.UserRepository;
import com.poscodx.jblog.vo.BlogVo;
import com.poscodx.jblog.vo.CategoryVo;
import com.poscodx.jblog.vo.UserVo;


@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional
	public void join(UserVo vo) {
		vo.setPassword(passwordEncoder.encode(vo.getPassword()));
		userRepository.insert(vo);
		
		BlogVo blogVo = new BlogVo();
        blogVo.setId(vo.getId());
        blogVo.setTitle(vo.getName() + "'s 블로그");
        blogVo.setLogo("/assets/images/spring-logo.jpg");
        blogRepository.insert(blogVo);

        CategoryVo categoryVo = new CategoryVo();
        categoryVo.setId(blogVo.getId());
        categoryVo.setName("기본 카테고리");
        categoryRepository.insert(categoryVo);
		
	}

	public UserVo getUser(String id, String password) {
		
		return userRepository.findByIDAndPassword(id, password);
	}

	public UserVo getUser(String id) {
		
		return userRepository.findByID(id);
	}

}
