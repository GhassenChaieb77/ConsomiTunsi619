package tn.esprit.spring.Entities;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;



@Entity

public class JackpotUser implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private JacUserFK jacuserfk ; 
	
	@ManyToOne
	@JoinColumn(name = "idUser", referencedColumnName = "user_id", insertable = false, updatable = false)
	private User user;

	@ManyToOne
	@JoinColumn(name = "idJackpot", referencedColumnName = "id", insertable = false, updatable = false)
	private Jackpot jackpot;

	

	public JackpotUser() {
		super();
	}

	public JackpotUser(JacUserFK jacuserfk, User user, Jackpot jackpot) {
		super();
		this.jacuserfk = jacuserfk;
		this.user = user;
		this.jackpot = jackpot;
	}

	public Jackpot getJackpot() {
		return jackpot;
	}

	public void setJackpot(Jackpot jackpot) {
		this.jackpot = jackpot;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
