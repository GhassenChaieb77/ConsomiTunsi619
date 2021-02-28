package tn.esprit.spring.Entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Category")
public class Category implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@OneToMany(mappedBy="category",fetch=FetchType.EAGER)
	private List<Product> products;
	
	@OneToOne
	private Layer layer;

	
	public Category(String name, List<Product> products, Layer layer) {
		super();
		this.name = name;
		this.products = products;
		this.layer = layer;
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
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public Layer getLayer() {
		return layer;
	}
	public void setLayer(Layer layer) {
		this.layer = layer;
	}
	
	

}