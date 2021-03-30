package tn.esprit.spring.Entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SauvegardeLDComment implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	long  id;
	
	long userLDcomment;
	long usercomment;
	
	
	
	public SauvegardeLDComment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SauvegardeLDComment(long id, long userLDcomment, long usercomment) {
		super();
		this.id = id;
		this.userLDcomment = userLDcomment;
		this.usercomment = usercomment;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getUserLDcomment() {
		return userLDcomment;
	}
	public void setUserLDcomment(long userLDcomment) {
		this.userLDcomment = userLDcomment;
	}
	public long getUsercomment() {
		return usercomment;
	}
	public void setUsercomment(long usercomment) {
		this.usercomment = usercomment;
	}
	
	
}
