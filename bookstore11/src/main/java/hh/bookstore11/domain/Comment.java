package hh.bookstore11.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class Comment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) //mariadb
	private long commentid;
	
	private String commentmessage;
	
	@ManyToOne
	@JoinColumn(name = "applicationuserid")
	private ApplicationUser applicationuser; //tämän pitää olla sama ApplicationUser.javassa MappedBy
	
	@ManyToOne
	@JoinColumn(name="bookid")
	private Book book; //tämän pitää olla sama Book.javassa MappedBy
	
		
	private String commentcreated = "";

	public Comment() {
		//super();
	}

	public Comment(String commentmessage, ApplicationUser applicationuser, Book book,
			String commentcreated) {
		super();
		//this.commentid = commentid;
		this.commentmessage = commentmessage;
		this.applicationuser = applicationuser;
		this.book = book;
		this.commentcreated = commentcreated;
	}

	public long getCommentid() {
		return commentid;
	}

	public void setCommentid(long commentid) {
		this.commentid = commentid;
	}

	public String getCommentmessage() {
		return commentmessage;
	}

	public void setCommentmessage(String commentmessage) {
		this.commentmessage = commentmessage;
	}

	public ApplicationUser getApplicationuser() {
		return applicationuser;
	}

	public void setApplicationuser(ApplicationUser applicationuser) {
		this.applicationuser = applicationuser;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public String getCommentcreated() {
		return commentcreated;
	}

	public void setCommentcreated(String commentcreated) {
		this.commentcreated = commentcreated;
	}

	@Override
	public String toString() {
		return "Comment [commentid=" + commentid + ", commentmessage=" + commentmessage + ", commentcreated="
				+ commentcreated + "]";
	}
	
	
	
	
	
	
	
	

}
