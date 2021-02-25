package tn.esprit.spring.Entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


public class Subject {

	public int idsubject;
	public String title;
	public String Content;
	public int rating;
	public int likes ;
	public int dislikes;
	public Date date;
	public int getIdsubject() {
		return idsubject;
	}
	public void setIdsubject(int idsubject) {
		this.idsubject = idsubject;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public int getDislikes() {
		return dislikes;
	}
	public void setDislikes(int dislikes) {
		this.dislikes = dislikes;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Subject(int idsubject, String title, String content, int rating, int likes, int dislikes, Date date) {
		super();
		this.idsubject = idsubject;
		this.title = title;
		Content = content;
		this.rating = rating;
		this.likes = likes;
		this.dislikes = dislikes;
		this.date = date;
	}
	public Subject() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
