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

@Entity 
public class Donation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id ;
	
	@Column(name="QuantityProd")
	private int quantityProd ;
	
	@ManyToOne
	private Event event ;
	
	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER, mappedBy="donation")
	private List<Product> products;
	
	
	
	public Donation(int quantityProd, Event event, List<Product> products) {
		super();
		this.quantityProd = quantityProd;
		this.event = event;
		this.products = products;
	}


	public Event getEvent() {
		return event;
	}

	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public int getQuantityProd() {
		return quantityProd;
	}
	
	public void setQuantityProd(int quantityProd) {
		quantityProd = quantityProd;
	}
	

}
