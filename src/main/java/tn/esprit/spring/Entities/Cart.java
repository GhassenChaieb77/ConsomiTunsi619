package tn.esprit.spring.Entities;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Cart implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private int quantity;
	
	private float prodpricetotal;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="cart",fetch=FetchType.EAGER)
	private Set<Product> products;
	
	@OneToOne(mappedBy="cart")
	private Order order;

	public Cart(int quantity, float prodpricetotal, Set<Product> products, Order order) {
		super();
		this.quantity = quantity;
		this.prodpricetotal = prodpricetotal;
		this.products = products;
		this.order = order;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getProdpricetotal() {
		return prodpricetotal;
	}

	public void setProdpricetotal(float prodpricetotal) {
		this.prodpricetotal = prodpricetotal;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
	
}
