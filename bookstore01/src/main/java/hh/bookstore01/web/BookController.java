package hh.bookstore01.web;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class BookController {	
	
	@RequestMapping("/index") //http://localhost:8080/index
	public String showMainPage() {
		return "index.html";  //index.html
	}

}
