package hh.bookstore14.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.bookstore14.domain.ApplicationUserRepository;
import hh.bookstore14.domain.Book;
import hh.bookstore14.domain.BookRepository;
import hh.bookstore14.domain.Comment;
import hh.bookstore14.domain.CommentRepository;
import jakarta.validation.Valid;

@Controller
public class CommentController {
	
	@Autowired 
	private CommentRepository commentrepository;
	
	@Autowired
	private BookRepository bookrepository;
	
	@Autowired
	private ApplicationUserRepository applicationuserrepository;
	
	
	//Listataan KAIKKI kommentit KAIKKIIN kirjoihin
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')") //metoditason tarkistus onko oikeus poistaa
	@RequestMapping(value = {"/allComments"}) //endpoint:  http://localhost:8080/listComments
	public String listComments(Model model, @AuthenticationPrincipal UserDetails userDetails) {
		System.out.println("--- CommentController.java ---NÄYTETÄÄN KAIKKI KOMMENTIT");

		model.addAttribute("comments", commentrepository.findAll());
		
		//lisätty 24102023 kun halutaan rajata näkyvyyttä niin että käyttäjä näkee vain itse lisäämiensä kirjojen edit ja delete-napin
		//jos tätä muutetaan, niin täytyy muuttaa myös booklist.html 
		//lisääminen ja poistaminen vaativat USER tai ADMIN-roolin
		//model.addAttribute("applicationuser", applicationuserrepository.findByUsername(userDetails.getUsername())); //lisätty 25102023
		
		return "allComments.html";	
	}

	/*
	//Kommenttien listaus (TEE: listaa kirjan id:n perusteella)
	@RequestMapping(value = {"listComments/{bookId}"}) //endpoint:  http://localhost:8080/listComments
	public String listCommentsByBookId(@PathVariable("id") Long bookId, Model model, @AuthenticationPrincipal UserDetails userDetails) {
		System.out.println("--- CommentController.java ---NÄYTETÄÄN YHDEN KIRJAN KOMMENTIT");
		model.addAttribute("comments", commentrepository.findByBookId(bookId));
		
		//lisätty 24102023 kun halutaan rajata näkyvyyttä niin että käyttäjä näkee vain itse lisäämiensä kirjojen edit ja delete-napin
		//jos tätä muutetaan, niin täytyy muuttaa myös booklist.html 
		//lisääminen ja poistaminen vaativat USER tai ADMIN-roolin
		//model.addAttribute("applicationuser", applicationuserrepository.findByUsername(userDetails.getUsername())); //lisätty 25102023
		
		return "listComments.html";	
	}
*/
	
	
	//Listaa YHDEN kirjan kommentit kirjan id:n perusteella
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')") //metoditason tarkistus onko oikeus poistaa
	@RequestMapping(value="/book/{id}/comments", method=RequestMethod.GET)
	public String showCommentsForBook(@PathVariable("id") Long bookId, Model model) {
		
		//Hae kommentit kirjan id:n perusteella
		List<Comment> bookComments = commentrepository.findByBookId(bookId);
		
		System.out.println("NÄYTETÄÄN KAIKKI KOMMENTIT KIRJALLE JONKA ID: " + bookId + "---CommentContoller");
		//System.out.println(bookComments);
		
		model.addAttribute("comments", bookComments);
		
		
		return "bookComments";
	}
	
	

	//Lisätään kommentti
	//Tänne ohjataan booklist -sivulta
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')") //metoditason tarkistus onko oikeus lisätä
	@RequestMapping(value="addComment/{id}")
	public String addComment(@PathVariable("id") Long id, Model model, @AuthenticationPrincipal UserDetails userDetails) {
	
		Comment comment = new Comment();
		
		comment.setBook(bookrepository.findById(id).get());
		
		System.out.println("");
		System.out.println("KIRJA JOTA KOMMENTOIDAAN" + comment.getBook() + "--- CommentController, addComment");
		System.out.println("KOMMENTTI: " + comment.getCommentid());

		model.addAttribute("comment", comment);
		
		System.out.println("KOMMENTTI, bookID: " + bookrepository.findById(id).get());
		model.addAttribute("applicationuser", applicationuserrepository.findByUsername(userDetails.getUsername())); //lisätty 22102023
		
		return "addComment";
	}
	
