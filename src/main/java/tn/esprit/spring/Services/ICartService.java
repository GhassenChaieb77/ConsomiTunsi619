package tn.esprit.spring.Services;

import java.util.List;



import tn.esprit.spring.Entities.*;

public interface ICartService {
	
	/*public void addToCart(long cart_id,long product_id,int quatity);
	public Cart getCartByUserId(long user_id);
	public List<Product> getCartProducts(long user_id);
	//public void removeAllCartByUser(long user_id);
	//public void removeCartItemByUser(Long user_id,Long cart_id);
	public int countCartsItem();
	public void updateQuantity(int quantity,Long cart_id);
	public List<Cart> getProductTotalPrice(Long cart_id,Long prod_id) ;
	
   */
	public List<Product> getCartProducts();
	public List<User> getUsers();
}
