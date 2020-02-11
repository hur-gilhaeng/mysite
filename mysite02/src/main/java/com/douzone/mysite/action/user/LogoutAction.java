package com.douzone.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class LogoutAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session != null && session.getAttribute("authUser") != null) {
			// 세션이 null이 아니고 authUser또한 null이 아닐때
			// 로그 아웃 처리!
			session.removeAttribute("authUser");
			session.invalidate();
		}
		WebUtil.redirect(request.getContextPath(), request, response);
	}

}
