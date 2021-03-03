package tn.esprit.spring.Services;

import org.springframework.beans.factory.annotation.Autowired;

import tn.esprit.spring.Entities.Cart;
import tn.esprit.spring.Entities.Order;
import tn.esprit.spring.Entities.Product;
import tn.esprit.spring.Repository.CartRepository;
import tn.esprit.spring.Repository.OrderRepository;
import tn.esprit.spring.Repository.ProductRepository;
import tn.esprit.spring.Repository.UserRepository;

public class OrderServiceImp implements IOrderService {
	
	@Autowired
	CartRepository cartRepository ;
	
	@Autowired
	ProductRepository productRepository; 
	
	@Autowired
	UserRepository userRepository; 
	
	@Autowired
	OrderRepository orderRepository;

	@Override
	public void saveOrder(long cart_id, long user_id, int quatity, float price, String paymentMethod) {
	
		
	}

	@Override
	public void deleteOrder(Order order, long user_id) {
		// TODO Auto-generated method stub
		
	}
	
	

}
