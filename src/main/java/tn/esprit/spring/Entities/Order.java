package tn.esprit.spring.Entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="commande")
public class Order implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	@Temporal (TemporalType.DATE)
	private Date date;
	

	private String address;
	

	private float totalprice;
	

	private String paymentmethod;
	
	@ManyToOne
	DeliveryAgent deliveryagent;
	
	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.LAZY, mappedBy="order")
	private Set<Complaint> complaints;
	
	@OneToOne(mappedBy="order")
	private Bill bill;
	
	@ManyToOne
	private User user ;
	
	@OneToOne
	private Cart cart;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public float getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(float totalprice) {
		this.totalprice = totalprice;
	}

	public String getPaymentmethod() {
		return paymentmethod;
	}

	public void setPaymentmethod(String paymentmethod) {
		this.paymentmethod = paymentmethod;
	}

	public DeliveryAgent getDeliveryagent() {
		return deliveryagent;
	}

	public void setDeliveryagent(DeliveryAgent deliveryagent) {
		this.deliveryagent = deliveryagent;
	}

	public Set<Complaint> getComplaints() {
		return complaints;
	}

	public void setComplaints(Set<Complaint> complaints) {
		this.complaints = complaints;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Order(Date date, String address, float totalprice, String paymentmethod, DeliveryAgent deliveryagent,
			Set<Complaint> complaints, Bill bill, User user, Cart cart) {
		super();
		this.date = date;
		this.address = address;
		this.totalprice = totalprice;
		this.paymentmethod = paymentmethod;
		this.deliveryagent = deliveryagent;
		this.complaints = complaints;
		this.bill = bill;
		this.user = user;
		this.cart = cart;
	}
	
	
	
}
