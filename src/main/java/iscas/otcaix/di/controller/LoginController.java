package iscas.otcaix.di.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	
	@RequestMapping(value="/")
	public ModelAndView login(){
		return new ModelAndView("index"); 
	}

}
