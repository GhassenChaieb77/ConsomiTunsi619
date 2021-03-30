
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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	private float sale;
	
	
	@Pattern(regexp = "619[0-9]{6}" , message = "must start with 619 and with 6 numbers")
	private String code;
	

	private int quantity;
	
	
	@JsonIgnore
	
	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.LAZY, mappedBy="product")
	private List<Subject> subjects;
	
	/*@OneToOne(mappedBy="product",fetch=FetchType.LAZY)
	private Subject subjects;*/
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy="product",fetch=FetchType.LAZY)
	private List<Publicity> publicities;
	

	@ManyToOne(cascade = CascadeType.ALL)
	private Category category;
	
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


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}








	public List<Publicity> getPublicities() {
		return publicities;
	}


	public void setPublicities(List<Publicity> publicities) {
		this.publicities = publicities;
	}


	


	


	





	


	public Product(String name, float price, String picture, String code, int quantity) {
		super();
		this.name = name;
		this.price = price;
		this.picture = picture;
		this.code = code;
		this.quantity = quantity;
	}
	public Product(){}


	public float getSale() {
		return sale;
	}


	public void setSale(float sale) {
		this.sale = sale;
	}


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}


	public List<Subject> getSubjects() {
		return subjects;
	}


	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}


	public Product(Long id, String name, float price, String picture, float sale,
			@Pattern(regexp = "619[0-9]{6}", message = "must start with 619 and with 6 numbers") String code,
			int quantity, List<Subject> subjects, List<Publicity> publicities, Category category) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.picture = picture;
		this.sale = sale;
		this.code = code;
		this.quantity = quantity;
		this.subjects = subjects;
		this.publicities = publicities;
		this.category = category;
	}
	
	
}