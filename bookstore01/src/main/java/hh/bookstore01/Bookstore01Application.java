package hh.bookstore01;

//BOOKSTORE WEEK 3

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.bookstore01.domain.Book;
import hh.bookstore01.domain.BookRepository;

@SpringBootApplication
public class Bookstore01Application {
	
	private static final Logger log = LoggerFactory.getLogger(Bookstore01Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Bookstore01Application.class, args);
	}
	
	
	@Bean
	public CommandLineRunner demo(BookRepository repository) {
		return(args) ->{
			Book kirjayks = new Book("Ohjelmoinnin salat", "tuntematon", "978-951-12345-0-1" , 2010, 24.95);
			Book kirjakaks = new Book("Tietokannat", "Severi Hakkeri", "978-951-12345-0-2", 2018, 29.90);
			Book kirjakolme = new Book("Koodaajan käsikirja", "Hilda Koodinkirjoittaja", "978-951-12345-0-3", 2020, 24.95);
			
			//Tallentaa objektin tietokantaan
			System.out.println("sinne meni!");
			repository.save(kirjayks);
			repository.save(kirjakaks);
			repository.save(kirjakolme);
			
			log.info("lisätty kirjat 1,2,3");
			
			System.out.println("nouda, fetch");
			
			for (Book book : repository.findAll()) {
			System.out.println(book.toString());
			}
		};
	}

}
