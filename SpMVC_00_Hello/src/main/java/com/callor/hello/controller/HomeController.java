package com.callor.hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.callor.hello.models.StudentDto;
import com.callor.hello.service.HomeService;

// 여기는 Controller 클래스 이다
@Controller   // 컨트롤러가 붙으면 router가 된다 // 컨트롤러 역할제어
public class HomeController {
	
//	@Autowired  // home service를 쓰고 싶으니 만들어진 것들을 자동으로 연결하라
	// 생성자에서 초기화 할 필요가 없다
	/*
	* 컴포넌트 자동으로 주입받기
	* 컴포넌트 클래스를 사용하여 객체를 "선언 만" 하고
	* 생성자를 통하여 컴포넌트 객체를 주입받아 생성(초기화)한다
	* Spring Framework 에 의해 준비된 컴포넌트는 필요한 곳에서
	* 선언만 하면 자동으로 주입이 된다
	* DI(Dependency Inject, 의존성 주입)
	* 생성자를 통해서 주입받을 객체는 final 키워드를 부착하여 선언한다
	*
	* 제어의 역전
	* 일반적인 자바 코드에서 어떤 클래스를 사용하여 만든
	* 객체의 method나 속성(변수)에 접근하기 위해서는 반드시
	* 객체를 선언하고 생성자를 통하여 객체를 생성(초기화)해야 한다
	* 그렇지 않으면 NullpointerException 이 발생한다
	* Class class = new Class() 와 같은 코드를 사용하여 사용할 곳에서
	* 객체를 생성하여 준비한다.
	* 
	* Spring Project 에서는 그러한 절차가 없이 필요하다 라는 선언만 하면
	* Spring Container 에 미리 준비된 컴포넌트(객체, bean)를 주입해 준다
	* 객체를 생성하는 방향이 일반 자바와 반대라는 의미로
	* "제어의 역전(Inverted of Control)" 이라고 한다
	* 
	* Spring Project 에서는 new 키워드를 사용하는 경우가 많지 않다
	* 
	*/
	// homeservice 가 필요하니 준비를 해달라 final이 있어야 생성자를 통하여 주입
	private final HomeService homeService;  // private final
	public HomeController(HomeService homeService) {  // 생성자를 통하여 자동주입
		this.homeService = homeService;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)  // 주소 /를요청하면 get방식으로요청
	public String home(){
		
		
		homeService.hello();
		
		return "home"; // home.jsp 를 렌더링 해서 사용자에게 보여주어라
		// res.render("퍼그주소") 이런느낌
	}
	
	@RequestMapping(value="/student",method=RequestMethod.GET)
	public String student(Model model) { //모델에 담으면 디스패치가 꺼내서보여준다
		StudentDto stDto = homeService.getStudent();
		/*
		 * STD = stDto 형식으로 변수를 만들고
		 * 그 변수를 model 객체에 추가하라
		 * */
		model.addAttribute("STD",stDto);  // view에 데이터를 보낼때 model 을쓴다
		// student.jsp에서 꺼내쓸 수 있다.          //jsp는 컨트롤러에서 핸들링한다
		return "student";  
		
		// 이걸 router 식으로 쓴다면..
		//  res.render("student(pug)",{STD : stDto}) 이건듯?
	}
	
}
