	package tn.esprit.spring.Controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.Entities.Layer;
import tn.esprit.spring.Entities.Product;
import tn.esprit.spring.Services.LayerServiceImp;

@RestController
@RequestMapping("/layer")
public class LayerController {
	
	  @Autowired
	  LayerServiceImp up;
	
	@PostMapping("/add")
	  public void createTutorial(@RequestBody Layer u) {
	 
	     up.saveLayer(u);
		
	  }
	
	
	
	@GetMapping("/retrieve-all-Product/{id}")
	 @ResponseBody
	 public List<Product> GetProduct(@PathVariable("id")long id) {
	 List<Product> list = up.GetP(id);
	 return list;
	}
	 @GetMapping("/retrieve-all-Layer")
	 @ResponseBody
	 public List<Layer> getLayers() {
	 List<Layer> list = up.getAllLayer();
		    
	        return list;
}
	 
	  @DeleteMapping("/Delete/{id}")
	  public void deleteTutorial(@PathVariable("id") long id) {
	      up.DeleteLayer(id);
	
	  }
	  
	  
	  @PutMapping("/Update/{id}")
	  public void updateLayer( @PathVariable("id") Long LayerID, @RequestBody Layer l)
	  {

		  Layer l1 =up.getLayerById(LayerID);
		  l1.setCapacity(l.getCapacity());
		  l1.setCategory(l.getCategory());
		  l1.setResponsible(l.getResponsible());


	 
	    final Layer uLayer = up.saveLayer(l1);
	  }
	  
	  
	  @PutMapping("/Set/{id}/{id2}")
	  public void setcat( @PathVariable("id") long LayerID,@PathVariable("id2") long id)
	  {
		  up.Setcategory(LayerID, id);

	  }
	  
	  
	  @PostMapping("/Setpro/{id}/{id2}")
	  public void setpro(@PathVariable("id") long LayerID,@PathVariable("id2") long id)
	  {
		  up.setprod(LayerID, id);

	  }
	  
	  @PostMapping("/Auto/{id}")
	  public void Auto2(@PathVariable("id") long LayerID)
	  {
          up.AutoLayer(LayerID);
	  }
	  

}
