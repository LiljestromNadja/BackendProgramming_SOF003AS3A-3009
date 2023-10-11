package hh.bookstore04.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {

	List<Book> findByTitle(String title); // Tulevaisuutta varten :) Muista ottaa importointi pois piilosta
	List<Book> findById(long id);
	List<Book> deleteById(long id);
	

	//voi tehdä tietokantaan hakuja eri attribuuteilla, esim findById(Long id), findByIsbn(String isbn)
}
