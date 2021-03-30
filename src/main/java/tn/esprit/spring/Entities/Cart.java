package tn.esprit.spring.Entities;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Cart implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	


	
	private float prodpricetotal;
	
	
	@JsonIgnore
	@OneToOne
	private User user;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER,mappedBy="cart", orphanRemoval=true)
    private List<OrderLine> OrderLines ;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public float getProdpricetotal() {
		return prodpricetotal;
	}

	public void setProdpricetotal(float prodpricetotal) {
		this.prodpricetotal = prodpricetotal;
	}

	


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

    

	public List<OrderLine> getOrderLines() {
		return OrderLines;
	}

	public void setOrderLines(List<OrderLine> orderLines) {
		//OrderLines = orderLines;
	    this.OrderLines.clear();
		 if (orderLines != null) {
		        this.OrderLines.addAll(orderLines);
		    }
	}
	
	

	public Cart(Long id, float prodpricetotal,  User user, List<OrderLine> orderLines) {
		super();
		this.id = id;
		this.prodpricetotal = prodpricetotal;
	
		this.user = user;
		OrderLines = orderLines;
	}

	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}
 


}
