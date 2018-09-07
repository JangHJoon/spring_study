package org.green.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	/*
	 * spring�� �����ϴ� bean�߿���
	 * type�� Ball�� �༮�� �˾Ƽ� ������ ��,
	 * �������ش�.
	 * 
	 * spring�� �����ϴ� bean�� ��� singleton ������ ���Ѵ�.
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





