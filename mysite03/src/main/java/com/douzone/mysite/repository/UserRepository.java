package com.douzone.mysite.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.UserVo;

@Repository
public class UserRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int insert(UserVo vo) {

		return sqlSession.insert("user.insert", vo);
	}
	
	public UserVo findByEmailAndPassword(UserVo inputVo) {
		
		UserVo result = sqlSession.selectOne("user.findByEmailAndPassword",inputVo);
		
		return result;
	}
	
	public UserVo findByNo(Long no) {
		
		UserVo result = sqlSession.selectOne("user.findByNo",no);
		
		return result;
	}
	
	public int uptate(UserVo vo) {
		
		return sqlSession.update("user.update", vo);
	}

}
