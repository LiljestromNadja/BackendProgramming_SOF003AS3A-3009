package hh.bookstore02.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.validation.BindingResult;
import jakarta.validation.Valid;

import hh.bookstore02.domain.Book;
import hh.bookstore02.domain.BookRepository;
import hh.bookstore02.domain.CategoryRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;

@Controller
public class BookController {	
	
	private static final Logger log = LoggerFactory.getLogger(BookController.class);
	
	@Autowired
	BookRepository brepository;
	
	@Autowired
	private CategoryRepository crepository;
	
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
		model.addAttribute("categories", crepository.findAll());
		log.info("Kirjan lisääminen");
		return "addbook";
		
	}
	
	//Tallennetaan uusi kirja
	@RequestMapping(value="/save", method = RequestMethod.POST) //tämä käytössä addbook.html :ssä
	public String save(@Valid Book book, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			System.out.println("Some validation error happened");
			model.addAttribute("book", book);
			model.addAttribute("categories", crepository.findAll()); // <---- tämän takia edellinen kaatui, tärkeä
			return "addbook";
		}
		brepository.save(book);
		log.info("Kirja lisätty");
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
		model.addAttribute("categories", crepository.findAll());
		log.info("Kirjan muokkaus");
		return "editBook";
	}

	@PostMapping("saveBook") //tämä käytössä editbook.html:ssä
	public String saveBook(Book book) {
		brepository.save(book);		
		log.info("Kirjaa muokattu");
		return "redirect:/booklist";
	}
}
