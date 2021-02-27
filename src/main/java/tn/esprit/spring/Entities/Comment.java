package tn.esprit.spring.Entities;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class Comment  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	int id ;
	
	@Column(name="Comment")
	String comment; 
	@Column(name="LikesComment")
	int likesComment ;
	@Column(name="DislikesComment")
	int dislikesComment;
	
	@Temporal(TemporalType.DATE)
	Date date;

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
     

}
