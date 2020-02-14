package com.douzone.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class BoardWriteFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String no = request.getParameter("no");
		if(no!=null&&(!"".equals(no))) {
			request.setAttribute("no",no); // 본문의no가 있다면 답글로 처리한다.
		}
		WebUtil.forward("/WEB-INF/views/board/write.jsp", request, response);
	}

}
