package hh.bookstore01.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hh.bookstore01.domain.Book;
import hh.bookstore01.domain.BookRepository;
import org.springframework.ui.Model;

@Controller
public class BookController {	
	
	private static final Logger log = LoggerFactory.getLogger(BookController.class);
	
	@Autowired
	BookRepository brepository;
	
	@RequestMapping("/index") //http://localhost:8080/index
	public String showMainPage() {
		return "index.html";  //index.html
	}
		
	@RequestMapping("/joku") //http://localhost:8080/joku
	public String showJokuPage() {
		return "joku.html";  //joku.html
	}
	
	//listaus 
	//@GetMapping(value = {"/","/booklist"}) //endpoint: http://localhost:8080/ ja  http://localhost:8080/booklist
	@GetMapping("/booklist") //http://localhost:8080/booklist
	public String bookList(Model model) {
		
		model.addAttribute("books", brepository.findAll());
		log.info("Kirjojen listaus");
		
		return "booklist.html";	
	}
	
	//lisääminen
	@RequestMapping(value="/add")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		return "addbook";
	}
	
	//@PostMapping("/save") // <-Lyhyempi tapa 
	@RequestMapping(value="/save", method = RequestMethod.POST) //tämä käytössä addbook.html :ssä
	public String save(Book book) {
		brepository.save(book);
		return "redirect:/booklist";
	}

	
	//poistaminen
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long bookId, Model model) {
		brepository.deleteById(bookId);
		return "redirect:../booklist";
	}
	
	
	//muokkaaminen
	@GetMapping("editBook/{id}")
	public String editBook(@PathVariable("id") Long id, Model model) {
		model.addAttribute("editBook", brepository.findById(id));
		return "editBook";
	}

	@PostMapping("saveBook") //tämä käytössä editbook.html:ssä
	public String saveBook(Book book) {
		brepository.save(book);
		return "redirect:/booklist";
	}
}
