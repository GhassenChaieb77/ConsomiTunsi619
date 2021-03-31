package tn.esprit.spring.Services;

import java.util.List;

import tn.esprit.spring.Entities.*;

public interface ICartService {
	
	
	
	
	public List<Product> getCartProducts(Long cart_id);
	public Cart AddOrderLinesToCart(Long LineId,Long CartId) ;
	public Cart RemoveOrderLinesFromCart(Long CartId) ;
	public Cart addCart();
	public Cart  getCartByUserId();
	public <List> Cart getAllOrderLinesByCart(Long cart_id);


	public List<Product> getCartProducts();




}
