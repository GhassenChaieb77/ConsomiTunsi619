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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity 
public class Event implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	int id;
	@Column(name="Name")
	String name ;
	@Column(name="Place")
	String place ;
	@Column(name="Participants_nbrs")
	int participants ;
	
	@OneToOne
	private Jackpot jackpot;
	
	@OneToMany(mappedBy="event", 
			cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Donation> donations = new ArrayList<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public int getParticipants() {
		return participants;
	}

	public void setParticipants(int participants) {
		this.participants = participants;
	}

	public Jackpot getJackpot() {
		return jackpot;
	}

	public void setJackpot(Jackpot jackpot) {
		this.jackpot = jackpot;
	}

	public List<Donation> getDonations() {
		return donations;
	}

	public void setDonations(List<Donation> donations) {
		this.donations = donations;
	}
	
	
    
}
