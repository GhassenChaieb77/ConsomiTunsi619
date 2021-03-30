package tn.esprit.spring.Services;

import java.text.DateFormat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.TimeUnit;

import javax.transaction.Transactional;
import javax.xml.datatype.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Entities.*;
import tn.esprit.spring.Repository.BillRepository;
import tn.esprit.spring.Repository.CartRepository;
import tn.esprit.spring.Repository.CouponRepository;
import tn.esprit.spring.Repository.OrderLineRepository;
import tn.esprit.spring.Repository.OrderRepository;
import tn.esprit.spring.Repository.UserRepository;

@Service
public class OrderService implements IOrderService {

	@Autowired
	UserRepository userRepository ;
	@Autowired
	BillRepository billRepository ;
	@Autowired
	OrderRepository orderRepo ;

	@Autowired
	OrderLineRepository orderlineRepo ;
	
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	CouponRepository couponRepository;
	
	@Autowired
	IBillService ibillService;
	
	@Autowired
	IOrderLineService OLService ;
	
	@Autowired
	ICartService icartservice;
	//PaymentMethod pm = null ;
	@Override
	@Transactional
	public Order CreateOrder(Long user_id,String Adresse,String methode) {
		

	
		Cart cart = cartRepository.getCartByUserId(user_id);
		//Order or = orderRepo.getOrderByUser(user_id);
		User user = userRepository.findById(user_id).get();
		
		  Order order = new Order();
		// TODO Auto-generated method stub
		Calendar calendar = Calendar.getInstance();
		Date date= new Date();

		 order.setDate(date);
		 order.setAddress(Adresse);
		 order.setLastName(cart.getUser().getLastName());
		 order.setFirstName(cart.getUser().getFirstName()); 
		 order.setPhoneNumber(cart.getUser().getTelephone());
		 order.setTotalprice(cart.getProdpricetotal());  
		 order.setPaymentmethod(methode);
         order.setCart(cart);
         order.setUser(user);

		 cart.setProdpricetotal(OLService.GetPriceTotal());
		 cartRepository.save(cart) ;
		 orderRepo.save(order) ;   
		
		
		 System.out.println(cart.getOrderLines().size());
		 return order  ;
		
	
	}
	



	@Override
	public List<Order> getOrdersByUser(Long user_id) {
		// TODO Auto-generated method stub
		return orderRepo.getOrderByUser(user_id)	;			}



	@Override
	public Order checkoutOff(Long user_id,Long order_id) {
		// TODO Auto-generated method stub
		Cart cart = cartRepository.getCartByUserId(user_id);
		Order or = orderRepo.findById(order_id).get();
		float priceOrig = or.getTotalprice();
		  
		//	float UserAmount = us.getBalance() ;
			
			  int QtFinal = 0;
				 
		if(or.getPaymentmethod().equals("offline"))
		{       
			
			 if(or.getBill()==null){
			     Bill bill= ibillService.CreateAndAffectBillToOrder(or.getId(),user_id);
			   		for (int i = 0; i < cart.getOrderLines().size(); i++) {
			   		    QtFinal  =+or.getCart().getOrderLines().get(i).getQuantity();
			   		   
			   		}
			   			bill.setQuantity(QtFinal );
			   	    	bill.setTotal(priceOrig);
			   	
			         billRepository.save(bill);
					 or.setBill(bill);
			}
			 orderRepo.save(or);
			 Long CurrentCartId= cart.getId();	
		     icartservice.RemoveOrderLinesFromCart(CurrentCartId);
			 or.setCart(null);
			 orderRepo.save(or);;
		}
		else {
			
			
		}
	return or;
	
	}



	@Override
	public List<Order> getOrdersByYear(int year) {
		// TODO Auto-generated method stub
		return orderRepo.getOrderByYear(year);
	}



	@Override
	public List<Order> getOrdersByMonth(int month) {
		// TODO Auto-generated method stub
		return orderRepo.getOrderByMonth(month);
	}


	@Override
	public Order AddOrderLine(Long user_id) {
		// TODO Auto-generated method stub
		Cart cart = cartRepository.getCartByUserId(user_id);
	//	OrderLine ol = orderlineRepo.findAllById(arg0)
		Order or = orderRepo.findById(user_id).get();
		return or;
	}





	@Override
	public Order getOrderByCartId(Long cart_id) {
		// TODO Auto-generated method stub
		return orderRepo.getOrderByCartId(cart_id);
	}




