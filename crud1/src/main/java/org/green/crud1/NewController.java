package org.green.crud1;

import java.sql.Connection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NewController {
	
	@Autowired
	private newTeacherDao dao;
	
	@RequestMapping("/dummy")
	public void doit() {
		
	}
	@RequestMapping("/batman/{someone}")
	public String todo(@PathVariable String someone) {
		System.out.println(someone);
		return someone;
	}
	@RequestMapping("/test")
	public String testObj(@ModelAttribute("some") Teacher t) {
		return "test";
	}
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insert(@ModelAttribute Teacher t) {
		dao.insert(t);
		return "redirect:/goList";
	}
	
	
	@RequestMapping({"/", "/goList"})
	public ModelAndView goList(Model model) {		
		List<Teacher> list = dao.getAll();
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.setViewName("list");
		return mav;
	}
	
	@RequestMapping("/goInsert")
	public String goInsert() {
		return "insert";
	}
}
