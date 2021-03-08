package tn.esprit.spring.Entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Publicity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	public Long id;
	

	public String name;
	
	@Temporal (TemporalType.DATE)
	public Date startDate;
	
	@Temporal (TemporalType.DATE)
	public Date endDate;
	

	public String type;
	

	public int finalViews;
	

	public int age;
	

	public String sex;
	

	public String saison;
	
	@ManyToOne
	Product product;
	
	@ManyToOne
	private Event event;
	
	public Publicity(){}
	
	public Publicity(String name, Date startDate, Date endDate, String type,int age, String sex,
			String saison) {
		super();
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.type = type;
		this.age = age;
		this.sex = sex;
		this.saison = saison;
	}
	
	public Publicity(String name, Date startDate, Date endDate, String type, int finalViews, int age, String sex,
			String saison, Product product, Event event) {
		super();
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.type = type;
		this.finalViews = finalViews;
		this.age = age;
		this.sex = sex;
		this.saison = saison;
		this.product = product;
		this.event = event;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getFinalViews() {
		return finalViews;
	}
	public void setFinalViews(int finalViews) {
		this.finalViews = finalViews;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getSaison() {
		return saison;
	}
	public void setSaison(String saison) {
		this.saison = saison;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
	
	

}
