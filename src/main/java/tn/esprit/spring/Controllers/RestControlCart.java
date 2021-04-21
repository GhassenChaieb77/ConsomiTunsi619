package tn.esprit.spring.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.Entities.*;
import tn.esprit.spring.Services.*;

@RestController
@RequestMapping("/cart")
public class RestControlCart {
	
	@Autowired
	ICartService icartService;
	
	@RequestMapping(value ="/AddOrderLinesToCart/{LineId}/{CartId}", method=RequestMethod.POST)
    public Cart AddOrderLinesToCart(@PathVariable("LineId")Long LineId,@PathVariable("CartId") Long CartId) {
		
		return icartService.AddOrderLinesToCart(LineId, CartId);

	}
	// http://localhost:8081/SpringMVC/servlet/addProduct
			//{ "totalprice":0}
			
			@PostMapping("/addCart")
		//	@ResponseBody
			public Cart addCart()
			{
				return icartService.addCart();
			
			}
		
	   //http://localhost:8081/SpringMVC/servlet/getCartProductst/1
		@GetMapping(value = "/getCartProducts/{cart_id}")
			    @ResponseBody
			public List<Product> getCartProducts(@PathVariable("cart_id")Long cart_id){
				return icartService.getCartProducts( cart_id);
			}
		
		@PutMapping(value = "/RemoveOrderLines/{cart_id}") 
		public Cart RemoveOrderLinesFromCart(@PathVariable("cart_id")Long cart_id){
			return icartService.RemoveOrderLinesFromCart(cart_id);
		}
		
		@GetMapping(value = "/getCart")
		public  ResponseEntity<Cart>  getCartByUserId() {
			
			try {
				 Cart c = icartService.getCartByUserId(); 
				
			    return ResponseEntity
		    	            .status(HttpStatus.ACCEPTED)
		    	            .body(c);	
			
			}
			catch (Exception e) {
				return ResponseEntity
	    	            .status(HttpStatus.ACCEPTED)
	    	            .body(null);
			}
			
		}
		
		
		
		@GetMapping(value = "/getAllOrderLinesByCart/{cart_id}")
	    @ResponseBody
	    public <List> Cart getAllOrderLinesByCart(@PathVariable("cart_id")Long cart_id){
		return icartService.getAllOrderLinesByCart(cart_id);
	}


                    //http://localhost:8081/SpringMVC/servlet/getCartProducts
			@GetMapping(value = "/getCartProducts")
			@ResponseBody
			public List<Product> getCartProducts() {
				
				return icartService.getCartProducts();
			}
			
}
