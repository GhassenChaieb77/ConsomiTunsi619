package tn.esprit.spring.Entities;

import java.io.Serializable;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class DeliveryAgent implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String firstname;
	
	private String lastname;
	
	private boolean isavailable;
	
	private Long phonenumber;
	
	private String position;
	
	private float traveledpath;

	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.LAZY, mappedBy="deliveryagent")
	private List<Order> orders;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public boolean isIsavailable() {
		return isavailable;
	}

	public void setIsavailable(boolean isavailable) {
		this.isavailable = isavailable;
	}

	public Long getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(Long phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public float getTraveledpath() {
		return traveledpath;
	}

	public void setTraveledpath(float traveledpath) {
		this.traveledpath = traveledpath;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public DeliveryAgent(String firstname, String lastname, boolean isavailable, Long phonenumber, String position,
			float traveledpath, List<Order> orders) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.isavailable = isavailable;
		this.phonenumber = phonenumber;
		this.position = position;
		this.traveledpath = traveledpath;
		this.orders = orders;
	}
	
			
	
}
