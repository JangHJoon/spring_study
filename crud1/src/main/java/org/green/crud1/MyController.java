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


// bean : �������� �����ϴ� �ڹ� ��ü
//
// controller Ŭ������� ���� �˷��ִ� ������̼�
// --> bean���� ��� �Ǿ���.
@Controller
public class MyController {
	
	@RequestMapping("/dummy")
	public void doit() {
		// void�� ��� mapping�� �̸��� ���� jsp������ ã�´�.		
	}
	
	
	//{someone} -> @pathvariable -> somebody
	@RequestMapping("/batman/{someone}")
	public String todo(@PathVariable("someone") String somebody){
		// �μ��� �̸��� @PathVariable�� Ű���� ���� ���
		// Ű���� ���� ����
		// todo(@PathVariable String someone)
		
		return somebody;
		
	}

	
	@RequestMapping("/test")
	public String testObj(@ModelAttribute("some") Teacher t){
		// �״�� ��ü�� teacher Ű�� ����Ǽ� ���޵ȴ�.
		// Ű���� �ٸ��� �ҷ��� @ModelAttribute ������̼��� ����Ѵ�.
		return "test";
	}
	
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insert(
		@RequestParam(value="name", required=false, defaultValue="default") String name,
		@RequestParam(value="age", required=false, defaultValue="0") int age,
		@RequestParam("subject") String subject
		){
		// @requestParam ������̼�
		
		// �Ķ���� Ű ���� �μ� ���� �̸��� ���� ��� value�� ���� ����
		// @requestParam ������̼��� �ƿ� ���� ����(���� x)
		// insert(String name, int age, String subject)
		
		// dto�� ������� �̸��� parameter named�� ���� ��� 
		// insert(Teacher t)�� ���� <jsp:useBean> �̶� �����

		// required�� false�� �ȳѾ� ������ ������ ���� �ʴ´� ��� null���� ����.
		// �ȳѾ�ö� defaultValue ������ �⺻�� ���� ����
		// �ڵ� �� ��ȯ request parameter �����ϱ�
		
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
	
	
	// 2���� �ּ� ó��
	// jsp ��ü�� �ʿ��� �� �μ��� �ʿ��� �μ��� ���⸸ �ϸ� ��
	@RequestMapping({"/", "/goList"})
	public ModelAndView goList(Model model){ // request ��� model�� ����(���������� �����ϴ� Ŭ����)
		TeacherDAO dao = TeacherDAO.getInstance();
		Connection con = dao.connect();
		
		List<Teacher> list = dao.getAll(con);
		dao.close(con);		
		// 1. request�� �־ ������
//		request.setAtribut("list", list);
		
		// 2. model�� �־ ������
//		model.addAttribute("list", list);
		
		// 3. ModelAndView nextPage�� ���� �־ ������
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