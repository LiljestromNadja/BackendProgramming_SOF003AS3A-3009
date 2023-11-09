package hh.bookstore17.web;

/***
 * Muista apien salliminen/kieltäminen/rajoittaminen 
 * WebSecurityConfig.java
 * 
 * **/

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hh.bookstore17.domain.Answer;
import hh.bookstore17.domain.AnswerRepository;
import hh.bookstore17.domain.Book;
import hh.bookstore17.domain.BookRepository;
import hh.bookstore17.domain.Comment;
import hh.bookstore17.domain.CommentRepository;

@RestController //palauttaa JSONia
public class RestBookStoreController {
	
	private static final Logger log = LoggerFactory.getLogger(RestBookStoreController.class);
	
	//injektoidaan Bookrepository RestBookStoreController-luokalle
	@Autowired
	BookRepository bookRepository;
	
	// Injektoidaan CommentRepostitory RestBookStoreController -luokalle
	@Autowired
	CommentRepository commentRepository;
	
	@Autowired
	AnswerRepository answerRepository;
	
	//palautetaan lista kirjoista http://localhost:8080/booksjson
	@GetMapping("/booksjson")
	public Iterable<Book> getBooks() {
		log.info("nouda ja palauta lista kirjoista");
		return bookRepository.findAll();
	}
	
	//lisätään uusi kirja 
	@PostMapping("booksjson")
	Book newBook(@RequestBody Book newBook) {
		log.info("Lisätäään kirja " + newBook);
		return bookRepository.save(newBook);
	}
	
	// muokataan olemassa olevaa kirjaa
		@PutMapping("/booksjson/{id}")
		Book editBook(@RequestBody Book editedBook, @PathVariable Long id) {
			log.info("Muokataan kirjaa " + editedBook);
			editedBook.setId(id);
			return bookRepository.save(editedBook);
		}
	
	/*//delete book
	@DeleteMapping("/booksjson/{id}")
	void deleteBook(@PathVariable Long id) {
		bookRepository.deleteById(id);
	}*/
	
	//poistetaan kirja
	@DeleteMapping("/booksjson/{id}")
	public Iterable<Book> deleteBook(@PathVariable Long id) {
		log.info("Poistetaan kirja, id: " + id);
		bookRepository.deleteById(id);
		return bookRepository.findAll();
	}
	
	//etsitään ja palautetaan yksi kirja
	@GetMapping("/booksjson/{id}")
	Optional<Book> getBook(@PathVariable Long id) {
		log.info("Etsitään kirja, id: " + id);
		return bookRepository.findById(id);
		
	}
	
	//COMMENTS
	
	
	//palautetaan lista kommenteista GET http://localhost:8080/commentsjson
	@GetMapping("/commentsjson")
	public Iterable<Comment> getComments() {
		System.out.println("---KAIKKI KOMMENTIT --- RestBookStoreController");
		return commentRepository.findAll();
	}
	
	
	// Palautetaan yksi kommentti id:n perusteella GET http://localhost:8080/commentsjson/1
	@GetMapping("/commentsjson/{id}")
	Optional<Comment> getComment(@PathVariable Long id) {
		System.out.println("---PALAUTETAAN KOMMENTTI, JONKA ID: " + id + " --- RestBookStoreController");
		return commentRepository.findById(id);
		
	}
	
	// Lisätään uusi kommentti POST http://localhost:8080/commentsjson
	
	@PostMapping("/commentsjson")
	Comment newComment(@RequestBody Comment newComment) {
		System.out.println("---LISÄTÄÄN KOMMENTTI " + newComment + " --- RestBookStoreController");
		return commentRepository.save(newComment);
	}
	
	// Muokataan kommenttia id:n perusteella PUT http://localhost:8080/commentsjson/1
	@PutMapping("/commentsjson/{id}")
	Comment editComment(@RequestBody Comment editedComment, @PathVariable Long id) {
		System.out.println("---MUOKATAAN KOMMENTTIA, JONKA ID: " + id + " --- RestBookStoreController");
		editedComment.setCommentid(id);
		return commentRepository.save(editedComment);
	}
	
	
	// Poistetaan kommentti DELETE http://localhost:8080/commentsjson/2
	@DeleteMapping("/commentsjson/{id}")
	public Iterable<Comment> deleteComment(@PathVariable Long id) {
		
		System.out.println("---POISTETAAN KOMMENTTI, JONKA ID: " + id + " --- RestBookStoreController");
		commentRepository.deleteById(id);
		return commentRepository.findAll();
	}
	
	
	
	//Answers
	
	//palautetaan lista vastauksista GET http://localhost:8080/answersjson
	@GetMapping("/answersjson")
	public Iterable<Answer> getAnswers() {
		System.out.println("---KAIKKI VASTAUKSET --- RestBookStoreController");
		return answerRepository.findAll();
	}
	
	//etsitään ja palautetaan yksi vastaus id:n perusteella
	@GetMapping("/answersjson/{id}")
	Optional<Answer> getAnswer(@PathVariable Long id) {
		System.out.println();
		log.info("---YKSI VASTAUKSES --- RestBookStoreController, id: " + id);
		return answerRepository.findById(id);
		
	}
	
	
	// Lisätään uusi vastaus POST http://localhost:8080/answersjson
	
	@PostMapping("/answersjson")
	Answer newAnswer(@RequestBody Answer newAnswer) {
		System.out.println("---LISÄTÄÄN VASTAUS KOMMENTTIIN " + newAnswer + " --- RestBookStoreController");
		return answerRepository.save(newAnswer);
	}
	
	
	
	// Muokataan vastausta id:n perusteella PUT http://localhost:8080/answersjson/1
	@PutMapping("/answersjson/{id}")
	Answer editAnswer(@RequestBody Answer editedAnswer, @PathVariable Long id) {
		System.out.println("---MUOKATAAN VASTAUSTA, JONKA ID: " + id + " --- RestBookStoreController");
		editedAnswer.setAnswerid(id);
		return answerRepository.save(editedAnswer);
	}
	
	
	// Poistetaan vastaus DELETE http://localhost:8080/answersjson/2
	@DeleteMapping("/answersjson/{id}")
	public Iterable<Answer> deleteAnswer(@PathVariable Long id) {
		
		System.out.println("---POISTETAAN VASTAUS, JONKA ID: " + id + " --- RestBookStoreController");
		answerRepository.deleteById(id);
		return answerRepository.findAll();
	}
	
	
	

	
	


}
