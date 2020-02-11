package com.douzone.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class BoardViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String no = request.getParameter("no");
		Long getNo = Long.parseLong(no);
		
		BoardVo vb = new BoardRepository().findNo(getNo);
		
		request.setAttribute("vb", vb);
		
		WebUtil.forward("/WEB-INF/views/board/view.jsp", request, response);
	}

}
