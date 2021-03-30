package tn.esprit.spring.Services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Entities.*;
import tn.esprit.spring.Repository.*;



@Service
public class CartService implements ICartService {

	@Autowired
	OrderLineRepository orderlineRepo ;
	@Autowired
	UserRepository userRepository ;
	
	@Autowired
	UserServiceImp userService ;
	
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	IOrderLineService OLService ;
	@Override
	public Cart AddOrderLinesToCart(Long LineId, Long CartId) {
		 Cart ca = cartRepository.findById(CartId).get();
		 OrderLine ordLine = orderlineRepo.findById(LineId).get();
		if (ca==null)
		{ 
			ca = new Cart();
		}
		else{
		 if(ca.getOrderLines() == null){
		
			List<OrderLine> orderLines = new ArrayList<>();
			orderLines.add(ordLine);
			ca.setOrderLines(orderLines);
			ca.setProdpricetotal(OLService.GetPriceTotal()) ;
			ordLine.setCart(ca);
		    
		 } 
		 
		else {
		   
			 ca.getOrderLines().add(ordLine);
			 
			 ca.setProdpricetotal(OLService.GetPriceTotal()) ;
			 ordLine.setCart(ca);
			    
			
			   
		   }
		}
		ca.setProdpricetotal(OLService.GetPriceTotal()) ;
        orderlineRepo.save(ordLine);
		cartRepository.save(ca);
		
		return ca; 
	}
	
	public void updateTotalPrice(Long user_id)
	{   User user = userRepository.findById(user_id).get();
		Cart ca = cartRepository.findById(user.getCart().getId()).get();
		ca.setProdpricetotal(OLService.GetPriceTotal()) ;
   
		cartRepository.save(ca);
	}
	


	@Override
	public List<Product> getCartProducts(Long cart_id) {
		// TODO Auto-generated method stub
		return cartRepository.getCartProducts(cart_id);
	}


	
	@Override
	public Cart RemoveOrderLinesFromCart(Long CartId) {
		 Cart cart = cartRepository.findById(CartId).get();
		    if (cart == null) {
		    	cart = new Cart();
		    } 
		    List<OrderLine> listOL= new ArrayList<OrderLine>() ;
		    listOL=cart.getOrderLines();
		    int Olsize= cart.getOrderLines().size();
		    cart.setOrderLines(null);
		    cart.setProdpricetotal(0);
		   // cartRepository.delete(cart);
		   // Long CurrentUserId= cart.getUser().getId();
		   // cart = addCart(CurrentUserId);
		    cartRepository.save(cart);
		return cart ;
	}





	@Override
	public Cart addCart() {
		// TODO Auto-generated method stub
		User user = userService.getUserInfo();
		Cart c = new Cart() ;
		c.setUser(user);
		cartRepository.save(c) ;
		return c ;
	}








	@Override
	public Cart getCartByUserId(Long user_id) {
		// TODO Auto-generated method stub
		updateTotalPrice(user_id);
		return cartRepository.getCartByUserId(user_id) ;
	}





	@Override
	public <List> Cart getAllOrderLinesByCart(Long cart_id) {
		// TODO Auto-generated method stub
		
		return cartRepository.getAllOrderLinesByCart(cart_id);
		
	}

		
	@Override
	public List<Product> getCartProducts() {

		List<Product> p = new ArrayList<Product>();
		List<OrderLine> l=cartRepository.getOrderlines();
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



}
