package HaagaHelia.springBootDemo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SBdemoController {
	
	@RequestMapping
	@ResponseBody
	public String showMainPage() {
		
		return "This is the main page";
	}
	
	@RequestMapping("index")
	@ResponseBody
	public String showIndex() {
		return "This is index";
	}

	
	@RequestMapping("sayhello") //http://localhost:8080/sayhello?yourname=Nadja
	@ResponseBody
	public String sayHello(@RequestParam (name="yourname")String name) {
		
		return "Hello " + name;
		
	}
	
	@RequestMapping("muumimoi") //http://localhost:8080/muumimoi?yourname=Pentti tai localhost:8080/muumimoi
	@ResponseBody
	public String muumiMoi(@RequestParam(name="yourname", required=false, defaultValue="Muumi") String nimi) {
		
		return "Heippa " + nimi;
	}
	
	
	@RequestMapping("nameandage") //http://localhost:8080/sayhello?yourname=Nadja
	@ResponseBody
	public String sayHello(@RequestParam (name="yourname", required=false, defaultValue="Olet siis nimetön") String name, 
			@RequestParam(name="age") int age) {
		
		return "Hello " + name + " " + age + " vuotta!";
		
	}
	
	
	
	
}
