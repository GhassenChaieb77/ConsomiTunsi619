package tn.esprit.spring.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.* ;

import tn.esprit.spring.Entities.Cart;
import tn.esprit.spring.Services.CartServiceImp;


@RestController
@RequestMapping("Cart")
public class CartController {
/*	
	@Autowired
	CartServiceImp cartService ;
	
	@RequestMapping("getCart/{user_id}")  
	public Cart getCartByUserId(@PathVariable("user_id") long user_id) {
		return cartService.getCartByUserId(user_id) ;
	}*/

}
