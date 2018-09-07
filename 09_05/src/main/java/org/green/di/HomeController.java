package org.green.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	/*
	 * spring이 관리하는 bean중에서
	 * type이 Ball인 녀석을 알아서 생성한 후,
	 * 연결해준다.
	 * 
	 * spring이 관리하는 bean은 모두 singleton 형식을 취한다.
	 * (state-less)
	 */
	@Autowired
	private Ball myBall;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		
		myBall.setNum(3);
		
		model.addAttribute("myBall", myBall);
		
		return "home";
	}
	
}





