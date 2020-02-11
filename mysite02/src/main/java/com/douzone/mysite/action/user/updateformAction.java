package com.douzone.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.repository.UserRepository;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class updateformAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 접근제어 (access Control List, ACL)
		HttpSession session = request.getSession();
		if(session == null) { // 세션이 null일때 메인으로 보냄
			WebUtil.redirect(request.getContextPath(), request, response);
			return;
		}
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) { // authUser가 null일때 메인으로 보냄
			WebUtil.redirect(request.getContextPath(), request, response);
			return;
		}
		
		UserVo userVo = new UserRepository().findByNo(authUser.getNo());
		request.setAttribute("profile", userVo);
		
		WebUtil.forward("/WEB-INF/views/user/updateform.jsp", request, response);

	}

}
