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

public class BoardModifyformAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("authUser")!=null) {
			Long userNo = ((UserVo)session.getAttribute("authUser")).getNo();
			
			String no = request.getParameter("no");
			Long getNo = Long.parseLong(no);
			
			BoardVo vb = new BoardRepository().findNo(getNo);
			
			if(userNo.equals(vb.getUserNo())) {
				
				request.setAttribute("mVo", vb);
				
				WebUtil.forward("/WEB-INF/views/board/modify.jsp", request, response);
				return;
			}
		}
		
		WebUtil.redirect(request.getContextPath()+"/board", request, response);
	}

}
