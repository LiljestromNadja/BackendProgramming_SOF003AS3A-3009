package hhBackEnd.week2.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller //KANNATTAA MUISTAA 
public class HelloThymeController {
	
	//Teht 1: Hello, if, greater than, lower than
	//http://localhost:8080/hellothyme?name=John&age=20
	@GetMapping("/hellothyme") //urlin endpoint
	public String sayGreetings(@RequestParam (name="name") String name, 
			@RequestParam(name="age") int ika,
			Model model) {
		model.addAttribute("nimi", name);
		model.addAttribute("ika", ika);
		
		return "hellothyme"; //html -tiedosto johon viitataan
	}
	
	@RequestMapping("index")
	
	public String showIndex() {
		return "index.html";
	}
}

