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
import tn.esprit.spring.Services.ICategoryService;
import tn.esprit.spring.Services.IProductService;

@RestController
public class RestControlProduct {

	@Autowired
	IProductService iprodService;
	
	@Autowired
	ICategoryService icatService;
	
	/////Product
	
	// http://localhost:8081/SpringMVC/servlet/addProduct
		//{"name":"tartare", "price":3.5, "picture":"tartare.jpg", "code":619123456, "quantity":12}
		
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
	
		
		//http://localhost:8081/SpringMVC/servlet/deleteProduct/1
	    @DeleteMapping("/deleteProduct/{idproc}") 
		@ResponseBody 
		public void deleteEmployeById(@PathVariable("idproc")long idproc) {
			iprodService.deleteProduct(idproc);
			
		}
	    
	 // http://localhost:8081/SpringMVC/servlet/UpdateProduct/2
		@PutMapping(value = "/UpdateProduct/{id}") 
		@ResponseBody
		public void updateProduct(@PathVariable("id") long id) {
			iprodService.updateProduct(id);
			
		} 
	    
		 //http://localhost:8081/SpringMVC/servlet/getAllProductsByName/tartare
		@GetMapping(value = "/getAllProductsByName/{name}")
	    @ResponseBody
		public List<Product> getAllProductsByName(@PathVariable("name") String name) {
			
			return iprodService.getAllProductByNamesJPQL(name);
		}
	    
		 //http://localhost:8081/SpringMVC/servlet/getProductById/2
		@GetMapping(value = "/getProductById/{id}")
	    @ResponseBody
		public Product getProductById(@PathVariable("id") long id) {
			
			return iprodService.getProductById(id);
		}
		
		 //http://localhost:8081/SpringMVC/servlet/getProductByCategory/1
		@GetMapping(value = "/getProductByCategory/{id}")
	    @ResponseBody
		public List<Product> getProductByCategory(@PathVariable("id") long id) {
			
			return iprodService.getAllProductByCategoryJPQLId(id);
		}
		
		 //http://localhost:8081/SpringMVC/servlet/getProductByCategoryName/food
		@GetMapping(value = "/getProductByCategoryName/{name}")
	    @ResponseBody
		public List<Product> getProductByCategoryName(@PathVariable("name") String name) {
			
			return iprodService.getAllProductByCategoryJPQLName(name);
		}
	    
/////Category	    
	    
	    
	   // http://localhost:8081/SpringMVC/servlet/addCategory
	 		//{"name":"food"}
	 		
	 		@PostMapping("/addCategory")
	 		@ResponseBody
	 		public Category addCat(@RequestBody Category c)
	 		{
	 			icatService.addCategory(c);
	 			return c;
	 		}
	    
	  //http://localhost:8081/SpringMVC/servlet/getAllCategories
			@GetMapping(value = "/getAllCategories")
		    @ResponseBody
			public List<Category> getAllCategories() {
				
				return icatService.dispalyCategories();
			}
	    
	    
	 // http://localhost:8081/SpringMVC/servlet/affectCategoryToProduct/1/1
	    @PutMapping(value = "/affectCategoryToProduct/{ProcId}/{cateId}") 
	 	public void affectCategoryToProduct(@PathVariable("ProcId")long ProcId, @PathVariable("cateId")long cateId)
	 	{
	 		icatService.affectCategoryToProduct(ProcId, cateId);
	 	}
	    
	 // http://localhost:8081/SpringMVC/servlet/disaffectCategoryToProduct/2/1
		@PutMapping(value = "/disaffectCategoryToProduct/{ProcId}/{cateId}") 
		public void disaffectCategoryToProduct(@PathVariable("ProcId")long ProcId, @PathVariable("cateId")long cateId)
		{
			icatService.disaffectCategoryToProduct(ProcId, cateId);
		}
}
