package com.douzone.mysite.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;

import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class BoardAction implements Action {
	final static private int PAGESIZE = 5;

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String getPage = request.getParameter("page");
		BoardRepository br = new BoardRepository();
		
		int maxPage = (br.ckPage()/PAGESIZE);
		if((br.ckPage()%PAGESIZE)!=0) maxPage+=1;
		
		int page = 1;
		
		List<BoardVo> boardlist;
		if(getPage==null||"".equals(getPage)) {
			boardlist = br.findPage(page-1);
		}
		
		else {
			page = Integer.parseInt(getPage);
			if(page >  maxPage || page < 1) {
				WebUtil.redirect(request.getContextPath()+"/board", request, response);
				return;
			}
			boardlist = br.findPage(page-1);
		}
		//boardlist = br.findAll();

		request.setAttribute("boardlist", boardlist);
		request.setAttribute("maxPage", maxPage);

		request.setAttribute("page", page);
		int pp = (page-1)/5;
		request.setAttribute("pp", pp);
		request.setAttribute("pagelist",pageList(page));
		
		WebUtil.forward("/WEB-INF/views/board/list.jsp", request, response);
	}
	
	private int[] pageList(int page) {
		int[] list = new int[PAGESIZE];

		int pagingset = (page-1)/5;

		list[0]=(pagingset*5)+1;
		list[1]=(pagingset*5)+2;
		list[2]=(pagingset*5)+3;
		list[3]=(pagingset*5)+4;
		list[4]=(pagingset*5)+5;

		return list;
	}
}