	@Override
	public void checkoutOnline(Long user_id,Long order_id,float amount) {
		// TODO Auto-generated method stub
		Cart cart = cartRepository.getCartByUserId(user_id);
		Order or = orderRepo.findById(order_id).get();	
		User us= userRepository.findById(user_id).get();
		float priceOrig = or.getTotalprice();
		  
	//	float UserAmount = us.getBalance() ;
		if(or.getPaymentmethod().equals("online"))
		{ 
		  int QtFinal = 0;
			 
			  if(amount==or.getTotalprice() && or.getTotalprice()!=0)
			  {
			     us.setBalance(us.getBalance()-amount);
			     userRepository.save(us);
			     if(or.getBill()==null){
				     Bill bill= ibillService.CreateAndAffectBillToOrder(or.getId(),user_id);
				   		for (int i = 0; i < cart.getOrderLines().size(); i++) {
				   		    QtFinal  =+or.getCart().getOrderLines().get(i).getQuantity();
				   		   
				   		}
				   			bill.setQuantity(QtFinal );
				   	    	bill.setTotal(priceOrig);
				   	
				         billRepository.save(bill);
						 or.setBill(bill);
				}
				 orderRepo.save(or);
				 Long CurrentCartId= cart.getId();	
			     icartservice.RemoveOrderLinesFromCart(CurrentCartId);
				 or.setCart(null);
				 orderRepo.save(or);
					
			      }
			  
		      else if (amount< or.getTotalprice() && or.getTotalprice()!=0) {
				  float RestPayment= priceOrig-amount ;
				  or.setTotalprice(RestPayment); 
				  orderRepo.save(or);
				  us.setBalance(us.getBalance()-amount);
				  userRepository.save(us);
				  System.out.println("You still have to pay"+ RestPayment);
				  checkoutOnline(user_id,order_id,amount);
				
		    	  }
			  else
				  if (amount> or.getTotalprice()&& or.getTotalprice()!=0){
					  Bill bill= ibillService.CreateAndAffectBillToOrder(or.getId(),user_id);
				    float RestAmount= amount-priceOrig ;
			     	us.setBalance((us.getBalance()-amount)+RestAmount);
				    userRepository.save(us);
				    if(or.getBill()==null){
				   		for (int i = 0; i < cart.getOrderLines().size(); i++) {
				   		    QtFinal  =+or.getCart().getOrderLines().get(i).getQuantity();
				   		   
				   		}
				   			bill.setQuantity(QtFinal );
				   	    	bill.setTotal(priceOrig);
				   	
				           billRepository.save(bill);
						 or.setBill(bill); 
				    }
					 orderRepo.save(or);
					 Long CurrentCartId= cart.getId();	
				     icartservice.RemoveOrderLinesFromCart(CurrentCartId);
					 or.setCart(null);
					 orderRepo.save(or);;
			      }		
		}
		
	
	}

///////////////////////////////////////////////////////////////////////////////////

   // coupons Management

	
	//get coupons 
	@Override
	public String getCouponCode(Long user_id) {
		// TODO Auto-generated method stub
		List<Order> UserOrders = orderRepo.getOrderByUser(user_id);
		List<Coupon> coupons = new ArrayList<>();
		couponRepository.findAll().forEach(coupons::add);
		Date today = new Date(System.currentTimeMillis());
		Long diffInDays=null;

		String code = null ;
		//String code=couponRepository.getCodeCouponByPercentage(code);
		int count=0;
            for (Order or : UserOrders) 
            {		
				Date orderDay = or.getDate() ;
				Long diffInMillies= Math.abs(today.getTime()- orderDay.getTime());	
				diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
				if(diffInDays <7 )	{	
					count++;	
					for (Coupon coupon : coupons) {
						if(coupon.isValid()){
			            if (coupon.getPercentage()==20 && count>=3 && count< 5){  	
			            	 code = coupon.getCode();
			            }
			            else if(coupon.getPercentage()==50 && count>=5) {
			            	  code = coupon.getCode();}
			         
					}}
				
                 }
            }
        return code;
	
	}


   
	
