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

import tn.esprit.spring.Entities.Product;
import tn.esprit.spring.Entities.Publicity;
import tn.esprit.spring.Entities.User;
import tn.esprit.spring.Services.ICategoryService;
import tn.esprit.spring.Services.IProductService;
import tn.esprit.spring.Services.IPublicityService;

@RestController
public class RestControlPublicity {

	@Autowired
	IProductService iprodService;
	
	@Autowired
	ICategoryService icatService;
	
	@Autowired
	IPublicityService ipubService;
	
	
	// http://localhost:8081/SpringMVC/servlet/addPub
	//{"age":"18", "endDate":"2021-03-15","name":"offre", "saison":"summer", "sex":"men" , "startDate":"2021-03-20" ,"type":"event"}
	
	@PostMapping("/addPub")
	@ResponseBody
	public Publicity addPub(@RequestBody Publicity p)
	{
		ipubService.addPublicity(p);
		return p;
	}
	
		
		//http://localhost:8081/SpringMVC/servlet/deletePub/5
	    @DeleteMapping("/deletePub/{id}") 
		@ResponseBody 
		public void deletePubById(@PathVariable("id")long id) {
	    	ipubService.deletePublicity(id);
			
		}
		 // http://localhost:8081/SpringMVC/servlet/UpdatePub
			@PutMapping(value = "/UpdatePub") 
			@ResponseBody
			public Publicity UpdateUpdatePub(@RequestBody Publicity p) {
				return ipubService.updatePublicity(p);
				
				
			}   
			 //http://localhost:8081/SpringMVC/servlet/getAllPubs
			@GetMapping(value = "/getAllPubs")
		    @ResponseBody
			public List<Publicity> getAllPubs() {
				
				 return ipubService.dispalyPublicities();
			}
			
			//http://localhost:8081/SpringMVC/servlet/delete
		    @DeleteMapping("/delete") 
			@ResponseBody 
			public void delete() {
		    	ipubService.deletePub();
				
			}
		
}
