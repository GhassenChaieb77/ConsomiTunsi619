package tn.esprit.spring.Entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Supplier implements Serializable {
		@Id
		private Long id;
		
		
		private String name;
		
		@OneToMany(cascade = CascadeType.ALL)
		private List<Stock> stocks;
		
		
}
