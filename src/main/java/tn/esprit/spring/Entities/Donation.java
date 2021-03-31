package tn.esprit.spring.Entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity 
public class Donation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id ;
	
	@Column(name="libelle")
	private String libelle ;
	
	
	@Temporal(TemporalType.DATE)
	private Date date = new Date(System.currentTimeMillis());
	
	@ManyToOne (cascade = CascadeType.ALL)
	private Event event ; 
	
	//@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.LAZY, mappedBy="donation")
	//private List<Product> products;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Order order ; 
	
	public Donation(){ super(); }
	
	public Donation(String libelle, Date date , Event event, Order order) {
		super();
		this.libelle = libelle;
		this.date = date; 
		this.event = event;
		this.order = order ; 
		//this.products = products;
	}


	public Event getEvent() {
		return event;
	}

	
	public void setEvent(Event event) {
		this.event = event;
	}


	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}


	public String getLibelle() {
		return libelle;
	}


	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}


	public Order getOrder() {
		return order;
	}


	public void setOrder(Order order) {
		this.order = order;
	}
	
	
	

}
