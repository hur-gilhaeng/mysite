package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.GuestbookRepository;
import com.douzone.mysite.vo.GuestbookVo;

@Service
public class GuestbookService {

	@Autowired
	private GuestbookRepository guestbookRepository;
	
	public List<GuestbookVo> findAll() {
		return guestbookRepository.findAll();
	}

	public Boolean insert(GuestbookVo vo) {
		return 1==guestbookRepository.insert(vo);
	}

	public Boolean delete(Long no, String password) {
		GuestbookVo vo = new GuestbookVo();
		vo.setNo(no);
		vo.setPassword(password);
		
		return 1==guestbookRepository.delete(vo);
	}

	public List<GuestbookVo> getMessageList(Long startNo) {
		return guestbookRepository.findAll(startNo);
	}

	public boolean writeMessage( GuestbookVo vo ) {
		int count = guestbookRepository.insert(vo);
		return count == 1;
	}

	public boolean deleteMessage(Long no, String password) {
		GuestbookVo vo = new GuestbookVo();
		vo.setNo(no);
		vo.setPassword(password);
		
		return 1==guestbookRepository.delete(vo);
	}
}
