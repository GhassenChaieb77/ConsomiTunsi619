package tn.esprit.spring.Entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SaveSearch implements Serializable{
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id ;
	long userid;
	String titlesub;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public String getTitlesub() {
		return titlesub;
	}
	public void setTitlesub(String titlesub) {
		this.titlesub = titlesub;
	}
	public SaveSearch(long id, long userid, String titlesub) {
		super();
		this.id = id;
		this.userid = userid;
		this.titlesub = titlesub;
	}
	public SaveSearch() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
