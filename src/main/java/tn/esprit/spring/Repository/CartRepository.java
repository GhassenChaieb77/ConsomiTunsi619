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

	
	/*//Get Items Total
	@Query("SELECT count(*) FROM Cart")
    public int countCartsItem();
	//Get Cart by user
	@Query("Select c from Cart c "
			+ "join c.Commande o "
			+ "join o.User u "
			+ "where u.id=:user_id") 
	public Cart getCartByUserId(@Param("user_id")Long user_id);
	
	//Get Cart by user
	@Modifying
	@Transactional
	@Query("update Cart set quantiy=:qty where id=:cart_id")
    void updateQuantity(@Param("qty")int quantity, @Param("cart_id")Long cart_id);
	
	//Delete Cart
	@Modifying
	@Transactional
	@Query("delete c from cart c "
			+ "join c.order o "
			+ "join o.user u "
			+ "where u.id=:user_id")
	public void removeAllCartByUser(@Param("user_id")Long user_id);
	
	//Delete CartItem
		@Modifying
		@Transactional
		@Query("delete c from cart c "
				+ "join c.order o "
				+ "join o.user u "
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
	
	
	
	 
	
}
