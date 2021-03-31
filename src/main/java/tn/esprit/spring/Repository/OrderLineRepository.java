package tn.esprit.spring.Repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.Entities.*;


@Repository
public interface OrderLineRepository extends CrudRepository<OrderLine, Long> {
	
	
	@Modifying
    @Transactional
    @Query("UPDATE OrderLine ol SET ol.quantity=:qty where ol.id=:idO")
		public void UpdateQuantity(@Param("qty")int quantity, @Param("idO")Long id) ;
	
	 @Query("Select "
				+ "DISTINCT ol from OrderLine ol "
				+ "where ol.product.id=:id ")
	 	public OrderLine  GetOrderLineByProduct(@Param("id") Long id);
	 
	 
	 @Query("Select "
				+ "DISTINCT ol.product from OrderLine ol "	
				+ "join Product p "
				+ "on ol.id=:id ")
	 	public <List>Product  GetProductByOrderLine(@Param("id") Long id);
	 
	   
	 @Query("Select "
				+ "sum(ol.quantity*ol.product.price) from OrderLine ol "
				+ "where ol.product.id=:id " 
				+ "and ol.id=:OrdLId ")
		public float GetOrderLinePrice(@Param("OrdLId")Long OrdLId ,@Param("id") Long Proid);
	
	 @Query("Select "
				+ "sum(ol.quantity*ol.product.price) from OrderLine ol ")
	 public float GetTotaPrice();
	 
	 @Query("Select count(*) from OrderLine ol ") 
		public int GetTotalQuantiy();
	
	 @Query("Select "
				+ "ol from OrderLine ol "
			    + "join Cart c "
				+ "on c.id=:cart_id ")
	 
	 public <List> OrderLine GetOrderLinesByCart(@Param("cart_id")Long cart_id) ;
}
