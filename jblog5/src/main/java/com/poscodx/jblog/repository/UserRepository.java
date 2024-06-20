package com.poscodx.jblog.repository;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.poscodx.jblog.vo.UserVo;

@Repository
public class UserRepository {
	
	private SqlSession sqlSession;
	
	public UserRepository(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }
	
	

	public void insert(UserVo vo) {
		
		sqlSession.insert("user.insert",vo);
	}



	public UserVo findByIDAndPassword(String id, String password) {
		
		return sqlSession.selectOne("user.findByIDAndPassword",Map.of("id",id,"password",password));
	}



	public UserVo findByID(String id) {
		
		return sqlSession.selectOne("user.findByID",id);
	}

}
