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
	
	@Autowired
	UserServiceImp userService ;
	//PaymentMethod pm = null ;
	@Override
	@Transactional
	public Order CreateOrder(String Adresse,String methode) {
		

	    User user = userService.getUserInfo();
		Cart cart = cartRepository.getCartByUserId(user.getId());
		//Order or = orderRepo.getOrderByUser(user_id);
		//User user = userRepository.findById(user_id).get();
		
		
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
         orderRepo.save(order) ;
		 cart.setProdpricetotal(OLService.GetPriceTotal());
		 cartRepository.save(cart) ;
			if(order.getBill()==null){
				float priceOrig = order.getTotalprice();

		         int QtFinal = 0;
	        Bill bill= ibillService.CreateAndAffectBillToOrder(order.getId(),user.getId());
	   		for (int i = 0; i < cart.getOrderLines().size(); i++) {
	   		    QtFinal  =+order.getCart().getOrderLines().get(i).getQuantity();
	   		   
	   		}
	   			bill.setQuantity(QtFinal );
	   	    	bill.setTotal(priceOrig);
	   	
	         billRepository.save(bill);
			 order.setBill(bill);
	}
		 orderRepo.save(order) ;   
		
		
		 System.out.println(cart.getOrderLines().size());
		 return order  ;
		
	
	}
	

	@Override
	public List<Order> getOrdersByUser() {
		// TODO Auto-generated method stub
		User user = userService.getUserInfo();
		return orderRepo.getOrderByUser(user.getId())	;	
		//return (List<Order>) orderRepo.findAll() ;
		 
		 
		
	}

	public List<Order> getOrders() {
		// TODO Auto-generated method stub
		User user = userService.getUserInfo();
		//return orderRepo.getOrderByUser(user.getId())	;	
		return (List<Order>) orderRepo.findAll() ;
		 

	}

	@Override
	public Order checkoutOff(Long order_id) {
		// TODO Auto-generated method stub
		User user = userService.getUserInfo();
		Cart cart = cartRepository.getCartByUserId(user.getId());
		Order or = orderRepo.findById(order_id).get();
		
	     
			
			 orderRepo.save(or);
			 Long CurrentCartId= cart.getId();	
		     icartservice.RemoveOrderLinesFromCart(CurrentCartId);
			 or.setCart(null);
			 orderRepo.save(or);;
	
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
	public Order AddOrderLine() {
		// TODO Auto-generated method stub
		User user = userService.getUserInfo();
		Cart cart = cartRepository.getCartByUserId(user.getId());
	//	OrderLine ol = orderlineRepo.findAllById(arg0)
		Order or = orderRepo.findById(user.getId()).get();
		return or;
	}





	@Override
	public Order getOrderByCartId(Long cart_id) {
		// TODO Auto-generated method stub
		return orderRepo.getOrderByCartId(cart_id);
	}




	@Override
	public void checkoutOnline(Long order_id,float amount) {
		// TODO Auto-generated method stub
		User us = userService.getUserInfo();
		Cart cart = cartRepository.getCartByUserId(us.getId());
		Order or = orderRepo.findById(order_id).get();	
	
		float priceOrig = or.getTotalprice();
		  
	//	float UserAmount = us.getBalance() ;
		if(or.getPaymentmethod().equals("online"))
		{ 
		  int QtFinal = 0;
			 
			  if(amount==or.getTotalprice() && or.getTotalprice()!=0)
			  {
			     us.setBalance(us.getBalance()-amount);
			     userRepository.save(us);
			     

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
				//  us.setBalance(us.getBalance()-amount);
				//  userRepository.save(us);
				  System.out.println("You still have to pay"+ RestPayment);
				  checkoutOnline(order_id,amount);
				
		    	  }
			  else
				  if (amount> or.getTotalprice()&& or.getTotalprice()!=0){
					 // Bill bill= ibillService.CreateAndAffectBillToOrder(or.getId(),us.getId());
				    float RestAmount= amount-priceOrig ;
			     	us.setBalance((us.getBalance()-amount)+RestAmount);
			
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
	public String getCouponCode() {
		// TODO Auto-generated method stub
		User user = userService.getUserInfo();
		List<Order> UserOrders = orderRepo.getOrderByUser(user.getId());
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
	public Order CouponDiscount(Long order_id) {
		// TODO Auto-generated method stub
	
		User user = userService.getUserInfo();
	 List<Order> UserOrders = orderRepo.getOrderByUser(user.getId());
	 
	/*   final Iterator<Order> itr = UserOrders.iterator();
	  //synchronized (UserOrders) {
	    Order lastElement=orderRepo.findById(order_id).get();
	    while(itr.hasNext()) {
	        lastElement =  itr.next();
	    }*/
	 long max = UserOrders.get(0).getId();
	 for(int i=1 ; i<UserOrders.size();i++) {
		 
		 if(UserOrders.get(i).getId()>max)
		 {
			 max= UserOrders.get(i).getId();
		 }
		 
	 }
	 
	 Order or=orderRepo.findById(max).get()  ;
	 List<Coupon> coupons = new ArrayList<>();
	 couponRepository.findAll().forEach(coupons::add);
	 if (getCouponCode()!=null)
	 { if (getCouponCode().equals("promo50")){
			
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
		 else if (getCouponCode().equals("promo20")){
				
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
	public void checkoutOnlinewithCoupon(Long order_id, float amount) {
		// TODO Auto-generated method stub
		User user = userService.getUserInfo();
		Cart cart = cartRepository.getCartByUserId(user.getId());
		Order or = orderRepo.findById(order_id).get();	
		//User us= userRepository.findById(user_id).get();
		float UserAmount = user.getBalance() ;
		Coupon c = couponRepository.findById(or.getCoupon().getId()).get();
		List<Coupon> coupons = new ArrayList<>();
		couponRepository.findAll().forEach(coupons::add);
		for (Coupon cp : coupons) {
				//CouponPay(user_id, order_id, amount);}
		
		//Coupon cp = couponRepository.findById(or.getCoupon().getId()).get();
		if(cp.isValid()&& getCouponCode()!=null) {
		//	CouponDiscount(order_id);
			 
			  if(amount==or.getTotalprice()&& or.getTotalprice()!=0)
			  {
			     user.setBalance(user.getBalance()-amount);
			     userRepository.save(user);
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
				//  user.setBalance(user.getBalance()-amount);
				 // userRepository.save(user);
				  System.out.println("You still have to pay"+ RestPayment);
				  checkoutOnlinewithCoupon(order_id,amount);
				  
		    	  }
			  else
				  if (amount> or.getTotalprice()&& or.getTotalprice()!=0){
				    float RestAmount= amount-or.getTotalprice() ;
			     	user.setBalance((user.getBalance()-amount)+RestAmount);
				    userRepository.save(user);
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
			 			  checkoutOnline(order_id,amount);
			
		}
		
		}
	}




	@Override
	public List<Bill> getUserBill()
	{
		User user = userService.getUserInfo();
	//	Cart cart = cartRepository.getCartByUserId(user.getId());
		
		List<Bill> bills = orderRepo.getUserBill(user.getId());
		 return bills ;
		
	}
	
	

	@Override
    public Order getorder(long id) {
        return orderRepo.getorderbyid(id);
    }
	
	

}
