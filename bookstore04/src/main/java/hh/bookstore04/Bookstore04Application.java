package hh.bookstore04;

//BOOKSTORE WEEK 6

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.bookstore04.domain.Book;
import hh.bookstore04.domain.BookRepository;
import hh.bookstore04.domain.Category;
import hh.bookstore04.domain.CategoryRepository;

@SpringBootApplication
public class Bookstore04Application {
	
	private static final Logger log = LoggerFactory.getLogger(Bookstore04Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Bookstore04Application.class, args);
	}
	
	
	@Bean
	public CommandLineRunner demo(BookRepository repository, CategoryRepository catrepository) {
		return(args) ->{
			
						
			Category ykkonen =new Category("IT");
			Category kakkonen=new Category("Ohjelmointi");
			
			catrepository.save(ykkonen);
			catrepository.save(kakkonen);
			
			log.info("Lisätään kategorioita");

			
			
			
			
			
			
			Book kirjayks = new Book("Ohjelmoinnin salat", "tuntematon", "978-951-12345-0-1" , 2010, 24.95, catrepository.findByName("IT").get(0));
			Book kirjakaks = new Book("Tietokannat", "Severi Hakkeri", "978-951-12345-0-2", 2018, 29.90, catrepository.findByName("Ohjelmointi").get(0));
			Book kirjakolme = new Book("Koodaajan käsikirja", "Hilda Koodinkirjoittaja", "978-951-12345-0-3", 2020, 24.95,catrepository.findByName("Ohjelmointi").get(0));
			Book kirjanelja = new Book("Koodaajan käsikirja uudistettu painos", "Hilda Koodinkirjoittaja", "978-951-12345-0-3", 2020, 24.95, catrepository.findByName("Ohjelmointi").get(0));
			
			//Tallentaa objektin tietokantaan
			System.out.println("sinne meni!");
			repository.save(kirjayks);
			repository.save(kirjakaks);
			repository.save(kirjakolme);
			repository.save(kirjanelja);
			
			log.info("lisätty kirjat 1,2,3");
			
			System.out.println("nouda, fetch");
			
			for (Book book : repository.findAll()) {
			System.out.println(book.toString());
			}
		};
	}

}
