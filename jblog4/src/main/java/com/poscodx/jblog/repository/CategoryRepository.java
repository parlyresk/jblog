package com.poscodx.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.poscodx.jblog.vo.CategoryVo;

@Repository
public class CategoryRepository {
	
	private SqlSession sqlSession;
	
	public CategoryRepository(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public void insert(CategoryVo vo) {
		
		sqlSession.insert("category.insert", vo);
	}

	public List<CategoryVo> getCategoriesByBlogId(String id) {
		
		return sqlSession.selectList("category.getCategoriesByBlogId", id);
	}

	public CategoryVo getCategory(Long categoryNoValue) {
		
		return sqlSession.selectOne("category.getCategory", categoryNoValue);
	}

	public List<CategoryVo> getCategoriesWithPostCountByBlogId(String id) {
		
		return sqlSession.selectList("category.getCategoriesWithPostCountByBlogId", id);
	}

	public void delete(Long no) {
		sqlSession.delete("category.delete",no);
		
	}

	public Long getMaxCategoryNoByBlogId(String id) {
		
		return sqlSession.selectOne("category.getMaxCategoryNoByBlogId", id);
	}

}
