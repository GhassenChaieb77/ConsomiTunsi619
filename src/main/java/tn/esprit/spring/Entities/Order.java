package tn.esprit.spring.Entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.beans.factory.annotation.Autowired;

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
	

  	private String firstName;
	
	private String lastName;
	private int phoneNumber;
	private String address;
	

	private float totalprice;
	

	
	private String paymentmethod;
	
	@JsonIgnore
	@ManyToOne(cascade=CascadeType.ALL)
	DeliveryAgent deliveryagent;
	

	@OneToOne(mappedBy="order")
	private Bill bill;
	

	@OneToOne
	private Donation donation ;
	@JsonIgnore
	@ManyToOne(cascade=CascadeType.ALL)
	private Cart cart;
	
	@JsonIgnore
	@ManyToOne(cascade=CascadeType.ALL)
	private User user;
	
	@ManyToOne
	private Coupon coupon;

 

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


/*	public List<OrderLine> getOrderlines() {
		return orderlines;
	}

	public void setOrderLines(List<OrderLine> orderlines) {
		this.orderlines = orderlines;
	}*/



	@Override
	public String toString() {
		return "Order [id=" + id + ", date=" + date + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", phoneNumber=" + phoneNumber + ", address=" + address + ", totalprice=" + totalprice
				+ ", paymentmethod=" + paymentmethod + ", deliveryagent=" + deliveryagent + ", bill=" + bill
				+ ", donation=" + donation + ", cart=" + cart + ", user=" + user + ", coupon=" + coupon + "]";
	}

	public String getPaymentmethod() {
		return paymentmethod;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public void setPaymentmethod(String paymentmethod) {
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Coupon getCoupon() {
		return coupon;
	}

	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}

	public Order(Long id, Date date, String firstName, String lastName, int phoneNumber, String address,
			float totalprice, String paymentmethod, DeliveryAgent deliveryagent, Bill bill, Donation donation,
			Cart cart, User user, Coupon coupon) {
		super();
		this.id = id;
		this.date = date;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.totalprice = totalprice;
		this.paymentmethod = paymentmethod;
		this.deliveryagent = deliveryagent;
		this.bill = bill;
		this.donation = donation;
		this.cart = cart;
		this.user = user;
		this.coupon = coupon;
	}

	


    
	
	
}
