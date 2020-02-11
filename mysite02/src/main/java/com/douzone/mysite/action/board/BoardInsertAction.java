package com.douzone.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class BoardInsertAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Long userNo = ((UserVo)request.getSession().getAttribute("authUser")).getNo();
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		
		BoardVo vo = new BoardVo();
		
		vo.setTitle(title);
		vo.setContents(content);
		vo.setUserNo(userNo);
		
		new BoardRepository().insert(vo);

		WebUtil.redirect(request.getContextPath()+"/board", request, response);
	}

}
