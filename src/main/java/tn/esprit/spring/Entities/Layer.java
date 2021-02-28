package tn.esprit.spring.Entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Layer")
public class Layer implements Serializable{
	
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="USER_ID")
	private Long id;
	
	@Column(name="capacity")
	private int capacity;
	
	@OneToOne(mappedBy="layer")
	private Category Category;
	
	public Layer(int capacity, tn.esprit.spring.Entities.Category category) {
		super();
		this.capacity = capacity;
		Category = category;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public Category getCategory() {
		return Category;
	}

	public void setCategory(Category category) {
		Category = category;
	}
	
	
	
	
	
	
}
