package tn.esprit.spring.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.Entities.*;

@Repository
public interface CartRepository extends CrudRepository<Cart, Long>  {

	
	//Get Items Total
	@Query("SELECT count(*) FROM Cart")
    public Long countCartsItem();
	//Get Cart by user
	 @Query("Select "
				+ "DISTINCT c from Cart c "
				+ "where c.user.id=:user_id ")
	public Cart getCartByUserId(@Param("user_id") Long user_id);	
	/*//Get Cart by user
	@Modifying
	@Transactional
	@Query("update Cart set quantiy=:qty where id=:cart_id")
    void updateQuantity(@Param("qty")int quantity, @Param("cart_id")Long cart_id);
	
	//Delete Cart
	@Modifying
	@Transactional
	@Query("delete c from Cart c "
			+ "join c.User u "
			+ "where u.id=:user_id")
	public void removeAllCartByUser(@Param("user_id")Long user_id);
	
	//Delete CartItem
		@Modifying
		@Transactional
		@Query("delete c from Cart c "
				+ "join c.Cart_products cp"
				+ "join c.User u "
				+ "where c.id=:cart_id"
				+ "and u.id=:user_id")
		public void removeCartItemByUser(@Param("user_id")Long user_id,@Param("cart_id")Long cart_id);

	//get Product Total Price
	@Query("Select sum(p.price*c.quatity) as total from Cart c "
			+ "join c.Product p "
			+ "where p.Cart_id=:cart_id"
			+ "and p.id=:prod_id")	         
	public List<Cart> getProductTotalPrice(@Param("cart_id")Long cart_id,@Param("prod_id")Long prod_id);
	*/
// List Product of a cart

	
	 @Query("Select "
				+ "DISTINCT p from OrderLine p "
				+ "join p.cart c ")
	public List<OrderLine> getOrderlines();
	 
	 @Query("Select DISTINCT p from Order p ")
	public List<Order> getOrders();
	 	 
	 
	 @Query("select DISTINCT c from Cart c ")
	public List<Cart> getProducts();
	  
	 
	 @Query("SELECT count(p.product.id) FROM OrderLine p "
	 		+ "where p.product.name=:name ")
	 public int countusers(@Param("name")String name);
	 
	 


	
}
