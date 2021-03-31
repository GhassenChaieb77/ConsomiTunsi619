package tn.esprit.spring.Entities;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;




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
	@Column(name="date")
	Date date; 
	@OneToOne(cascade = CascadeType.ALL ) //, orphanRemoval = true
	private Jackpot jackpot ; 
	@Column(name="don")
	private float don=10 ;
	@JsonIgnore 
	@OneToMany(mappedBy="event", 
			cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Donation> donations;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="event",fetch=FetchType.LAZY)
	private List<Publicity> publicities;
	
	
	public Event(){
		this.don=10;
	} 
	
	
	
	public Event(Long id , String name, String place, int participants, float don) {
		super();
		this.id = id ; 
		this.name = name;
		this.place = place;
		this.participants = participants;
		this.don=don; 
		
	} 
	
	
	public Event(Long id , String name, String place, int participants, String date) throws ParseException {
		super();
		this.id = id ; 
		this.name = name;
		this.place = place;
		this.participants = participants;
		this.date=new SimpleDateFormat("yyyy-MM-dd").parse(date); 
		
	}
	
	public Event(Long id , String name, String place, int participants, String date, float don) throws ParseException {
		super();
		this.id = id ; 
		this.name = name;
		this.place = place;
		this.participants = participants;
		this.date=new SimpleDateFormat("yyyy-MM-dd").parse(date); 
		this.don=don; 
	}

	
	//this.date=new SimpleDateFormat("yyyy-MM-dd").parse(date)

	public float getDon() {
		return don;
	}



	public void setDon(int don) {
		this.don = don;
	}



	public Event(Long id, String name, String place, int participants, Jackpot jackpot) {
		super();
		this.id = id;
		this.name = name;
		this.place = place;
		this.participants = participants;
		this.jackpot = jackpot;
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
	
	

    public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
	}



	public void ajouterParti(){
    	participants= participants +1 ; 
    }
	
	 public void ajouterDon(float s){
	    	don = don + s ; 
	    }
}


