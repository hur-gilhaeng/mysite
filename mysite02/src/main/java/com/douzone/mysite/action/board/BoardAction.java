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
			boardlist = br.findPage(page-1);
		}
		
		//boardlist = br.findAll();

		request.setAttribute("boardlist", boardlist);
		request.setAttribute("maxPage", maxPage);
		if(page >  maxPage) page = 1;
		request.setAttribute("page", page);
		request.setAttribute("pagelist",pageList(page));
		
		WebUtil.forward("/WEB-INF/views/board/list.jsp", request, response);
	}
	
	private int[] pageList(int page) {
		int[] list = new int[PAGESIZE];
		
		if(page<3) {
			for(int i=0;i<PAGESIZE;i++) {
				list[i]=i;
			}
		}
		else {
			list[0]=page-2;
			list[1]=page-1;
			list[2]=page;
			list[3]=page+1;
			list[4]=page+2;
		}
		
		return list;
	}
}
