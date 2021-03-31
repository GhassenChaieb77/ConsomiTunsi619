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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import tn.esprit.spring.Entities.Category;
import tn.esprit.spring.Entities.Product;
@Entity
public class Layer implements Serializable{
	
	
	

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="layerid")
	private long id;
	
	@Column(name="capacity")
	private int capacity;
	
	@Column(name="capacitynow")
	private int capacityNow;
	
	private String responsible;
	
	@OneToMany(cascade = CascadeType.REFRESH)
	private List<Product> products;	
	
	@OneToOne
	private Category category;
	
	public Layer()
	{
		
	}
	
	public Layer(int capacity, Category category) {
		super();
		this.capacity = capacity;
		this.category = category;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}



	public String getResponsible() {
		return responsible;
	}

	public void setResponsible(String responsible) {
		this.responsible = responsible;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public int getCapacityNow() {
		return capacityNow;
	}

	public void setCapacityNow(int capacityNow) {
		this.capacityNow = capacityNow;
	}
	
	
	
	
	
	
}
