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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class User implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)

	private Long id;

	private String FirstName;
	
	private String LastName;
	
	private Date date;
    @Enumerated(EnumType.STRING)
	Role Role;    
	@Column(name="email",nullable = false)
	@Email
    private String email;

    private String password;

    private int telephone;

    private float balance;
    
 
    private String gender;
    
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL,fetch=FetchType.LAZY)
	private List<Jackpot> jackpots = new ArrayList<>();
    
    @JsonIgnore
	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.LAZY, mappedBy="user")
	private List<Comment> comments = new ArrayList<>();

	@JsonIgnore
	@OneToOne(mappedBy="user")
	private Cart cart;


	public User(String firstName, String lastName, Date date, tn.esprit.spring.Entities.Role role, @Email String email,
			String password, int telephone, float balance, String gender, List<Jackpot> jackpots,
			List<Comment> comments, Cart cart) {
		super();
		FirstName = firstName;
		LastName = lastName;
		this.date = date;
		Role = role;
		this.email = email;
		this.password = password;
		this.telephone = telephone;
		this.balance = balance;
		this.gender = gender;
		this.jackpots = jackpots;
		this.comments = comments;
		this.cart = cart;
	}

	public User()
	{
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
	






     
	
}