	//Tallennetaan kommentti
	//Tänne ohjataan tallennus seuraavista endpointeista: 
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')") //metoditason tarkistus onko oikeus 
	@RequestMapping(value="/saveComment", method = RequestMethod.POST) //tämä käytössä addbook.html :ssä
	public String save(@Valid Comment comment,  Book book, BindingResult bindingResult, Model model, @AuthenticationPrincipal UserDetails userDetails) {
		if (bindingResult.hasErrors()) {
			System.out.println("Some validation error happened");
			model.addAttribute("comment", comment);
			model.addAttribute("book", bookrepository.findById(comment.getBook().getId())); // <---- tämän takia edellinen kaatui, tärkeä
			model.addAttribute("applicationuser", applicationuserrepository.findByUsername(userDetails.getUsername())); //päivitetty 22102023, käytetään addBook.html:ssä
			return "addComment";
		}
		
		
		commentrepository.save(comment);
		
		System.out.println("LISÄTTY KOMMENTTI: " + comment + comment.getBook());
		System.out.println("Lisääjän käyttäjätunnus: " + comment.getApplicationuser().getUsername());
		return "redirect:/allComments";
	}



	//31-10-2023
	//Lisätään kommentti, esimerkkipolku http://localhost:8080/book/3/addComment/
	//tänne ohjataan YKSITTÄISEN kirjan kommenttien listaussivulta
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')") //metoditason tarkistus onko oikeus lisätä
	@RequestMapping(value="/book/{id}/addComment/")
	public String addCommentByBookId(@PathVariable("id") Long id, Model model, @AuthenticationPrincipal UserDetails userDetails) {
	
		Comment comment = new Comment();
		
		comment.setBook(bookrepository.findById(id).get());
		
		System.out.println("KIRJA JOTA KOMMENTOIDAAN" + comment.getBook() + "--- CommentController, addComment");
		System.out.println("KOMMENTTI: " + comment.getCommentid());

		model.addAttribute("comment", comment);
		
		System.out.println("KOMMENTTI, bookID: " + bookrepository.findById(id).get());
		model.addAttribute("applicationuser", applicationuserrepository.findByUsername(userDetails.getUsername())); //lisätty 22102023
		
		return "addComment"; 
	}
	
	//31-10-2023
	//Lisätään kommentti, esimerkkipolku http://localhost:8080/chooseBookToComment/
	//tänne ohjataan KAIKKIEN kommentien listaussivulta
	//tässä endpointissa käyttäjä valitsee listasta, mitä kirjaa kommentoi
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')") //metoditason tarkistus onko oikeus lisätä
	@RequestMapping(value="chooseBookToComment/")
	public String chooseBookToComment(Model model, @AuthenticationPrincipal UserDetails userDetails) {
	
		model.addAttribute("comment", new Comment());
		model.addAttribute("books", bookrepository.findAll());
		model.addAttribute("applicationuser", applicationuserrepository.findByUsername(userDetails.getUsername())); //lisätty 31-10-2023
		
		model.addAttribute("KÄYTTÄJÄ ", applicationuserrepository.findByUsername(userDetails.getUsername()) + "LISÄSI KOMMENTIN"); //lisätty 31-10-2023
		System.out.println("Endpoint: chooseBookToComment");
		
		return "chooseAndAddComment"; //käytetään eri lomaketta, kuin yhden kirjan kommentoinneissa
	}
	
	

	//Kommenttien hallinnointi, edit ja delete 
	
	//31-10-2023
	//Poista kirja
	@PreAuthorize("hasAuthority('ADMIN')") //metoditason tarkistus onko oikeus poistaa 
	@RequestMapping(value="/deleteComment/{id}", method=RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long commentId, Model model) {
		commentrepository.deleteById(commentId);
		System.out.println("POISTETAAN KOMMENTTI, id: " + commentId);
		return "redirect:../allComments"; 
	}
	
	
	
	
	
	
	
	

}
