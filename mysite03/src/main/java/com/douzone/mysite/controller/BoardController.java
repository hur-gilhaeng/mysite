package com.douzone.mysite.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.service.BoardService;
import com.douzone.mysite.vo.BoardVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
//	@RequestMapping("")
//	public String list(
//			@RequestParam( value="p", required=true, defaultValue="1") Integer page,
//			@RequestParam( value="kwd", required=true, defaultValue="") String keyword,
//			Model model){
//
//		Map<String, Object> map = boardService.getContentsList( page, keyword );
//		model.addAllAttributes(map);
//		
//		return "board/list";
//	}
	
	@RequestMapping(value ="", method = RequestMethod.POST)
	public String list( String keyword, Model model){
		
		List<BoardVo> list = boardService.findAll();
		model.addAttribute("boardlist", list);
		
		return "board/list";
	}
	
	@RequestMapping("/view/{no}")
	public String view(@PathVariable("no") Long no, Model model){
		BoardVo vo = boardService.findNo(no);
		model.addAttribute("vo", vo);
		return "board/view";
	}
	
	@RequestMapping(value ="/modify/{no}", method = RequestMethod.GET)
	public String modify(@PathVariable("no") Long no, Model model){
		BoardVo vo = boardService.findNo(no);
		vo.setNo(no);
		model.addAttribute("vo", vo);
		return "board/modify";
	}
	
	@RequestMapping(value ="/modify/{no}", method = RequestMethod.POST)
	public String modify(@PathVariable("no") Long no, BoardVo vo, Model model){
		
		// boardService.modify(vo);
		return "board/view";
	}
	
	@RequestMapping(value ="/write", method = RequestMethod.GET)
	public String write(){
		return "board/write";
	}
	
	@RequestMapping(value ="/write", method = RequestMethod.POST)
	public String write(BoardVo vo){
		// boardService.write(vo);
		return "redirect:/board";
	}
	
	
	@RequestMapping("/delete/{no}")
	public String delete(@PathVariable("no") Long no) {
		boardService.delete(no);
		return "redirect:/board";
	}
	
	
	
	
	

}
