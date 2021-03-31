package tn.esprit.spring.Controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.* ;

import tn.esprit.spring.Entities.Cart;
import tn.esprit.spring.Entities.OrderLine;
import tn.esprit.spring.Entities.Product;
import tn.esprit.spring.Entities.User;
import tn.esprit.spring.Services.CartService;
import tn.esprit.spring.Services.CartServiceImp;


@RestController
public class CartController {

	@Autowired
	CartService cartService ;
/*	
	@RequestMapping("getCart/{user_id}")  
	public Cart getCartByUserId(@PathVariable("user_id") long user_id) {
		return cartService.getCartByUserId(user_id) ;
	}*/
	

	
	//http://localhost:8081/SpringMVC/servlet/getCartProducts
			@GetMapping(value = "/getCartProducts")
			@ResponseBody
			public List<Product> getCartProducts() {
				
				return cartService.getCartProducts();
			}
			
			//http://localhost:8081/SpringMVC/servlet/getusers/2
			@GetMapping(value = "/getusers")
			@ResponseBody
			public List<User> getusers() {
				
				return cartService.getUsers();
			}
}
