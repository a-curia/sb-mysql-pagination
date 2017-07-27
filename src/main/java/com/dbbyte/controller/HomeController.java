package com.dbbyte.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	
	@RequestMapping("/")
	public ModelAndView index(Model model) {	
		
		model.addAttribute("pageTitle", "BooksApp");	
		model.addAttribute("pageHello", "Welcome to BooksApp");

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("pages/index");
		
		return modelAndView;
	}
	
	@RequestMapping("/about")
	public ModelAndView about(Model model) {	
		
		model.addAttribute("pageTitle", "BooksApp");
		model.addAttribute("pageHello", "About Us");

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("pages/about");
		
		return modelAndView;
	}	
	
	@RequestMapping("/services")
	public ModelAndView services(Model model) {	
		
		model.addAttribute("pageTitle", "BooksApp");
		model.addAttribute("pageHello", "Our Services");

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("pages/services");
		
		return modelAndView;
	}		
	
	@RequestMapping("/contact")
	public ModelAndView contact(Model model) {	
		
		model.addAttribute("pageTitle", "BooksApp");
		model.addAttribute("pageHello", "contact Us");

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("pages/contact");
		
		return modelAndView;
	}		
	
}