package tn.esprit.spring.Entities;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Comment  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id ;
	
	@Column(name="Comment")
	String comment; 
	@Column(name="LikesComment")
	private int likesComment ;
	@Column(name="DislikesComment")
	private int dislikesComment;
	
	@Temporal(TemporalType.DATE)
	private Date date = new Date(System.currentTimeMillis());
	
	
	@ManyToOne
	private Subject subject;
	
	@ManyToOne
	private User user ;

	
	public Comment(String comment, int likesComment, int dislikesComment, Date date, Subject subject, User user) {
		super();
		this.comment = comment;
		this.likesComment = likesComment;
		this.dislikesComment = dislikesComment;
		this.date = date;
		this.subject = subject;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getLikesComment() {
		return likesComment;
	}

	public void setLikesComment(int likesComment) {
		this.likesComment = likesComment;
	}

	public int getDislikesComment() {
		return dislikesComment;
	}

	public void setDislikesComment(int dislikesComment) {
		this.dislikesComment = dislikesComment;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Comment(Long id, String comment, int likesComment, int dislikesComment, Date date, Subject subject,
			User user) {
		super();
		this.id = id;
		this.comment = comment;
		this.likesComment = likesComment;
		this.dislikesComment = dislikesComment;
		this.date = date;
		this.subject = subject;
		this.user = user;
	}
     
   
}
