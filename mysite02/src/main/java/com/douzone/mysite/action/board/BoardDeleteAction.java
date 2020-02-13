package com.douzone.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class BoardDeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		if(session.getAttribute("authUser")!=null) {
			Long auno = ((UserVo)session.getAttribute("authUser")).getNo();
			String unoSet = request.getParameter("uno");
			Long uno = Long.parseLong(unoSet);
			
			if(auno == uno) {
				String no = request.getParameter("no");
				Long getNo = Long.parseLong(no);

				new BoardRepository().boardDel(getNo);
			}
		}

		WebUtil.redirect(request.getContextPath()+"/board", request, response);
	}

}
