
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
	
	private Long id;
	
	
	private String name;
	

	private float price;
	
	
	private String picture;
	
	
	private int code;
	

	private int quantity;
	
	@ManyToOne
	private Category category;
	
	@OneToOne(mappedBy="product")
	private Subject subject;
	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="product",fetch=FetchType.LAZY)
	private List<Publicity> publicities;
	
	
	@ManyToMany(cascade = CascadeType.ALL,mappedBy="products")
	private List<Stock> stocks;


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


	public Subject getSubject() {
		return subject;
	}


	public void setSubject(Subject subject) {
		this.subject = subject;
	}


	public List<Publicity> getPublicities() {
		return publicities;
	}


	public void setPublicities(List<Publicity> publicities) {
		this.publicities = publicities;
	}


	public List<Stock> getStocks() {
		return stocks;
	}


	public void setStocks(List<Stock> stocks) {
		this.stocks = stocks;
	}


	public Product(String name, float price, String picture, int code, int quantity, Category category, Subject subject,
			List<Publicity> publicities, List<Stock> stocks) {
		super();
		this.name = name;
		this.price = price;
		this.picture = picture;
		this.code = code;
		this.quantity = quantity;
		this.category = category;
		this.subject = subject;
		this.publicities = publicities;
		this.stocks = stocks;
	}
	
	
	
}