
package tn.esprit.spring.Entities;
import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="price")
	private float price;
	
	@Column(name="picture")
	private String picture;
	
	@Column(name="code")
	private int code;
	
	@Column(name="quantity")
	private int quantity;
	
	@ManyToOne
	private Category category;
	
	@ManyToOne
	private Cart cart;
	
	@ManyToOne
	private Donation donation;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="product",fetch=FetchType.LAZY)
	private List<Publicity> publicities;
	
	@OneToOne
	private Subject subject;
	
	@ManyToMany(cascade = CascadeType.ALL,mappedBy="products")
	private List<Stock> stocks;
	
	
	public Product(String name, float price, String picture, int code, int quantity, Category category, Cart cart,
			Donation donation, List<Publicity> publicities, Subject subject, List<Stock> stocks) {
		super();
		this.name = name;
		this.price = price;
		this.picture = picture;
		this.code = code;
		this.quantity = quantity;
		this.category = category;
		this.cart = cart;
		this.donation = donation;
		this.publicities = publicities;
		this.subject = subject;
		this.stocks = stocks;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	

	public Donation getDonation() {
		return donation;
	}

	public void setDonation(Donation donation) {
		this.donation = donation;
	}

	public List<Publicity> getPublicities() {
		return publicities;
	}

	public void setPublicities(List<Publicity> publicities) {
		this.publicities = publicities;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public List<Stock> getStocks() {
		return stocks;
	}

	public void setStocks(List<Stock> stocks) {
		this.stocks = stocks;
	}
	
	
	
}