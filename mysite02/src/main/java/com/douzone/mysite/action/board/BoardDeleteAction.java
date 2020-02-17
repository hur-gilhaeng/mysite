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

public class BoardDeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		if(session.getAttribute("authUser")!=null) {
			// 삭제 작업 전 사용자 확인...
			Long auno = ((UserVo)session.getAttribute("authUser")).getNo();
			String unoSet = request.getParameter("uno");
			Long uno = Long.parseLong(unoSet);
			BoardRepository br = new BoardRepository();

			if(auno == uno) { // 확인 완료시 삭제 작업 진행
				String no = request.getParameter("no");
				Long getNo = Long.parseLong(no);
				BoardVo vo = new BoardVo();
				vo = br.findNoTogNo(getNo);
				int ddepth = br.findDdepth(vo);
				
				if(vo.getDepth()<ddepth){
					br.boardDel(getNo);
				}else {
					br.boardInvis(getNo);
					br.updateTooNoUp(vo);
				}
			}
		}

		WebUtil.redirect(request.getContextPath()+"/board", request, response);
	}

}
