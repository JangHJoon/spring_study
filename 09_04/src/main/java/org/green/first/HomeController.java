package org.green.first;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	
	@RequestMapping(value="/test")
	public String myMethod() {
		return "other";
	}
	
	
	
	
//	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	// 스프링이 제공하는 어노테이션
	// request 위치가 루트(/)이고 method가 GET방식일 때
	// method 설정이 없으면 POST,GET 모두 받음
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(){
//	public String home(Locale locale, Model model) {
		
		
		
		
		
		
		
//		logger.info("Welcome home! The client locale is {}.", locale);
//		
//		Date date = new Date();
//		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
//		
//		String formattedDate = dateFormat.format(date);
//		
//		model.addAttribute("serverTime", formattedDate );
		
		
//		return "other";
		return "home";
	}
	
}
