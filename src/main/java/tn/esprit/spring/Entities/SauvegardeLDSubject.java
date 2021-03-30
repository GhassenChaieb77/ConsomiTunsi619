package tn.esprit.spring.Entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SauvegardeLDSubject implements Serializable{
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	long  id;
	
	long userLDsubject;
	long usersubject;
	
	public SauvegardeLDSubject() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SauvegardeLDSubject(long id, long userLDsubject, long usersubject) {
		super();
		this.id = id;
		this.userLDsubject = userLDsubject;
		this.usersubject = usersubject;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserLDsubject() {
		return userLDsubject;
	}

	public void setUserLDsubject(long userLDsubject) {
		this.userLDsubject = userLDsubject;
	}

	public long getUsersubject() {
		return usersubject;
	}

	public void setUsersubject(long usersubject) {
		this.usersubject = usersubject;
	}

	

	



	



	
	
}
