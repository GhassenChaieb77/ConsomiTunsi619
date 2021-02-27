package tn.esprit.spring.Entities;


import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;



@Entity
public class Jackpot implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	int id ;
	
	@Column(name="Amount")
	float amount ;
	
	@Column(name="JackpotsNum")
	int jackpotsNum ;
	
	@OneToOne(mappedBy="jackpot",cascade = CascadeType.ALL)
	private Event event;

	
	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
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
		jackpotsNum = jackpotsNum;
	}
	
}
