package hh.bookstore05.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.bookstore05.domain.Book;
import hh.bookstore05.domain.BookRepository;
import hh.bookstore05.domain.CategoryRepository;
import jakarta.validation.Valid;

@Controller
public class BookController {
	
	@Autowired
	private BookRepository repository;
	
	@Autowired
	private CategoryRepository crepository;
	
	//Main/index
	@RequestMapping(value= {"/", "/main" , "/index"}) //Eendpoint: http://localhost:8080 , http://localhost:8080/index ,http://localhost:8080/main
	public String showMainPage() {
		return "index";  //index.html
	}
	
	//Kaikki kirjat	
	@RequestMapping(value = {"/booklist"}) //endpoint:  http://localhost:8080/booklist
	public String bookList(Model model) {
		
		model.addAttribute("books", repository.findAll());
		
		return "booklist.html";	
	}
	

	//Lisätään kirja
	@PreAuthorize("hasAuthority('ADMIN')") //metoditason tarkistus onko oikeus lisätä
	@RequestMapping(value="/add")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categories", crepository.findAll());
		return "addbook";
	}
	
	//Tallennetaan uusi kirja
	@PreAuthorize("hasAuthority('ADMIN')") //metoditason tarkistus onko oikeus 
	@RequestMapping(value="/save", method = RequestMethod.POST) //tämä käytössä addbook.html :ssä
	public String save(@Valid Book book, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			System.out.println("Some validation error happened");
			model.addAttribute("book", book);
			model.addAttribute("categories", crepository.findAll()); // <---- tämän takia edellinen kaatui, tärkeä
			return "addbook";
		}
		repository.save(book);
		System.out.println("LISÄTTY: " + book + book.getCategory());
		return "redirect:/booklist";
	}

	//Poista kirja
	@PreAuthorize("hasAuthority('ADMIN')") //metoditason tarkistus onko oikeus poistaa 
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long bookId, Model model) {
		repository.deleteById(bookId);
		System.out.println("POISTETAAN KIRJA, id: " + bookId);
		return "redirect:../booklist"; 
	}
	

	//Muokkaa
	@PreAuthorize("hasAuthority('ADMIN')") //metoditason tarkistus, onko oikeus muokata
	@RequestMapping(value= "/editBook/{id}", method = RequestMethod.GET)
	public String editBook(@PathVariable("id") Long id, Model model) {
		model.addAttribute("book", repository.findById(id)); //bookID
		model.addAttribute("categories", crepository.findAll());
		return "editBook";
	}
/*
	@PostMapping("saveBook") //tämä käytössä editbook.html:ssä
	public String saveBook(Book book) {
		repository.save(book);
		return "redirect:/booklist";
	}*/
}
