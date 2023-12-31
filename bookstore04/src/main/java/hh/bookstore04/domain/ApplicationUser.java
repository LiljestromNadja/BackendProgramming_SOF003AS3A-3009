package hh.bookstore04.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "UserTable")
public class ApplicationUser {
	
	//ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;  
    
    
    //Uniikki käyttäjätunnus
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    
    //Salasana
    @Column(name = "password", nullable = false)
    private String passwordHash;
    
    //Käyttäjän rooli
    @Column(name = "role", nullable = false)
    private String role;
    
    //ExampleGet your own Java Server
    /*enum Role {
    	  ADMIN,
    	  USER,
    	  VISITOR
    	}*/
    
    
    
    //Constructors  
	public ApplicationUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ApplicationUser(String username, String passwordHash, String role) {
		super();
		this.username = username;
		this.passwordHash = passwordHash;
		this.role = role;
	}

	

	//get&set
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	//toString
	@Override
	public String toString() {
		return "ApplicationUser [id=" + id + ", username=" + username + ", passwordHash=" + passwordHash + ", role="
				+ role + "]";
	}    
    
	
    
}
