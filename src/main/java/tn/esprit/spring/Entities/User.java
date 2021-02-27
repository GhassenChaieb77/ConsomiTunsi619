package tn.esprit.spring.Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;

@Entity
@Table(name="USER")
public class User implements Serializable{
	
	private static final long serialVersionUID = 4659010424724882893L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="USER_ID")
	private int id;
	@Column(name="USER_FirstName")
	private String FirstName;
	@Column(name="USER_LastName")
	private String LastName;
	@Temporal (TemporalType.DATE)
	private Date date;
    @Enumerated(EnumType.STRING)
	Role Role;    
	@Column(name="Email",nullable = false)
	@Email
    private String email;
    @Column(name="Password")
    private String password;
    @Column(name="Tele")
    private int telephone;
    @Column(name="Balance")
    private float balance;
    
    @Column(name="Gender")
    private String gender;
    
	public User() {
		
	}
	
	
	

	public User(int id, String firstName, String lastName, Date date, tn.esprit.spring.Entities.Role role,
			@Email String email, String password, int telephone, float balance) {
		super();
		this.id = id;
		FirstName = firstName;
		LastName = lastName;
		this.date = date;
		Role = role;
		this.email = email;
		this.password = password;
		this.telephone = telephone;
		this.balance = balance;
	}




	public int getTelephone() {
		return telephone;
	}




	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}






	public float getBalance() {
		return balance;
	}






	public void setBalance(float balance) {
		this.balance = balance;
	}




	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Role getRole() {
		return Role;
	}

	public void setRole(Role role) {
		Role = role;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	} 
	
	@ManyToMany	
	private List<Jackpot> jackpots = new ArrayList<>();
    
	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER, mappedBy="user")
	private List<Order> orders = new ArrayList<>();
    
	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER, mappedBy="user")
	private List<Comment> comments = new ArrayList<>();

	public String getGender() {
		return gender;
	}




	public void setGender(String gender) {
		this.gender = gender;
	}




	public List<Jackpot> getJackpots() {
		return jackpots;
	}




	public void setJackpots(List<Jackpot> jackpots) {
		this.jackpots = jackpots;
	}




	public List<Order> getOrders() {
		return orders;
	}




	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}




	public List<Comment> getComments() {
		return comments;
	}




	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	
}

