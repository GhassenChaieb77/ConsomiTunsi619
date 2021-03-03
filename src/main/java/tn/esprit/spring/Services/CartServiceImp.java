package tn.esprit.spring.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Entities.Cart;
import tn.esprit.spring.Entities.Product;
import tn.esprit.spring.Repository.CartRepository;
import tn.esprit.spring.Repository.OrderRepository;
import tn.esprit.spring.Repository.ProductRepository;
import tn.esprit.spring.Repository.UserRepository;



@Service
public class CartServiceImp implements ICartService {

/*	@Autowired
	CartRepository cartRepository ;
	
	@Autowired
	ProductRepository productRepository; 
	
	@Autowired
	UserRepository userRepository; 
	
	@Autowired
	OrderRepository orderRepository;

	@Override
	public void addToCart(long cart_id, long product_id, int quatity) {
        
		int addedQt = quatity;
	    Product productManagedEntity = productRepository.findById(product_id).get();
		Cart cartManagedEntity = cartRepository.findById(cart_id).get();


		if(cartManagedEntity.getProducts() == null){
			
			List<Product> products = new ArrayList<>();
			products.add(productManagedEntity);
			cartManagedEntity.setQuantity(quatity);
			cartManagedEntity.setProducts(products);
		}else{
			
			addedQt= cartManagedEntity.getQuantity()+quatity ;
			cartManagedEntity.setQuantity(addedQt);
			cartManagedEntity.getProducts().add(productManagedEntity);
		}


		cartRepository.save(cartManagedEntity); 

		
	}

	@Override
	public Cart getCartByUserId(long user_id) {
		// TODO Auto-generated method stub
		
		return cartRepository.getCartByUserId(user_id) ;
	}

	/*@Override
	public void removeAllCartByUser(long user_id) {
		  cartRepository.removeAllCartByUser(user_id);
		
	}

	@Override
	public int countCartsItem() {
		// TODO Auto-generated method stub
		return cartRepository.countCartsItem();
	}

	@Override
	public void updateQuantity(int quantity, Long cart_id) {
		cartRepository.updateQuantity(quantity, cart_id);
		
	}

	@Override
	public List<Cart> getProductTotalPrice(Long cart_id, Long prod_id) {
		// TODO Auto-generated method stub
		return cartRepository.getProductTotalPrice(cart_id, prod_id);
	}

@Override
	public void removeCartItemByUser(Long user_id, Long cart_id) {
		//cartRepository.removeCartItemByUser(user_id, cart_id);
		
	}

	@Override
	public List<Product> getCartProducts(long user_id) {
		// TODO Auto-generated method stub
		Cart UserCart = cartRepository.getCartByUserId(user_id) ;
		return UserCart.getProducts();
	} 
*/
}
