package com.douzone.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.BoardVo;

@Repository
public class BoardRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<BoardVo> findAll() {
		return sqlSession.selectList("board.findAll");
	}

	public BoardVo findNo(Long no) {
		return sqlSession.selectOne("board.findNo", no );
	}

	public Boolean delete(Long no) {
		int count = sqlSession.update("board.delete", no );
		return count == 1;
	}
	
}
