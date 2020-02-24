package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.UserVo;

@Repository
public class UserRepository {
	public Boolean insert(UserVo vo) {
		Boolean result = false;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();

			String sql = "insert into user value(null, ?, ?, password(?), ?, now())";
			
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPassword());
			pstmt.setString(4, vo.getGender());

			int count = pstmt.executeUpdate();
			result = count==1;

		} catch (SQLException e) {
			e.printStackTrace();
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
	
	public UserVo findByEmailAndPassword(UserVo inputVo) {
		UserVo result = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			String sql =  " select no, name "+ 
						  "   from user"+
						  "  where email = ?"+
						  "    and password = password(?)";
			pstmt = conn.prepareStatement(sql); 
			
			pstmt.setString(1, inputVo.getEmail());
			pstmt.setString(2, inputVo.getPassword());
			
			rs = pstmt.executeQuery(); 
			
			if(rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);

				result = new UserVo();
				result.setNo(no);
				result.setName(name);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		
		return result;
	}
	
	public UserVo findByNo(Long no) {
		UserVo result = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			String sql =  " select name, email, gender"+ 
						  "   from user"+
						  "  where no = ?";
			pstmt = conn.prepareStatement(sql); 
			
			pstmt.setLong(1, no);
			
			rs = pstmt.executeQuery(); 
			
			if(rs.next()) {
				String name = rs.getString(1);
				String email = rs.getString(2);
				String gender = rs.getString(3);

				result = new UserVo();
				result.setNo(no);
				result.setName(name);
				result.setEmail(email);
				result.setGender(gender);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}
	
	public Boolean userNameUpdate(UserVo vo) {
		Boolean result = false;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();

			String sql = " update user " + 
					     "    set name = ? " + 
					     "  where no = ? ";
			
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getName());
			pstmt.setLong(2, vo.getNo());

			int count = pstmt.executeUpdate();
			result = count==1;

		} catch (SQLException e) {
			e.printStackTrace();
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
			conn = getConnection();

			String sql = " update user " + 
					     "    set password = password(?) " + 
					     "  where no = ? ";
			
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getPassword());
			pstmt.setLong(2, vo.getNo());

			int count = pstmt.executeUpdate();
			result = count==1;

		} catch (SQLException e) {
			e.printStackTrace();
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
			conn = getConnection();

			String sql = " update user " + 
					     "    set gender = ? " + 
					     "  where no = ? ";
			
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getGender());
			pstmt.setLong(2, vo.getNo());

			int count = pstmt.executeUpdate();
			result = count==1;

		} catch (SQLException e) {
			e.printStackTrace();
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
	
	private Connection getConnection() throws SQLException{
		try {
			Class.forName("org.mariadb.jdbc.Driver");

			//String url = "jdbc:mysql://127.0.0.1:3307/webdb";
			String url = "jdbc:mysql://192.168.1.97:3307/webdb";
			 
			return DriverManager.getConnection(url, "webdb", "webdb");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

}
