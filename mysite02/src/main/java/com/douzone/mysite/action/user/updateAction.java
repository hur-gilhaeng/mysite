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

public class updateAction implements Action {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		UserVo setUser = (UserVo)session.getAttribute("authUser");
		
		Long no = setUser.getNo();
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		
		UserVo editUser = new UserVo();
		editUser.setNo(no);
		editUser.setName(name);
		editUser.setPassword(password);
		editUser.setGender(gender);
		
		UserRepository ur = new UserRepository();
		
		if(!name.isEmpty()) {
			ur.userNameUpdate(editUser);
			setUser.setName(name);
		}
		if(!password.isEmpty()) {
			ur.userPasswordUpdate(editUser);
		}
		if(!gender.isEmpty()) {
			ur.userGenderUpdate(editUser);
		}

		session.setAttribute("authUser", setUser);
		WebUtil.redirect(request.getContextPath(), request, response);
	}

}
