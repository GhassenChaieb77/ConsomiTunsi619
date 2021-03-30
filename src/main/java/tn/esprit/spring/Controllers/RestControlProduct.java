package tn.esprit.spring.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.Entities.Category;
import tn.esprit.spring.Entities.Product;
import tn.esprit.spring.Entities.Publicity;
import tn.esprit.spring.Entities.Reduction;
import tn.esprit.spring.Services.ICategoryService;
import tn.esprit.spring.Services.IProductService;
import tn.esprit.spring.Services.IPublicityService;

@RestController
public class RestControlProduct {

	@Autowired
	IProductService iprodService;
	
	@Autowired
	ICategoryService icatService;
	
	@Autowired
	IPublicityService ipubService;
	
	/////Product
	
	// http://localhost:8081/SpringMVC/servlet/addProduct
		//{"name":"tartare", "price":3.5, "picture":"tartare.jpg", "code":"619123456", "quantity":12}
		
		@PostMapping("/addProduct")
		@ResponseBody
		public Product addProc(@RequestBody Product p)
		{
			iprodService.addProduct(p);
			return p;
		}
		
		 //http://localhost:8081/SpringMVC/servlet/getAllProducts
		@GetMapping(value = "/getAllProducts")
	    @ResponseBody
		public List<Product> getAllProducts() {
			
			 return iprodService.dispalyProducts();
		}
	
		
		//http://localhost:8081/SpringMVC/servlet/deleteProduct/5
	    @DeleteMapping("/deleteProduct/{idproc}") 
		@ResponseBody 
		public void deleteProductById(@PathVariable("idproc")long idproc) {
			iprodService.deleteProduct(idproc);
			
		}
	    
	 // http://localhost:8081/SpringMVC/servlet/UpdateProduct
		@PutMapping(value = "/UpdateProduct") 
		@ResponseBody
		public Product updateProduct(@RequestBody Product p) {
			return iprodService.updateProduct(p);
			
		} 
	    
		 //http://localhost:8081/SpringMVC/servlet/getAllProductsByName/tartare
		@GetMapping(value = "/getAllProductsByName/{name}")
	    @ResponseBody
		public List<Product> getAllProductsByName(@PathVariable("name") String name) {
			
			return iprodService.getAllProductByNamesJPQL(name);
		}
	    
		 //http://localhost:8081/SpringMVC/servlet/getProductById/6
		@GetMapping(value = "/getProductById/{id}")
	    @ResponseBody
		public Product getProductById(@PathVariable("id") long id) {
			
			return iprodService.getProductById(id);
		}
		
		 //http://localhost:8081/SpringMVC/servlet/getProductByCategory/4
		@GetMapping(value = "/getProductByCategory/{id}")
	    @ResponseBody
		public List<Product> getProductByCategory(@PathVariable("id") long id) {
			
			return iprodService.getAllProductByCategoryJPQLId(id);
		}
		
		 //http://localhost:8081/SpringMVC/servlet/getCategoryByProductId/6
		@GetMapping(value = "/getCategoryByProductId/{id}")
	    @ResponseBody
		public String getCategoryByProductId(@PathVariable("id") long id) {
			
			return iprodService.getProductCategoryById(id);
		}
		
		
		//http://localhost:8081/SpringMVC/servlet/detailProduct/2
		@GetMapping(value = "/detailProduct/{id}")
		@ResponseBody
		public List<Product> detaill(@PathVariable("id") long id) {
			
			return iprodService.detailProduct(id);
		}
		
		//http://localhost:8081/SpringMVC/servlet/getAllProductByCategoryJPQLName/clothes
		@GetMapping(value = "/getAllProductByCategoryJPQLName/{name}")
		@ResponseBody
		public List<Product> getAllProductByCategoryJPQLName(@PathVariable("name") String name) {
			
			return iprodService.getAllProductByCategoryJPQLName(name);
		}
		
		
		// http://localhost:8081/SpringMVC/servlet/affectProductToPub/6/4
	    @PutMapping(value = "/affectProductToPub/{ProcId}/{pubId}") 
	 	public void affectProductToPub(@PathVariable("ProcId")long ProcId, @PathVariable("pubId")long pubId)
	 	{
	 		iprodService.affectProductToPub(ProcId, pubId);
	 	}
	    
	 // http://localhost:8081/SpringMVC/servlet/disaffectProductToPub/6
	    @PutMapping(value = "/disaffectProductToPub/{pubId}") 
	 	public void disaffectProductToPub( @PathVariable("pubId")long pubId)
	 	{
	 		iprodService.disaffectProductToPub(pubId);
	 	}
		
		 //http://localhost:8081/SpringMVC/servlet/getPubByProductId/4
		@GetMapping(value = "/getPubByProductId/{id}")
	    @ResponseBody
		public List<Publicity> getPubByProductId(@PathVariable("id") long id) {
			
			return iprodService.getPubByProductId(id);
		}

}
