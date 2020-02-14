package com.douzone.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class BoardModifyAction implements Action {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		if(session.getAttribute("authUser")!=null) {
			// 수정 작업 전 사용자 확인...
			Long auno = ((UserVo)session.getAttribute("authUser")).getNo();
			String uno = request.getParameter("uno");
			Long getUno = Long.parseLong(uno);
			if(auno == getUno) {
				String no = request.getParameter("no");
				Long getNo = Long.parseLong(no);

				String title = request.getParameter("title");
				String content = request.getParameter("content");

				BoardVo vo = new BoardVo();
				vo.setNo(getNo);
				vo.setTitle(title);
				vo.setContents(content);
				vo.setUserNo(getUno);
				
				new BoardRepository().boardUpdate(vo);

				// veiw와 같이 게시글 구현
				request.setAttribute("vb", vo);
				WebUtil.forward("/WEB-INF/views/board/view.jsp", request, response);
				return;
			}
		}
		
		WebUtil.redirect(request.getContextPath()+"/board", request, response);
	}
}
