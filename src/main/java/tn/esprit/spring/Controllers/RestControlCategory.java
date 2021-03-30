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
import tn.esprit.spring.Entities.Reduction;
import tn.esprit.spring.Services.ICategoryService;
import tn.esprit.spring.Services.IProductService;

@RestController
public class RestControlCategory {
	@Autowired
	IProductService iprodService;
	
	@Autowired
	ICategoryService icatService;
	
	// http://localhost:8081/SpringMVC/servlet/addCategory
		//{"name":"clothes"}
		
		@PostMapping("/addCategory")
		@ResponseBody
		public String addCategory(@RequestBody Category c)
		{
			return icatService.addCategory(c);
			
		}

//http://localhost:8081/SpringMVC/servlet/getAllCategories
	@GetMapping(value = "/getAllCategories")
    @ResponseBody
	public List<Category> getAllCategories() {
		
		return icatService.dispalyCategories();
	}
	
	//http://localhost:8081/SpringMVC/servlet/deleteCategory/4
    @DeleteMapping("/deleteCategory/{id}") 
	@ResponseBody 
	public void deleteCategoryById(@PathVariable("id")long id) {
		icatService.deleteCategory(id);
		
	}
    
	 // http://localhost:8081/SpringMVC/servlet/UpdateCategory
	@PutMapping(value = "/UpdateCategory") 
	@ResponseBody
	public Category UpdateCategory(@RequestBody Category c) {
		return icatService.updateCategory(c);
		
		
	} 


// http://localhost:8081/SpringMVC/servlet/affectCategoryToProduct/6/4
@PutMapping(value = "/affectCategoryToProduct/{ProcId}/{cateId}") 
	public void affectCategoryToProduct(@PathVariable("ProcId")long ProcId, @PathVariable("cateId")long cateId)
	{
		icatService.affectCategoryToProduct(ProcId, cateId);
	}

//http://localhost:8081/SpringMVC/servlet/disaffectCategoryToProduct/6
@PutMapping(value = "/disaffectCategoryToProduct/{ProcId}") 
	public void disaffectCategoryToProduct(@PathVariable("ProcId")long ProcId)
	{
		icatService.disaffectCategoryToProduct(ProcId);
	}


}
