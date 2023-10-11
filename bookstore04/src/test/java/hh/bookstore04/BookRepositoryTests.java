package hh.bookstore04;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import hh.bookstore04.domain.Book;
import hh.bookstore04.domain.BookRepository;
//import hh.bookstore04.domain.Category;
import hh.bookstore04.domain.CategoryRepository;

//HUOMHUOM! Pakettien erilaiset nimet src/main/java ja src/test/java saattavat aiheuttaa erroreita. 
//Esim. jos on nimetty vaikka (tai kopioitu eri projekteista) src/main/java/hh.bookstore04 ja src/test/java/k23.bookstore02 == JUnit-testaus ei löydä testiluokkaa. 


@DataJpaTest
public class BookRepositoryTests {
	
	
	@Autowired
	BookRepository brepository;
	
	@Autowired
	CategoryRepository catrepository;
	
	@Test
	public void findBookByTitle() {
		List<Book> books = brepository.findByTitle("Ohjelmoinnin salat");
		assertThat(books.get(0).getTitle().equalsIgnoreCase("ohjelmoinnin salat"));
		
	}
	@Test
	public void findBookByAnotherTitle() {
		List<Book> books = brepository.findByTitle("Tietokannat");
		assertThat(books.get(0).getTitle().equalsIgnoreCase("tietokannat"));
		
	}
	
	@Test
	public void findByid() {
		
		brepository.findById((long)1); 
		List<Book> books = brepository.findById(1); //etsitään id:llä 1
		
		assertThat(books).hasSize(1); //tuloksen pitäisi olla 1
	}
	
    @Test
    public void deleteNewBook() {
		List<Book> books = brepository.findByTitle("Tietokannat");
		Book book = books.get(0);
		brepository.delete(book);
		List<Book> newBooks = brepository.findByTitle("Tietokannat");
		assertThat(newBooks).hasSize(0);
     }
    
    @Test
    public void findAllBooks() {
    	Iterable<Book> books = brepository.findAll(); //haetaan kaikki kirjat
    	assertThat(books).hasSize(3);// listan pituus pitäisi olla 3
    }
    


    


}
