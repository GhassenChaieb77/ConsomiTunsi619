package tn.esprit.spring.Entities;

import java.util.Date;

public class Comment {
	public int idcomment;
	public String comment;
	public String Content;
	public int likescomment ;
	public int dislikescomment;
	public Date date;
	public int getIdcomment() {
		return idcomment;
	}
	public void setIdcomment(int idcomment) {
		this.idcomment = idcomment;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public int getLikescomment() {
		return likescomment;
	}
	public void setLikescomment(int likescomment) {
		this.likescomment = likescomment;
	}
	public int getDislikescomment() {
		return dislikescomment;
	}
	public void setDislikescomment(int dislikescomment) {
		this.dislikescomment = dislikescomment;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Comment(int idcomment, String comment, String content, int likescomment, int dislikescomment, Date date) {
		super();
		this.idcomment = idcomment;
		this.comment = comment;
		Content = content;
		this.likescomment = likescomment;
		this.dislikescomment = dislikescomment;
		this.date = date;
	}
	
}
