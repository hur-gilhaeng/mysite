package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douzone.mysite.vo.BoardVo;

public class BoardRepository {
	
	public Boolean insert(BoardVo vo) {
		Boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = " insert into board value(null, ?, ?, 0, now(), "
					+ " (select ifnull(max(b.g_no),0)+1 from board b), "
					+ " 1, 0, ?, 'visible' )";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setLong(3, vo.getUserNo());

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

	public List<BoardVo> findAll() {
		ResultSet rs = null;

		Connection conn = null;
		PreparedStatement pstmt = null;

		List<BoardVo> result = new ArrayList<BoardVo>();

		try {
			conn = getConnection();
			String sql = "   select b.no, b.title, u.name, b.hit, b.reg_date, "+
						 "          b.g_no, b.o_no, b.depth, b.user_no, b.shows "+
						 "     from board b "+
						 "     join user u on b.user_no = u.no "+
						 " order by g_no desc, o_no asc";
			pstmt = conn.prepareStatement(sql); 

			rs = pstmt.executeQuery(); 

			while (rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String userName = rs.getString(3);
				int hit = rs.getInt(4);
				String regDate = rs.getString(5);
				int gNo = rs.getInt(6);
				int oNo = rs.getInt(7);
				int depth = rs.getInt(8);
				Long userNo = rs.getLong(9);
				String shows = rs.getString(10);

				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setUserName(userName);
				vo.setHit(hit);
				vo.setRegDate(regDate);
				vo.setgNo(gNo);
				vo.setoNo(oNo);
				vo.setDepth(depth);
				vo.setUserNo(userNo);
				vo.setShows(shows);

				result.add(vo);
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

	public List<BoardVo> findPage(int page) {
		ResultSet rs = null;

		Connection conn = null;
		PreparedStatement pstmt = null;

		List<BoardVo> result = new ArrayList<BoardVo>();

		try {
			conn = getConnection();
			String sql = "   select b.no, b.title, u.name, b.hit, b.reg_date, "+
						 "          b.g_no, b.o_no, b.depth, b.user_no, b.shows "+
						 "     from board b "+
						 "     join user u on b.user_no = u.no "+
						 "    where b.shows='visible' or b.shows='deleted' "+
						 " order by g_no desc, o_no asc "+
						 "    limit ?,5";
			pstmt = conn.prepareStatement(sql); 

			pstmt.setInt(1, ((page)*5));
			
			rs = pstmt.executeQuery(); 

			while (rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String userName = rs.getString(3);
				int hit = rs.getInt(4);
				String regDate = rs.getString(5);
				int gNo = rs.getInt(6);
				int oNo = rs.getInt(7);
				int depth = rs.getInt(8);
				Long userNo = rs.getLong(9);
				String shows = rs.getString(10);

				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setUserName(userName);
				vo.setHit(hit);
				vo.setRegDate(regDate);
				vo.setgNo(gNo);
				vo.setoNo(oNo);
				vo.setDepth(depth);
				vo.setUserNo(userNo);
				vo.setShows(shows);

				result.add(vo);
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
	
	public BoardVo findNo(Long no) {
		ResultSet rs = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		
		BoardVo result = new BoardVo();;
		
		try {
			conn = getConnection();
			String sql = "   select b.title, b.contents, b.user_no "+
						 "     from board b "+
						 "    where b.no = ? ";
			pstmt = conn.prepareStatement(sql); 
			
			pstmt.setLong(1, no);
			
			rs = pstmt.executeQuery(); 

			if (rs.next()) {

				String title = rs.getString(1);
				String contents = rs.getString(2);
				Long userNo = rs.getLong(3);

				result.setNo(no);
				result.setTitle(title);
				result.setContents(contents);
				result.setUserNo(userNo);;

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
	
	public Boolean boardUpdate(BoardVo vo) {
		Boolean result = false;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();

			String sql = "update board set title = ?, contents = ?  where no = ? ";
			
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setLong(3, vo.getNo());

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
	
	public BoardVo findNoTogNo(Long no) {
		ResultSet rs = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		
		BoardVo result = new BoardVo();
		
		try {
			conn = getConnection();
			String sql = "   select b.g_no, b.o_no, b.depth "+
						 "     from board b "+
						 "    where b.no = ? ";
			pstmt = conn.prepareStatement(sql); 
			
			pstmt.setLong(1, no);
			
			rs = pstmt.executeQuery(); 

			if (rs.next()) {

				int gNo = rs.getInt(1);
				int oNo = rs.getInt(2);
				int depth = rs.getInt(3);

				result.setgNo(gNo);
				result.setoNo(oNo);
				result.setDepth(depth);

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

	public int findDdepth(BoardVo vo) {
		ResultSet rs = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int result = 0;
		
		try {
			conn = getConnection();
			String sql = "   select b.depth "+
						 "     from board b "+
						 "    where b.g_no = ? and b.o_no = ? and shows != 'invisible' ";
			pstmt = conn.prepareStatement(sql); 
			
			pstmt.setInt(1, vo.getgNo());
			pstmt.setInt(2, vo.getoNo()+1);
			
			rs = pstmt.executeQuery(); 

			if (rs.next()) {
				
				result = rs.getInt(1);
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
	
	public Boolean replyInsert(BoardVo vo) {
		Boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = " insert into board value(null, ?, ?, 0, now(), "
					+ " ?, ?+1, ?+1, ?, 'visible' )";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setInt(3, vo.getgNo());
			pstmt.setInt(4, vo.getoNo());
			pstmt.setInt(5, vo.getDepth());
			pstmt.setLong(6, vo.getUserNo());

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
	
	public Boolean updateTooNo(BoardVo vo) {
		Boolean result = false;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();

			String sql = "update board set o_no = o_no+1"
					   + " where g_no = ? and o_no > ?";
			
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, vo.getgNo());
			pstmt.setInt(2, vo.getoNo());

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
	
	public Boolean updateTooNoUp(BoardVo vo) {
		Boolean result = false;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();

			String sql = "update board set o_no = o_no-1"
					   + " where g_no = ? and o_no > ?";
			
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, vo.getgNo());
			pstmt.setInt(2, vo.getoNo());

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
	
	public Boolean boardDel(Long getNo) {
		Boolean result = false;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();

			String sql = "update board set shows = 'deleted' "
					   + " where no = ?";
			
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, getNo);

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
	
	public Boolean boardInvis(Long getNo) {
		Boolean result = false;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();

			String sql = "update board set shows = 'invisible' "
					   + " where no = ?";
			
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, getNo);

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
	
	public Boolean hitUpdate(Long no) {
		Boolean result = false;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();

			String sql = "update board set hit = hit+1"
					   + " where no = ?";
			
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, no);

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

	public int ckPage() {
		ResultSet rs = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int result = -1;
		
		try {
			conn = getConnection();
			String sql = "   select count(b.no) "+
						 "     from board b "+
						 "    where shows != 'invisible' ";
			pstmt = conn.prepareStatement(sql); 
			
			rs = pstmt.executeQuery(); 

			if (rs.next()) {
				
				result = rs.getInt(1);
				
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
