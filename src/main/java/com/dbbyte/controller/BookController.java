package com.dbbyte.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dbbyte.domain.Book;
import com.dbbyte.domain.BookForm;
import com.dbbyte.repository.BookRepository;

@Controller
public class BookController {
	
	@Autowired
	private BookRepository 	bookRepository; 	
	
	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public ModelAndView showAllBooks(@PageableDefault(value=10, page=0) Pageable pageable, Model model) {
		
		Page<Book> page = bookRepository.findAll(pageable);
		
		model.addAttribute("pageTitle", "BookSample - show all books");	
		model.addAttribute("books", page.getContent());
	
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("pages/books");
		
		return modelAndView;
	}
	
    @RequestMapping(value="/books", method=RequestMethod.POST)
    public String customerSubmit(@ModelAttribute BookForm bookForm, Model model) {
         
        model.addAttribute("customer", bookForm);
        String info = String.format("Customer Submission:  title = %s, author = %s", 
        		 bookForm.getTitle(), bookForm.getAuthor());
        System.out.println(info);
         
        return "redirect:/books?title="+bookForm.getTitle()+"&author="+bookForm.getAuthor();
    }
	
	
	@RequestMapping(value = "/books", params="title", method = RequestMethod.GET)
	public ModelAndView showAllBooksLikeTitle(@RequestParam(value="title", required=true) String title, Model model) {
		
		model.addAttribute("pageTitle", "BookSample - show all books with title like");	
		model.addAttribute("books", bookRepository.findByTitleContaining(title));
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("pages/books");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/books", params={"title","author"}, method = RequestMethod.GET)
	public ModelAndView showAllBooksByTitleAndAuthor(@RequestParam(value="title", required=true) String title, @RequestParam(value="author") String author, Model model) {
		
		
		model.addAttribute("pageTitle", "BookSample - show all books with exact title and author");	
		
		if (author.equals("ALL")) {
			model.addAttribute("books", bookRepository.findByTitle(title));
		} else {
			model.addAttribute("books", bookRepository.findByTitleAndAuthor(title,author));
		}
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("pages/books");
		
		return modelAndView;
	}	
	
}