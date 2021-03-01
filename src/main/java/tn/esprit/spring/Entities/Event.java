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
	private Long id;
	@Column(name="Name")
	private String name ;
	@Column(name="Place")
	private String place ;
	@Column(name="Participants_nbrs")
	int participants ;
	
	@OneToOne
	private Jackpot jackpot;
	
	@OneToMany(mappedBy="event", 
			cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Donation> donations;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="event",fetch=FetchType.EAGER)
	private List<Publicity> publicities;
	
	
	public Event(String name, String place, int participants, Jackpot jackpot, List<Donation> donations,
			List<Publicity> publicities) {
		super();
		this.name = name;
		this.place = place;
		this.participants = participants;
		this.jackpot = jackpot;
		this.donations = donations;
		this.publicities = publicities;
	}

	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
	
	public List<Publicity> getPublicities() {
		return publicities;
	}

	public void setPublicities(List<Publicity> publicities) {
		this.publicities = publicities;
	}

    
}
