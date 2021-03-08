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
	public Product  GetProductByOrderLine(@Param("id") Long id);
}
