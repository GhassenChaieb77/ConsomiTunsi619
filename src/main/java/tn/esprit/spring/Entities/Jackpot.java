package tn.esprit.spring.Entities;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;



@Entity
public class Jackpot implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id ;
	
	@Column(name="Amount")
	private float amount ;
	
	@Column(name="JackpotsNum")
	private int jackpotsNum ;
	
	@OneToOne(mappedBy="jackpot",cascade = CascadeType.ALL)
	private Event event;

	@ManyToMany	(mappedBy="jackpots", 
			cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private List<User> users;
	
	
	
	public Jackpot(float amount, int jackpotsNum, Event event, List<User> users) {
		super();
		this.amount = amount;
		this.jackpotsNum = jackpotsNum;
		this.event = event;
		this.users = users;
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
	
	public float getAmount() {
		return amount;
	}
	
	public void setAmount(float amount) {
		this.amount = amount;
	}
	
	public int getJackpotsNum() {
		return jackpotsNum;
	}
	
	public void setJackpotsNum(int jackpotsNum) {
		this.jackpotsNum = jackpotsNum;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	

}
