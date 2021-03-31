package tn.esprit.spring.Entities;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Subject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id ;
	

	private String title ;

	private String content;

	


	private float likes=0;
	
	private float dislikes=0;
	
	private float rating=0;

	@Temporal(TemporalType.DATE)
	private Date date = new Date(System.currentTimeMillis());
	

	
	@ManyToOne
	private Product product;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.LAZY, mappedBy="subject")
	private List<Comment> comments;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}




	public float getLikes() {
		return likes;
	}

	public void setLikes(float likes) {
		this.likes = likes;
	}

	public float getDislikes() {
		return dislikes;
	}

	public void setDislikes(float dislikes) {
		this.dislikes = dislikes;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}



	public Subject() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Subject(Long id, String title, String content, float likes, float dislikes, float rating, Date date,
			Product product, List<Comment> comments) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.likes = likes;
		this.dislikes = dislikes;
		this.rating = rating;
		this.date = date;
		this.product = product;
		this.comments = comments;
	}



	

	
	
}
