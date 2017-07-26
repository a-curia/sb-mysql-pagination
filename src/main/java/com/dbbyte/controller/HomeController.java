package com.dbbyte.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/show")
public class HomeController {
	
	@RequestMapping("")
	public ModelAndView index(Model model) {	
		
		model.addAttribute("pageTitle", "BookSample");	

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		
		return modelAndView;
	}
	
}