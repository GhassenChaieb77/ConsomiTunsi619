package tn.esprit.spring.Entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Order implements Serializable {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	@Temporal (TemporalType.DATE)
	private Date date;
	
	private String adress;
	
	private float totalprice;
	
	private String paymentmethod;
	
	@ManyToOne
	DeliveryAgent deliveryagent;
	
	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER, mappedBy="order")
	private List<Complaint> complaints;
	
	@OneToOne
	private Bill bill;
	
	@ManyToOne
	private User user ;
	
	@OneToOne
	private Cart cart;
	
	

	public Order(Date date, String adress, float totalprice, String paymentmethod, DeliveryAgent deliveryagent,
			List<Complaint> complaints, Bill bill, User user, Cart cart) {
		super();
		this.date = date;
		this.adress = adress;
		this.totalprice = totalprice;
		this.paymentmethod = paymentmethod;
		this.deliveryagent = deliveryagent;
		this.complaints = complaints;
		this.bill = bill;
		this.user = user;
		this.cart = cart;
	}

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

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
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

	public List<Complaint> getComplaints() {
		return complaints;
	}

	public void setComplaints(List<Complaint> complaints) {
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
	
	
}
