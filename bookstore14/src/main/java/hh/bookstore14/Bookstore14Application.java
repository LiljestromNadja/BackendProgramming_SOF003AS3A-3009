package hh.bookstore14;

import java.time.LocalDateTime;
/*
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner; 	*/
import org.springframework.boot.SpringApplication;	
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;

/*
import hh.bookstore14.domain.ApplicationUser;
import hh.bookstore14.domain.ApplicationUserRepository;
import hh.bookstore14.domain.Book;
import hh.bookstore14.domain.BookRepository;
import hh.bookstore14.domain.Category;
import hh.bookstore14.domain.CategoryRepository; */

@SpringBootApplication
public class Bookstore14Application {
	
//	private static final Logger log = LoggerFactory.getLogger(Bookstore14Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Bookstore14Application.class, args);
		
		System.out.println("");
		System.out.println("Jos tekemisen puutetta ilmenee: ");
		System.out.println("-käyttäjän muokkaaminen");
		System.out.println("-kirjat kategorioittain");
		
		System.out.println("-Jonkinlainen aakkostus, käyttäjän mukaan lajittelu tms. ");
		
		System.out.println("Versio 14 (01-11-2023)");
		System.out.println("- tietokannan polku bookstorecommentsdb (application.properties)");
		
		
		System.out.println("");
		
		

		LocalDateTime ldt = LocalDateTime.now();

		System.out.println("Bookstore running!");
		System.out.println("Käytössä MariaDb");
		

		System.out.println(ldt);
		

	
	}
	
	
	/*
	@Bean
	public CommandLineRunner demo(BookRepository repository, CategoryRepository crepository, ApplicationUserRepository urepository) {
		return(args) ->{
			
			Category ykkonen =new Category("IT");
			Category kakkonen=new Category("Ohjelmointi");
			
			crepository.save(ykkonen);
			crepository.save(kakkonen);
			
			Book kirjayks = new Book("Ohjelmoinnin salat", "tuntematon", "978-951-12345-0-1" , 2010, 24.95, crepository.findByName("IT").get(0));
			Book kirjakaks = new Book("Tietokannat", "Severi Hakkeri", "978-951-12345-0-2", 2018, 29.90, crepository.findByName("Ohjelmointi").get(0));
			Book kirjakolme = new Book("Koodaajan käsikirja", "Hilda Koodinkirjoittaja", "978-951-12345-0-3", 2020, 24.95, crepository.findByName("Ohjelmointi").get(0));
			//Book kirjakolme = new Book("Koodaajan käsikirja", "Hilda Koodinkirjoittaja", "978-951-12345-0-3", 2020, 24.95, kakkonen);
			
			
			//Tallentaa objektin tietokantaan
			System.out.println("sinne meni!");
			repository.save(kirjayks);
			repository.save(kirjakaks);
			repository.save(kirjakolme);
			
			log.info("lisätty kirjat 1,2,3");
			
			//Lisätään käyttäjät tietokantaan, admin/admin ja user/user, pentti/pentti(admin)
			ApplicationUser user1 = new ApplicationUser("user","$2a$10$RIqlxElPXzQKJayHKJwSNOxDMnMh.j.OHwQvOoPj0gld.sbXsqqgK" ,"USER");
			ApplicationUser user2 = new ApplicationUser("admin", "$2a$10$aGjp6jEUEspwUkQrCbGAWuKScc9DRHTQ6LXMRX2TAM5A6tzHdy8/6", "ADMIN");
			ApplicationUser pentti = new ApplicationUser("pentti", "$2a$10$KzsfhslxfLsSivybtKfyguhx5zS9vzG.LIUwqC2SWOIOQZ7ml/gJi", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);
			urepository.save(pentti);
			System.out.println("LISÄTTY KÄYTTÄJÄT: " + user1 + " " + user2 + " " + pentti );
			log.info("Lisätty käyttäjät admin ja user ja pentti");
			
			System.out.println("nouda, fetch");
			for (Book book : repository.findAll()) {
			System.out.println(book.toString());
			}
		};
	}*/

}
