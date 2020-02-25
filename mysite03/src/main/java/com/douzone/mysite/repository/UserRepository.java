package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.exception.UserRepositoryException;
import com.douzone.mysite.vo.UserVo;

@Repository
public class UserRepository {
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private SqlSession sqlSession;
	
	public Boolean insert(UserVo vo) {
		int count = sqlSession.insert("user.insert", vo);
		Boolean result = false;

		result = count==1;
		return result;
	}
	
	public UserVo findByEmailAndPassword(UserVo inputVo) {
		
		UserVo result = sqlSession.selectOne("user.findByEmailAndPassword",inputVo);
		
		return result;
	}
	
	public UserVo findByNo(Long no) {
		
		UserVo result = sqlSession.selectOne("user.findByNo",no);
		
		return result;
	}
	
	public Boolean userNameUpdate(UserVo vo) {
		Boolean result = false;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();

			String sql = " update user " + 
					     "    set name = ? " + 
					     "  where no = ? ";
			
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getName());
			pstmt.setLong(2, vo.getNo());

			int count = pstmt.executeUpdate();
			result = count==1;

		} catch (SQLException e) {
			throw new UserRepositoryException("error :" + e);
		} finally {
			try {
				if(conn != null) {
					conn.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public Boolean userPasswordUpdate(UserVo vo) {
		Boolean result = false;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();

			String sql = " update user " + 
					     "    set password = password(?) " + 
					     "  where no = ? ";
			
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getPassword());
			pstmt.setLong(2, vo.getNo());

			int count = pstmt.executeUpdate();
			result = count==1;

		} catch (SQLException e) {
			throw new UserRepositoryException("error :" + e);
		} finally {
			try {
				if(conn != null) {
					conn.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

	public Boolean userGenderUpdate(UserVo vo) {
		Boolean result = false;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();

			String sql = " update user " + 
					     "    set gender = ? " + 
					     "  where no = ? ";
			
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getGender());
			pstmt.setLong(2, vo.getNo());

			int count = pstmt.executeUpdate();
			result = count==1;

		} catch (SQLException e) {
			throw new UserRepositoryException("error :" + e);
		} finally {
			try {
				if(conn != null) {
					conn.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	

/* 
 *  conn = getConnection();
 *  위의 코드를 아래의 코드로 바꾸어 사용
 *  conn = dataSource.getConnection();
 *	바꾼 후에 아래의 getConnection()를 제거한다.
 */

//	private Connection getConnection() throws SQLException{
//		Connection conn = null;
//		try {
//			Class.forName("org.mariadb.jdbc.Driver");
//
//			//String url = "jdbc:mysql://127.0.0.1:3307/webdb";
//			String url = "jdbc:mysql://192.168.1.97:3307/webdb";
//			
//			conn = DriverManager.getConnection(url, "webdb", "webdb");
//
//		} catch (ClassNotFoundException e) {
//			throw new UserRepositoryException("드라이버 로딩 실패 :" + e);
//		}
//		return conn;
//	}

}
