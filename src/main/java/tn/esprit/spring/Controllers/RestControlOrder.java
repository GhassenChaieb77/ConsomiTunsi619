package tn.esprit.spring.Controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lowagie.text.DocumentException;

import tn.esprit.spring.Entities.*;
import tn.esprit.spring.Repository.BillRepository;
import tn.esprit.spring.Repository.CartRepository;
import tn.esprit.spring.Repository.OrderRepository;
import tn.esprit.spring.Repository.UserRepository;
import tn.esprit.spring.Services.*;



@RestController
@RequestMapping("/order")
public class RestControlOrder {
	@Autowired
	IOrderLineService iorderLineService;
	@Autowired
	IOrderService iorderService;

	@Autowired
	ICartService icartService;
	@Autowired
	IBillService ibillService;
	@Autowired
	UserRepository userRepository ;

	
	@Autowired
	UserServiceImp userService ;
	
	@Autowired
	OrderRepository orderRepo ;
	
	
	@Autowired
	CartRepository cartRepository;
	
	@PostMapping("/confirmorder/{Adresse}/{methode}")
	//{"Adresse":"Nabel" }
		@ResponseBody
		public ResponseEntity<Order> Ordring(@PathVariable("Adresse")String Adresse, @PathVariable("methode")String methode )
		{
		 try{  Cart cart = icartService.getCartByUserId();
		 if (cart.getProdpricetotal() != 0)
			{
			 
				 return ResponseEntity.ok(iorderService.CreateOrder(Adresse,methode)) ;
			 }
		 
		 return ResponseEntity.ok(null) ;}
			 catch (Exception e){
		    		e.getStackTrace();
		    		return ResponseEntity
		    	            .status(HttpStatus.FORBIDDEN)
		    	            .body(null);
		    		}
			
		
		
		}
	
	@GetMapping(value = "/getOrdersByUser")
	public List<Order> getOrdersByUserId() {
		
		return iorderService.getOrdersByUser(); 
	}
	
	@GetMapping(value = "/getOrdersByYear/{year}")
	public List<Order> getOrderByYear(@PathVariable("year") int year) {
		
		return iorderService.getOrdersByYear(year); 
	}

	@GetMapping(value = "/getOrdersByMonth/{month}")
	public List<Order> getOrderByMonth(@PathVariable("month") int month) {
		
		return iorderService.getOrdersByMonth(month); 
	}
	/*@PutMapping(value = "/AffectBillToOrder/{order_id}/{bill_id}") 
	public Bill AffectBillToOrder (@PathVariable("order_id")Long order_id,@PathVariable("bill_id")Long bill_id) {
		return ibillService.CreateAndAffectBillToOrder(order_id, bill_id) ;
	}*/
	
	@GetMapping(value = "/getOrderByCart/{cart_id}")
	 public Order getOrderByCartId(@PathVariable("cart_id")Long cart_id) {
		
		 return iorderService.getOrderByCartId(cart_id); 
	}
	
	    
	
	    @RequestMapping(value="/payoffline/{order_id}",method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
		@ResponseBody
		public Order Checkout(@PathVariable("order_id")Long order_id)
		{
			return iorderService.checkoutOff(order_id) ;
		
		}
	    
	    @RequestMapping(value="/PayOnline/{order_id}/{amount}",method=RequestMethod.POST)
		@ResponseBody
		public ResponseEntity<String> CheckOutOnline(@PathVariable("order_id")Long order_id,@PathVariable("amount") float amount)
		{
	    	
	    	 User us = userService.getUserInfo();
	    //	User us= userRepository.findById(user_id).get();
    		float UserAmount = us.getBalance() ;
    		
    		Order or = orderRepo.findById(order_id).get();
    		
    		float origPrice= or.getTotalprice();
    		float rest =  origPrice-amount ;
	    	if(UserAmount>= amount){
	    		try {
	    			if(amount<or.getTotalprice()) {
		    					
    				 iorderService.checkoutOnline(order_id, amount) ;	
    				 
    				 return ResponseEntity
		    	            .status(HttpStatus.ACCEPTED)
		    	            .body("You still have to pay "+rest);	  		
	    			}
	    			
	    			else{	
	    				iorderService.checkoutOnline(order_id, amount) ;		  		
		                 return ResponseEntity.ok(or.toString());
		            	}
	    			}

	    	catch (Exception e){
	    		e.getStackTrace();
	    		return ResponseEntity
	    	            .status(HttpStatus.FORBIDDEN)
	    	            .body("fail");
	    		}
	    		
	    		}else{
	    	return ResponseEntity
    	            .status(HttpStatus.FORBIDDEN)
    	            .body("You don't have enough money!");}
	    }
	    
	    @GetMapping(value = "/getCouponCode")
	    public String getCouponCode() {
	    	return iorderService.getCouponCode();
	    }
	    
	    
	    
	    @RequestMapping(value="/PayOnlineWithCoupons/{order_id}/{amount}",method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
		@ResponseBody
		public ResponseEntity<String>  checkoutOnlinewithCoupon(@PathVariable("order_id")Long order_id,@PathVariable("amount") float amount)
		{
	    	 User us = userService.getUserInfo();
	    	//User us= userRepository.findById(user_id).get();
    		float UserAmount = us.getBalance() ;
    		Order or = orderRepo.findById(order_id).get();	
	    	if(UserAmount>= amount){
	    		try {
	    		
	    			if(amount<or.getTotalprice()) {
		    					
    				 iorderService.checkoutOnlinewithCoupon(order_id, amount) ;	
    				 return ResponseEntity
		    	            .status(HttpStatus.ACCEPTED)
		    	            .body("You still have to pay "+or.getTotalprice());	  	
    				
	    			}
	    			
	    			else{	
	    				iorderService.checkoutOnlinewithCoupon(order_id, amount) ;	
	    				if(or.getCoupon()==null)
		    			{return ResponseEntity
			    	            .status(HttpStatus.FORBIDDEN)
			    	            .body("Coupon not available!");} 
		                 return ResponseEntity.ok(or.toString());
		            	}
	    			}

	    	catch (Exception e){
	    		e.getStackTrace();
	    		return ResponseEntity
	    	            .status(HttpStatus.FORBIDDEN)
	    	            .body("fail");
	    		}
	    		
	    		}else{
	    	return ResponseEntity
    	            .status(HttpStatus.FORBIDDEN)
    	            .body("You don't have enough money!");}
	    }
	    
	   
	    @PutMapping(value = "/CouponDiscount/{order_id}")
	    public Order CouponDiscount(@PathVariable("order_id")Long order_id){
	    	return iorderService.CouponDiscount(order_id);
	    }
	 
	    
	    // PDF
	      
	    @GetMapping("/getBillpfd/export/pdf")
	    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
	        response.setContentType("application/pdf");
	        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
	        String currentDateTime = dateFormatter.format(new Date());
	         
	        String headerKey = "Content-Disposition";
	        String headerValue = "attachment; filename=bill.pdf";
	        response.setHeader(headerKey, headerValue);
	       
	        List<Bill> listbills =iorderService.getUserBill() ;
	        

	    	 BillPDFExporter exporter = new BillPDFExporter(listbills);
	    	 exporter.export(response);

		
	         
	    }
	   

}
