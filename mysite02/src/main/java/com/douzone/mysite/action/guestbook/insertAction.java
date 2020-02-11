package com.douzone.mysite.action.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.GuestbookDao;
import com.douzone.mysite.vo.GuestbookVo;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class insertAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		String content = request.getParameter("content");
		
		GuestbookVo vo = new GuestbookVo();
		
		vo.setName(name);
		vo.setPassword(pass);
		vo.setContents(content);
		
		if(!(name.isEmpty()||pass.isEmpty()||content.isEmpty())){
			new GuestbookDao().insert(vo);
		}
		WebUtil.redirect(request.getContextPath()+"/guestbook", request, response);
	}

}
