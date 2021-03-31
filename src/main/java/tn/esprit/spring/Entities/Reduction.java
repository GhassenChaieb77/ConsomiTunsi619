package tn.esprit.spring.Entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Reduction implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ReductionKey reductionKey;
	

	private String name;

	private float sale;
	
	private float reduction;
	

	@ManyToOne(cascade = CascadeType.ALL)
	public Product product;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public float getSale() {
		return sale;
	}

	public void setSale(float sale) {
		this.sale = sale;
	}

	public float getReduction() {
		return reduction;
	}

	public void setReduction(float reduction) {
		this.reduction = reduction;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public ReductionKey getReductionKey() {
		return reductionKey;
	}

	public void setReductionKey(ReductionKey reductionKey) {
		this.reductionKey = reductionKey;
	}
	
	
	
}
