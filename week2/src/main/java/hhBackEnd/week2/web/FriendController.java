package hhBackEnd.week2.web;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FriendController {
	
	//teht 3
	//http://localhost:8080/friend
	private ArrayList<String> friends = new ArrayList<>();
	
	//voi toteuttaa myös 
	//@RequestMapping(value="/friend", method=RequestMethod.GET)
	@GetMapping("/friend")
	public String handleFriends(@RequestParam(name="nimi", required = false) String name, Model model) {
		if (name != null) {
			friends.add(name);
		}
		model.addAttribute("friends", friends);
		return "friendsview";
	}
	

}
