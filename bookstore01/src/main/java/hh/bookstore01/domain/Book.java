package hh.bookstore01.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Book {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)	
	private long id;
	
	private String title, author, isbn;
	private int publicationYear;
	private double price;
	
	public Book() {}
	
	public Book(String title, String author, String isbn, int publicationYear, double price) {
		super();
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.publicationYear = publicationYear;
		this.price = price;
	}
	public Book(long id, String title, String author, String isbn, int publicationYear, double price) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.publicationYear = publicationYear;
		this.price = price;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public int getPublicationYear() {
		return publicationYear;
	}
	public void setPublicationYear(int publicationYear) {
		this.publicationYear = publicationYear;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	/*
	@Override
	public String toString() {
		return "Book [title=" + title + ", author=" + author + ", isbn=" + isbn + ", publicationYear=" + publicationYear
				+ ", price=" + price + "]";
	}
	*/
	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", isbn=" + isbn + ", publicationYear="
				+ publicationYear + ", price=" + price + "]";
	}
	
	
	
	
	

}
