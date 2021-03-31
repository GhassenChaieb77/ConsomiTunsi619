package tn.esprit.spring.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.Entities.OrderLine;
import tn.esprit.spring.Entities.Product;
import tn.esprit.spring.Services.IOrderLineService;
import tn.esprit.spring.Services.IProductService;



@RestController
@RequestMapping("/orderLine")
public class RestControlOrderLine {
	
	@Autowired
	IOrderLineService iorderLineService;
	

	@RequestMapping(value ="/addProtoOrderLine/{idPro}/{quantity}", method=RequestMethod.POST)
    public OrderLine AddProductbyQuantity(@PathVariable("idPro") Long idPro,@PathVariable("quantity")int quantity) {
		
		return iorderLineService.AddProductbyQuantity(quantity, idPro);

	}
	
	@PutMapping(value = "/RemoveOrderLine/{OrderLId}") 
	public void RemoveProduct(@PathVariable("OrderLId") Long OrderLId ){
		iorderLineService.RemoveOrderLine(OrderLId);
		
	}
	@PutMapping(value = "/UpdateQuantity/{id}/{Newquantity}") 
 	@ResponseBody
	public OrderLine UpdateQuantity(@PathVariable("Newquantity") int Newquantity, @PathVariable("id") Long id)
	{
		return iorderLineService.UpdateQuantity(Newquantity, id);
	}
	
	@GetMapping(value = "/GetProductByOrderLine/{idOL}")
	public Product  GetProductByOrderLine(@PathVariable("idOL")Long id) {
		return iorderLineService.GetProductByOrderLine(id);
	}
	
	// URL : http://localhost:8081/SpringMVC/servlet/GetOrderLinePrice/2
    @GetMapping(value = "GetOrderLinePrice/{OrdLId}/{id}")
    @ResponseBody
	public float GetOrderLinePrice(@PathVariable("OrdLId")Long OrdLId,@PathVariable("id") Long id){
    	
    	return iorderLineService.GetOrderLinePrice(OrdLId,id);
    }


    @GetMapping(value = "/GetTotalPrice")
    @ResponseBody
	public float GetPriceTotal(){
    	
    	return iorderLineService.GetPriceTotal();
    }
    
    
    @GetMapping(value = "/GetOrderLinesByCart/{cart_id}")
    @ResponseBody
    public <List> OrderLine GetOrderLinesByCart(@PathVariable("cart_id")Long cart_id) {
    	return iorderLineService.GetOrderLinesByCart(cart_id) ;
    }
}

	
	

