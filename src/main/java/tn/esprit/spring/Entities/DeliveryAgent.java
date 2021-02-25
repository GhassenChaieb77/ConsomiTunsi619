package tn.esprit.spring.Entities;

import java.io.Serializable;

import javax.persistence.Entity;
@Entity
public class DeliveryAgent implements Serializable {
	private Long id;
	
	private String firstname;
	
	private String lastname;
	
	private boolean isavailable;
	
	private Long phonenumber;
	
	private String position;
	
	private float traveledpath;

	public DeliveryAgent(Long id, String firstname, String lastname, boolean isavailable, Long phonenumber,
			String position, float traveledpath) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.isavailable = isavailable;
		this.phonenumber = phonenumber;
		this.position = position;
		this.traveledpath = traveledpath;
	}

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
	
}