    //Prepare discounts
	@Override
	public Order CouponDiscount(Long user_id,Long order_id) {
		// TODO Auto-generated method stub
	 List<Order> UserOrders = orderRepo.getOrderByUser(user_id);
	 
	 final Iterator<Order> itr = UserOrders.iterator();
	    //synchronized (UserOrders) {
	    Order lastElement=orderRepo.findById(order_id).get();
	    while(itr.hasNext()) {
	        lastElement =  itr.next();
	    }
	 Order or=  lastElement ;
	 List<Coupon> coupons = new ArrayList<>();
	 couponRepository.findAll().forEach(coupons::add);
	 if (getCouponCode(user_id)!=null)
	 { if (getCouponCode(user_id).equals("promo50")){
			
				float discount = (float) (or.getTotalprice()*0.5);
				float NewPrice= or.getTotalprice()-discount ;
				or.setTotalprice(NewPrice);
				for (Coupon coupon : coupons) {
					if (coupon.getCode().equals("promo50") &&coupon.isValid())
			 	       or.setCoupon(coupon);
					   or.setTotalprice(NewPrice);
				}
				orderRepo.save(or);
				
			}
		 else if (getCouponCode(user_id).equals("promo20")){
				
				float discount = (float) (or.getTotalprice()*0.2);
				float NewPrice= or.getTotalprice()-discount ;
				or.setTotalprice(NewPrice);
				for (Coupon coupon : coupons) {
					if (coupon.getCode().equals("promo20") &&coupon.isValid())
			 	       or.setCoupon(coupon);
					   or.setTotalprice(NewPrice);
				}
				orderRepo.save(or);
		 }}
	 return or ;
	}
	
	
	public Order getLastElement(final List<Order> UserOrders) {
	    final Iterator<Order> itr = UserOrders.iterator();
	    //synchronized (UserOrders) {
	    Order lastElement=null ;
	    while(itr.hasNext()) {
	        lastElement =  itr.next();
	    }
	    return lastElement; 
	
	}




	@Override
	public void checkoutOnlinewithCoupon(Long user_id, Long order_id, float amount) {
		// TODO Auto-generated method stub
		Cart cart = cartRepository.getCartByUserId(user_id);
		Order or = orderRepo.findById(order_id).get();	
		User us= userRepository.findById(user_id).get();
		float UserAmount = us.getBalance() ;
		Coupon c = couponRepository.findById(or.getCoupon().getId()).get();
		List<Coupon> coupons = new ArrayList<>();
		couponRepository.findAll().forEach(coupons::add);
		for (Coupon cp : coupons) {
				//CouponPay(user_id, order_id, amount);}
		
		//Coupon cp = couponRepository.findById(or.getCoupon().getId()).get();
		if(cp.isValid()&& getCouponCode(user_id)!=null) {
			CouponDiscount(user_id,order_id);
			 
			  if(amount==or.getTotalprice()&& or.getTotalprice()!=0)
			  {
			     us.setBalance(us.getBalance()-amount);
			     userRepository.save(us);
				 Long CurrentCartId= cart.getId();	
				 icartservice.RemoveOrderLinesFromCart(CurrentCartId);
				 //Cart NewC = icartservice.addCart(user_id);
				 or.setCart(null);
				 
				 c.setValid(false);
				 System.out.println(c);
				 couponRepository.save(c);
				 orderRepo.save(or);
			      }
			  
		      else if (amount< or.getTotalprice()&& or.getTotalprice()!=0) {
				  float RestPayment= or.getTotalprice()-amount ;
				  or.setTotalprice(RestPayment); 
				  orderRepo.save(or);
				  us.setBalance(us.getBalance()-amount);
				  userRepository.save(us);
				  System.out.println("You still have to pay"+ RestPayment);
				  checkoutOnlinewithCoupon(user_id,order_id,amount);
				  
		    	  }
			  else
				  if (amount> or.getTotalprice()&& or.getTotalprice()!=0){
				    float RestAmount= amount-or.getTotalprice() ;
			     	us.setBalance((us.getBalance()-amount)+RestAmount);
				    userRepository.save(us);
					Long CurrentCartId= cart.getId();	
					icartservice.RemoveOrderLinesFromCart(CurrentCartId);
					//Cart NewC = icartservice.addCart(user_id);
					or.setCart(null);
					cp.setValid(false);
					couponRepository.save(cp);
					orderRepo.save(or);
			      }
			    

		  }
		
		
		else {
			 			  checkoutOnline(user_id,order_id,amount);
			
		}
		
		}
	}
	
	
	
	

}
