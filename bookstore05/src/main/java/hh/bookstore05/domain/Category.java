package hh.bookstore05.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;

@Entity
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long categoryid;
	
	@Size(min = 1, max = 30)
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "category") //mappedByn pitää olla sama kuin Book.javassa private Category category;
	@JsonIgnore
	private List<Book> books;

	public Category() {}
	
	public Category(String name) {  //HUOM, tarkkana constructoreiden kanssa!
		super();
		this.name = name;
		
	}

	public Long getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(Long categoryid) {
		this.categoryid = categoryid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	@Override
	public String toString() {
		return "Category [categoryid=" + categoryid + ", name=" + name + ", books=" + books + "]";
	}
	
	
	
	
	
	
	

	
	

}
