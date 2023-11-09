package hh.bookstore17.web;



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

import hh.bookstore17.domain.Answer;
import hh.bookstore17.domain.AnswerRepository;
import hh.bookstore17.domain.ApplicationUserRepository;
import hh.bookstore17.domain.Comment;
import hh.bookstore17.domain.CommentRepository;
import jakarta.validation.Valid;

@Controller
public class AnswerController {
	
	@Autowired
	private AnswerRepository answerRepository;
	
	@Autowired
	private ApplicationUserRepository applicationuserrepository;
	
	@Autowired 
	private CommentRepository commentrepository;
	
	//Kommentteihin vastaaminen kommentin id:n perusteella
	
	//Lisätään vastaus kommenttiin
	//Tänne ohjataan bookComments -sivulta 
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')") //metoditason tarkistus onko oikeus lisätä
	@RequestMapping(value="addAnswer/{id}")
	public String addAnswer(@PathVariable("id") Long id, Model model, @AuthenticationPrincipal UserDetails userDetails) {
	
		Answer answer = new Answer();
		
		answer.setComment(commentrepository.findById(id).get());
		
		System.out.println("");
		System.out.println("KOMMENTTI JOHON VASTATAAN" + answer.getComment() + "--- CommentController, addComment");
		System.out.println("VASTAUS: " + answer.getAnswerid());

		model.addAttribute("answer", answer);
		
		System.out.println("VASTAUS, commentID: " + commentrepository.findById(id).get());
		model.addAttribute("applicationuser", applicationuserrepository.findByUsername(userDetails.getUsername())); 
		
		return "addAnswer";
	}
	
	//Tallennetaan vastaus
	//Tänne ohjataan tallennus seuraavista endpointeista:  addAnswer/{id}
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')") //metoditason tarkistus onko oikeus 
	@RequestMapping(value="/saveAnswer", method = RequestMethod.POST) //tämä käytössä addAnswer.html :ssä
	public String save(@Valid Answer answer, Comment comment, BindingResult bindingResult, Model model, @AuthenticationPrincipal UserDetails userDetails) {
		if (bindingResult.hasErrors()) {
			System.out.println("Some validation error happened");
			model.addAttribute("answer", answer);
			model.addAttribute("comment", commentrepository.findById(answer.getComment().getCommentid())); // <---- tämän takia edellinen kaatui, tärkeä
			model.addAttribute("applicationuser", applicationuserrepository.findByUsername(userDetails.getUsername())); 
			return "addAnswer";
		}
		
		
		answerRepository.save(answer);
		
		System.out.println("LISÄTTY VASTAUS: " + answer + answer.getComment());
		System.out.println("Lisääjän käyttäjätunnus: " + comment.getApplicationuser().getUsername());
		
		return "redirect:booklist";
	}
	
	





}
