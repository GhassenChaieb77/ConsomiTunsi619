package tn.esprit.spring.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.Entities.*;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
	
	@Query("Select "
				+ "DISTINCT o from Order o "
				+ "where o.user.id=:user_id ")
	 public List<Order> getOrderByUser(@Param("user_id") Long user_id)  ;
	
	
	@Query("Select "
			+ "DISTINCT o from Order o "
			+ "where o.cart.id=:cart_id ")
	public Order getOrderByCartId(@Param("cart_id")Long cart_id);
	
	 @Query("Select "
				+ "DISTINCT o from Order o "			 
				+ "where year(date)=:year ")
	 public List<Order> getOrderByYear(@Param("year")int year);
	 
	 @Query("Select "
				+ "DISTINCT o from Order o "			 
				+ "where month(date)=:month ")
	 public List<Order> getOrderByMonth(@Param("month")int month);
	 

	 
}
