package tn.esprit.spring.Entities;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
public class Rating implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "rating")
    private int rating;
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    @ManyToOne()
    private Subject subject;
	public Rating(Long id, User user, Subject subject, int rating) {
		super();
		this.id = id;
		this.user = user;
		this.subject = subject;
		this.rating = rating;
	}

<<<<<<< HEAD
	public Rating(int rating, User user, Subject subject) {
		super();
		this.rating = rating;
		this.user = user;
		this.subject = subject;
	}

=======
>>>>>>> 3d9d8420ff92872147ddccc45a9078ed9f78bbb3
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Rating() {
		super();
		// TODO Auto-generated constructor stub
	}
}

