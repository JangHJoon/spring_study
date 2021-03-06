package org.green.crud1;

import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


// bean : 스프링이 관리하는 자바 객체
//
// controller 클래스라는 것을 알려주는 어노테이션
// --> bean으로 등록 되었다.
@Controller
public class MyController {
	
	@RequestMapping("/dummy")
	public void doit() {
		// void인 경우 mapping한 이름과 같은 jsp파일을 찾는다.		
	}
	
	
	//{someone} -> @pathvariable -> somebody
	@RequestMapping("/batman/{someone}")
	public String todo(@PathVariable("someone") String somebody){
		// 인수의 이름과 @PathVariable의 키값이 같을 경우
		// 키값은 생략 가능
		// todo(@PathVariable String someone)
		
		return somebody;
		
	}

	
	@RequestMapping("/test")
	public String testObj(@ModelAttribute("some") Teacher t){
		// 그대로 객체에 teacher 키에 저장되서 전달된다.
		// 키값을 다르게 할려면 @ModelAttribute 어노테이션을 사용한다.
		return "test";
	}
	
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insert(
		@RequestParam(value="name", required=false, defaultValue="default") String name,
		@RequestParam(value="age", required=false, defaultValue="0") int age,
		@RequestParam("subject") String subject
		){
		// @requestParam 어노테이션
		
		// 파라미터 키 값과 인수 변수 이름이 같을 경우 value값 생략 가능
		// @requestParam 어노테이션을 아예 생략 가능(권장 x)
		// insert(String name, int age, String subject)
		
		// dto의 멤버변수 이름과 parameter named이 같을 경우 
		// insert(Teacher t)도 가능 <jsp:useBean> 이랑 비슷함

		// required가 false면 안넘어 오더라도 오류가 뜨지 않는다 대신 null값이 들어간다.
		// 안넘어올때 defaultValue 값으로 기본값 설정 가능
		// 자동 형 변환 request parameter 추출하기
		
//		String name = request.getParameter("name");
//		int age = Integer.parseInt(request.getParameter("age"));
//		String subject = request.getParameter("subject");
		Teacher t = new Teacher(name, age, subject);
		
		TeacherDAO dao = TeacherDAO.getInstance();
		Connection con = dao.connect();
		int resultRow = dao.insert(con, t);
		dao.close(con);
		
		
		return "redirect:/goList";
	}
	
	
	// 2개의 주소 처리
	// jsp 객체가 필요할 때 인수에 필요한 인수를 적기만 하면 됨
	@RequestMapping({"/", "/goList"})
	public ModelAndView goList(Model model){ // request 대신 model도 가능(스프링에서 제공하는 클래스)
		TeacherDAO dao = TeacherDAO.getInstance();
		Connection con = dao.connect();
		
		List<Teacher> list = dao.getAll(con);
		dao.close(con);		
		// 1. request에 넣어서 보내기
//		request.setAtribut("list", list);
		
		// 2. model에 넣어서 보내기
//		model.addAttribute("list", list);
		
		// 3. ModelAndView nextPage도 같이 넣어서 보내기
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.setViewName("list");
		return mav;		
//		return "list";
	}
	
	@RequestMapping("/goInsert")
	public String goInsert() {
		return "insert";
	}
}
