package com.douzone.mysite.action.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.GuestbookDao;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class deleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String no = request.getParameter("no");
		String password = request.getParameter("password");
		
		Long noLong = Long.parseLong(no);
		
		GuestbookDao dao = new GuestbookDao();
		
		if(dao.ckPassword(noLong, password)){
			dao.delete(noLong);
		}
		else{
			
		}
		
		WebUtil.redirect(request.getContextPath()+"/guestbook", request, response);

	}

}
