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

public class BoardWriteAction implements Action {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		if(session.getAttribute("authUser")!=null) {
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			Long userNo = ((UserVo)session.getAttribute("authUser")).getNo();
			BoardVo vo;
			
			String no = request.getParameter("no");
			if(no.isEmpty()) {

				vo = new BoardVo();
				vo.setTitle(title);
				vo.setContents(content);
				vo.setUserNo(userNo);

				if(!title.isEmpty()) {
					new BoardRepository().insert(vo);
				}
			}
			else {
				BoardRepository br = new BoardRepository();
				Long getNo = Long.parseLong(no);
				
				vo = br.findNoTogNo(getNo);
				br.updateTooNo(vo);
				
				vo.setTitle(title);
				vo.setContents(content);
				vo.setUserNo(userNo);
				if(!title.isEmpty()) {
					br.replyInsert(vo);
				}
			}
		}
		WebUtil.redirect(request.getContextPath()+"/board", request, response);
	}
}
