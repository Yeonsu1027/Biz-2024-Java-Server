package com.callor.hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.callor.hello.model.StudentDto;
import com.callor.hello.service.StudentService;

@Controller
public class HomeController {
	/*
	 * Spring Projext 에서 가장 많이 보이는 에러 메시지
	 * No qualifying bean of type 'com.callor.hello.service.StudentService'
	 * HomeController 에서 StudentService 클래스로 만들어진 bean(객체, 컴포넌트)를
	 * 사용하겠다고 Spring 에게 달라 라고 요청을 했다
	 * 
	 * 그런데 어떤 이유로 StudentService 클래스의 bean 이 생성이 안되었을때
	 * 보여지는 에러 메시지다                  //대체로 service에서 @서비스 어노테이션 안붙여서그럼
	 * 
	 * 이 메시지의 원인은 bean 을 생성할 클래스에 @Annotation 이 누락된 경우다 ▲
	 * 
	 * */

	// homeservice 가 필요하니 준비를 해달라 final이 있어야 생성자를 통하여 주입
	private final StudentService studentService; // private final

	// 생성자
	public HomeController(StudentService studentService) { // 생성자를 통하여 자동주입
		this.studentService = studentService;
		// 맴버변수 = 주입받은 studentService  ***초기화코드
	}

	// 주소 /를요청하면 get방식으로요청
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) { //모델에 담으면 디스패치가 꺼내서보여준다
		StudentDto stDto = studentService.getStudent(); // studentservice에서 만든 getstudent 호출

		model.addAttribute("STD", stDto);

		return "home";
		// home.jsp 를 렌더링 해서 사용자에게 보여주어라
				// res.render("퍼그주소") 이런느낌
		// 이걸 router 식으로 쓴다면..
				//  res.render("student(pug)",{STD : stDto})
	}

}
