package tn.esprit.spring.Entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity 
public class Donation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	int id ;
	
	@Column(name="QuantityProd")
	int quantityProd ;
	
	@ManyToOne
	private Event event ;
	
	public Event getEvent() {
		return event;
	}

	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getQuantityProd() {
		return quantityProd;
	}
	
	public void setQuantityProd(int quantityProd) {
		quantityProd = quantityProd;
	}
	

}
