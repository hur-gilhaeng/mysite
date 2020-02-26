package com.douzone.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.service.GuestbookService;
import com.douzone.mysite.vo.GuestbookVo;


@Controller
@RequestMapping("/guestbook")
public class GuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;
	
	@RequestMapping("")
	public String list(Model model) {
		List<GuestbookVo> list = guestbookService.findAll();
		model.addAttribute("list", list);
		return "guestbook/list";
	}
	
	@RequestMapping("/insert")
	public String insert(GuestbookVo vo) {
		guestbookService.insert(vo);
		return "redirect:/guestbook";
	}
	
	@RequestMapping(value ="/delete/{no}", method = RequestMethod.GET)
	public String delete(@PathVariable("no") Long no, Model model) {
		model.addAttribute("no",no);
		return "guestbook/delete";
	}
	// 위의 코드는 아래와 같이 표현 할 수 있으나 권장하지 않는다!!
	//	@RequestMapping(value ="/delete/{no}", method = RequestMethod.GET)
	//	public String delete(@ModelAttribute @PathVariable("no") Long no) {
	//		return "guestbook/delete";
	//	}
	
	@RequestMapping(value ="/delete/{no}", method = RequestMethod.POST)
	public String delete(@PathVariable("no") Long no, String password) {
		if(guestbookService.delete(no, password))
			return "redirect:/guestbook";
		else
			return "redirect:/guestbook/delete/"+no;
	}

}
