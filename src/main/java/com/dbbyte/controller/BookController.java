package com.dbbyte.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dbbyte.domain.Book;
import com.dbbyte.repository.BookRepository;

@Controller
@RequestMapping(value="/show")
public class BookController {
	
	@Autowired
	private BookRepository 	bookRepository; 	
	
	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public ModelAndView showAllBooks(@PageableDefault(value=10, page=0) Pageable pageable, Model model) {
		
		Page<Book> page = bookRepository.findAll(pageable);
		
		model.addAttribute("pageTitle", "BookSample - show all books");	
		model.addAttribute("books", page.getContent());
	
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("result");
		
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/books", params="title", method = RequestMethod.GET)
	public ModelAndView showAllBooksLikeTitle(@RequestParam(value="title", required=true) String title, Model model) {
		
		model.addAttribute("pageTitle", "BookSample - show all books with title like");	
		model.addAttribute("books", bookRepository.findByTitleContaining(title));
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("result");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/books", params={"title","author"}, method = RequestMethod.GET)
	public ModelAndView showAllBooksByTitleAndAuthor(@RequestParam(value="title", required=true) String title, @RequestParam(value="author", required=true) String author, Model model) {
		
		model.addAttribute("pageTitle", "BookSample - show all books with exact title and author");	
		model.addAttribute("books", bookRepository.findByTitleAndAuthor(title,author));
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("result");
		
		return modelAndView;
	}	
	
}