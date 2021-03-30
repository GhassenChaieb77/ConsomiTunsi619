package tn.esprit.spring.Services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.type.LocalDateTimeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Entities.Cart;
import tn.esprit.spring.Entities.OrderLine;
import tn.esprit.spring.Entities.Product;
import tn.esprit.spring.Entities.Publicity;
import tn.esprit.spring.Entities.User;
import tn.esprit.spring.Repository.CartRepository;
import tn.esprit.spring.Repository.OrderRepository;
import tn.esprit.spring.Repository.ProductRepository;
import tn.esprit.spring.Repository.UserRepository;



//@Service
public class CartServiceImp {

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
*/
	
/*	
	@Autowired
	CartRepository cartRepo;  
	
	@Override
	public List<Product> getCartProducts() {

		List<Product> p = new ArrayList<Product>();
		List<OrderLine> l=cartRepo.getOrderlines();
	    int nb= l.size();
		for (int i=0;i<nb;i++)
		{
			
			List<Publicity> pub =l.get(i).getProduct().getPublicities();
			int nombre=pub.size();
			
			for(int k=0;k<nombre;k++)
			{
				 Date d=l.get(i).getCart().getUser().getDate(); 
				 int year=d.getYear()+1900;  
			
				if(pub.get(k).getSex().equals(l.get(i).getCart().getUser().getGender()) && pub.get(k).getAge()==(2021-year))
				{				
						if(p.contains(pub.get(k).getProduct()))
						{
							p.remove(pub.get(k).getProduct());
						}								
						p.add(l.get(i).getProduct());          						
				}		
			}
		}	
		return p;	
	} 

	
	@Override
	public List<User> getUsers() {

		List<User> p = new ArrayList<User>();
		List<OrderLine> l=cartRepo.getOrderlines();
		int nb= l.size();
		for (int i=0;i<nb;i++)
		{
			p.add(l.get(i).getCart().getUser());
		}
		return p;
}*/
}
