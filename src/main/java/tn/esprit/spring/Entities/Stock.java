package tn.esprit.spring.Entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
	
	@Entity
	@Table(name="Stock")
	public class Stock implements Serializable {
			
		private static final long serialVersionUID = 4659010424724882893L;
		@Id
		@GeneratedValue (strategy = GenerationType.IDENTITY)
		@Column(name="Stock_id")
		private Long id;
		@Column(name="EntryQt")
	    private int EntryQt;
		@Column(name="OutletQt")
	    private int OutletQt;
		@Column(name="PurchasePrice")
	    private float PurchasePrice;
		@Temporal (TemporalType.DATE)
		private Date OperationDate;
		@Temporal (TemporalType.DATE)
		private Date PurchaseDate;
		
		
		
		@ManyToMany(cascade = CascadeType.ALL)
		private List<Product> products;



		


		public Stock(int entryQt, int outletQt, float purchasePrice, Date operationDate, Date purchaseDate,
				List<Product> products) {
			super();
			this.EntryQt = entryQt;
			this.OutletQt = outletQt;
			this.PurchasePrice = purchasePrice;
			this.OperationDate = operationDate;
			this.PurchaseDate = purchaseDate;
			this.products = products;
		}



		public Long getId() {
			return id;
		}



		public void setId(Long id) {
			this.id = id;
		}



		public int getEntryQt() {
			return EntryQt;
		}



		public void setEntryQt(int entryQt) {
			EntryQt = entryQt;
		}



		public int getOutletQt() {
			return OutletQt;
		}



		public void setOutletQt(int outletQt) {
			OutletQt = outletQt;
		}



		public float getPurchasePrice() {
			return PurchasePrice;
		}



		public void setPurchasePrice(float purchasePrice) {
			PurchasePrice = purchasePrice;
		}



		public Date getOperationDate() {
			return OperationDate;
		}



		public void setOperationDate(Date operationDate) {
			OperationDate = operationDate;
		}



		public Date getPurchaseDate() {
			return PurchaseDate;
		}



		public void setPurchaseDate(Date purchaseDate) {
			PurchaseDate = purchaseDate;
		}



		public List<Product> getProducts() {
			return products;
		}



		public void setProducts(List<Product> products) {
			this.products = products;
		}
		
		
				
		
		
	
}
