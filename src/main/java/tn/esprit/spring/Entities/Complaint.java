package tn.esprit.spring.Entities;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class Complaint implements Serializable {
	private Long id;

	private String type;
	
	private String content;
	
	

	public Complaint(Long id, String type, String content) {
		super();
		this.id = id;
		this.type = type;
		this.content = content;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
