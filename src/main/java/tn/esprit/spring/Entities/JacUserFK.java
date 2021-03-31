package tn.esprit.spring.Entities;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;

@Embeddable
public class JacUserFK implements Serializable {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;

	private Long idUser;

	private Long idJackpot;
	
	

	public JacUserFK() {
		super();
	}

	public JacUserFK(Long idUser, Long idJackpot) {
		super();
		this.idUser = idUser;
		this.idJackpot = idJackpot;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public Long getIdJackpot() {
		return idJackpot;
	}

	public void setIdJackpot(Long idJackpot) {
		this.idJackpot = idJackpot;
	}
	
	
}
