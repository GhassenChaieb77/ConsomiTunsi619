package tn.esprit.spring.Controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
	IBillService ibillService;
	@Autowired
	UserRepository userRepository ;

	@Autowired
	OrderRepository orderRepo ;
	
	@Autowired
	CartRepository cartRepository;
	
	@PostMapping("/order/{user_id}/{Adresse}/{methode}")
	//{"Adresse":"Nabel" }
		@ResponseBody
		public ResponseEntity<Order> Ordring(@PathVariable("user_id")Long user_id,@PathVariable("Adresse")String Adresse, @PathVariable("methode")String methode )
		{
		 try{  Cart cart = cartRepository.getCartByUserId(user_id);
		 if (cart.getProdpricetotal() != 0)
			{
			 
				 return ResponseEntity.ok(iorderService.CreateOrder(user_id,Adresse,methode)) ;
			 }
		 
		 return ResponseEntity.ok(null) ;}
			 catch (Exception e){
		    		e.getStackTrace();
		    		return ResponseEntity
		    	            .status(HttpStatus.FORBIDDEN)
		    	            .body(null);
		    		}
			
		
		
		}
	
	@GetMapping(value = "/getOrdersByUser/{user_id}")
	public List<Order> getOrdersByUserId(@PathVariable("user_id") Long user_id) {
		
		return iorderService.getOrdersByUser(user_id); 
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
	
	    
	
	    @RequestMapping(value="/payoffline/{user_id}/{order_id}",method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
		@ResponseBody
		public Order Checkout(@PathVariable("user_id")Long user_id,@PathVariable("order_id")Long order_id)
		{
			return iorderService.checkoutOff(user_id,order_id) ;
		
		}
	    
	    @RequestMapping(value="/PayOnline/{user_id}/{order_id}/{amount}",method=RequestMethod.POST)
		@ResponseBody
		public ResponseEntity<String> CheckOutOnline(@PathVariable("user_id")Long user_id,@PathVariable("order_id")Long order_id,@PathVariable("amount") float amount)
		{
	    	User us= userRepository.findById(user_id).get();
    		float UserAmount = us.getBalance() ;
    		
    		Order or = orderRepo.findById(order_id).get();
    		
    		float origPrice= or.getTotalprice();
    		float rest =  origPrice-amount ;
	    	if(UserAmount>= amount){
	    		try {
	    			if(amount<or.getTotalprice()) {
		    					
    				 iorderService.checkoutOnline(user_id, order_id, amount) ;	
    				 
    				 return ResponseEntity
		    	            .status(HttpStatus.ACCEPTED)
		    	            .body("You still have to pay "+rest);	  		
	    			}
	    			
	    			else{	
	    				iorderService.checkoutOnline(user_id, order_id, amount) ;		  		
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
	    
	    @GetMapping(value = "/getCouponCode/{user_id}")
	    public String getCouponCode(@PathVariable("user_id")Long user_id) {
	    	return iorderService.getCouponCode(user_id);
	    }
	    
	    
	    
	    @RequestMapping(value="/PayOnlineWithCoupons/{user_id}/{order_id}/{amount}",method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
		@ResponseBody
		public ResponseEntity<String>  checkoutOnlinewithCoupon(@PathVariable("user_id")Long user_id,@PathVariable("order_id")Long order_id,@PathVariable("amount") float amount)
		{
	    	User us= userRepository.findById(user_id).get();
    		float UserAmount = us.getBalance() ;
    		Order or = orderRepo.findById(order_id).get();	
	    	if(UserAmount>= amount){
	    		try {
	    		
	    			if(amount<or.getTotalprice()) {
		    					
    				 iorderService.checkoutOnline(user_id, order_id, amount) ;	
    				 return ResponseEntity
		    	            .status(HttpStatus.ACCEPTED)
		    	            .body("You still have to pay "+or.getTotalprice());	  		
	    			}
	    			
	    			else{	
	    				iorderService.checkoutOnline(user_id, order_id, amount) ;	
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
	    
	   
	    @PutMapping(value = "/CouponDiscount/{user_id}/{order_id}")
	    public Order CouponDiscount(@PathVariable("user_id")Long user_id,@PathVariable("order_id")Long order_id){
	    	return iorderService.CouponDiscount(user_id,order_id);
	    }
	 
	    
	    // PDF
	      
	    @GetMapping("/getBillpfd/export/pdf")
	    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
	        response.setContentType("application/pdf");
	        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
	        String currentDateTime = dateFormatter.format(new Date());
	         
	        String headerKey = "Content-Disposition";
	        String headerValue = "attachment; filename=bill_" + currentDateTime + ".pdf";
	        response.setHeader(headerKey, headerValue);
	         
	        
	         
	      //  UserPDFExporter exporter = new UserPDFExporter();
	      //  exporter.export(response);
	         
	    }
	   

}
