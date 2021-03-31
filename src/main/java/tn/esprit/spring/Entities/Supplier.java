package tn.esprit.spring.Entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;

@Entity
public class Supplier implements Serializable {
		@Id
		private Long id;
		
		@GeneratedValue (strategy = GenerationType.IDENTITY)
		private String name;
		
		@Column(name="Email",nullable = false,unique = true)
		@Email
	    private String email;
		
		@ManyToMany(cascade = CascadeType.ALL)
		private List<Stock> stocks;

		
public Supplier() {
			
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public List<Stock> getStocks() {
			return stocks;
		}

		public void setStocks(List<Stock> stocks) {
			this.stocks = stocks;
		}

		public Supplier(String name, @Email String email) {
			super();
			this.name = name;
			this.email = email;
		}

		
		
		
}
