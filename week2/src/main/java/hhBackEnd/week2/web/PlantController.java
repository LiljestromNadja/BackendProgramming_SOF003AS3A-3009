package hhBackEnd.week2.web;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import hhBackEnd.week2.domain.Plant;

@Controller 
public class PlantController {
	

	//http://localhost:8080/plants
	@GetMapping("plants") //urlin endpoint
	public String listPlants(Model model) {
		
		ArrayList<Plant> plants = new ArrayList<>();
		
		plants.add(new Plant("Isotäplämaija", "Pidä multa kosteana", "Hajavalo, ei suora auringonpaiste"));
		plants.add(new Plant("Kumiviikuna", "Anna kuivahtaa", "Hajavalo"));
		plants.add(new Plant("Viirivehka", "Lähes aina jano, anna silti kuivahtaa", "Selviää hämärässä"));
		
		model.addAttribute("plants", plants);
		return "plants";
	}

}
