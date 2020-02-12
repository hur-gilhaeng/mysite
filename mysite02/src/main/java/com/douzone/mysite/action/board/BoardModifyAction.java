package com.douzone.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class BoardModifyAction implements Action {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		if(session.getAttribute("authUser")!=null) {
			String no = request.getParameter("no");
			Long getNo = Long.parseLong(no);
			
			String title = request.getParameter("title");
			String content = request.getParameter("content");

			BoardVo vo = new BoardVo();
			vo.setNo(getNo);
			vo.setTitle(title);
			vo.setContents(content);

			new BoardRepository().boardUpdate(vo);
			
			WebUtil.redirect(request.getContextPath()+"/board?a=view&no="+no, request, response);
			return;
		}
		
		WebUtil.redirect(request.getContextPath()+"/board", request, response);
	}
}
