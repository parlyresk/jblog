package com.poscodx.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.poscodx.jblog.vo.PostVo;

@Repository
public class PostRepository {
	
private SqlSession sqlSession;
	
	public PostRepository(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public List<PostVo> getPostsByCategoryNo(Long categoryNoValue) {
		
		return sqlSession.selectList("post.getPostsByCategoryNo", categoryNoValue);
	}

	public PostVo getPost(Long postNoValue) {
		
		return sqlSession.selectOne("post.getPost", postNoValue);
	}

	public void deleteByCategoryNo(Long no) {
		sqlSession.delete("post.deleteByCategoryNo", no);
		
	}

	public void insert(PostVo postVo) {
		sqlSession.insert("post.insert", postVo);
		
	}

	public Long getMaxPostNoByCategoryNo(Long categoryNoValue) {
		
		return sqlSession.selectOne("post.getMaxPostNoByCategoryNo", categoryNoValue);
	}

}
