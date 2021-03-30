package tn.esprit.spring.Entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	

	@Enumerated(EnumType.STRING)
	private PaymentMethod paymentmethod;
	
	@JsonIgnore
	@ManyToOne(cascade=CascadeType.ALL)
	DeliveryAgent deliveryagent;
	
	@JsonIgnore
	@OneToOne(mappedBy="order")
	private Bill bill;
	
	
	@JsonIgnore
	@OneToOne
	private Donation donation ;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.LAZY)
	private Set<OrderLine> orderline;

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

    
	public DeliveryAgent getDeliveryagent() {
		return deliveryagent;
	}

	public void setDeliveryagent(DeliveryAgent deliveryagent) {
		this.deliveryagent = deliveryagent;
	}


	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}


	public Set<OrderLine> getOrderline() {
		return orderline;
	}

	public void setOrderline(Set<OrderLine> orderline) {
		this.orderline = orderline;
	}

	public PaymentMethod getPaymentmethod() {
		return paymentmethod;
	}

	public void setPaymentmethod(PaymentMethod paymentmethod) {
		this.paymentmethod = paymentmethod;
	}

	public Donation getDonation() {
		return donation;
	}

	public void setDonation(Donation donation) {
		this.donation = donation;
	}

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Order(Long id, Date date, String address, float totalprice, PaymentMethod paymentmethod,
			DeliveryAgent deliveryagent, Bill bill, Donation donation,
			Set<OrderLine> orderline) {
		super();
		this.id = id;
		this.date = date;
		this.address = address;
		this.totalprice = totalprice;
		this.paymentmethod = paymentmethod;
		this.deliveryagent = deliveryagent;
		this.bill = bill;
		this.donation = donation;
		this.orderline = orderline;
	}

	
  

    
	
	
}
