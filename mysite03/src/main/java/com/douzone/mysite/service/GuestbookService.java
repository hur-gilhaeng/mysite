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
		return guestbookRepository.insert(vo);
	}

	public Boolean delete(Long no, String password) {
		if(guestbookRepository.ckPassword(no, password)) {
			return guestbookRepository.delete(no);
		}
		else {
			return false;
		}
	}

}
