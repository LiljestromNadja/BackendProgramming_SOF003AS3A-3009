package hhBackEnd.week2.web;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import hhBackEnd.week2.domain.Student;

@Controller
public class StudentController {
	
	//teht 2
	//http://localhost:8080/hellostudent
	@GetMapping("hellostudent") //urlin endpoint
	public String helloStudents(Model model) {
		
		ArrayList<Student> students = new ArrayList<>();
		
		students.add(new Student("Kate", "Cole"));
		System.out.println(students);
		students.add(new Student("Dan", "Brown"));
		students.add(new Student("Mike", "Mars"));
		
		model.addAttribute("students", students); //lainausmerkeissä olevan pitää olla sama kuin .html:ssä
		
		System.out.println(students);
		return "studentlistview";
	}

}
