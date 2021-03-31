package tn.esprit.spring.Controllers;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
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
import tn.esprit.spring.Entities.Stock;
import tn.esprit.spring.Repository.StockRepository;
import tn.esprit.spring.Services.StockServiceImpa;
@RestController
@RequestMapping("/Stock")
public class StockController {
	
	@Autowired
	StockServiceImpa ss;
	
	
	@PostMapping("/Auto/{id}")
	 @ResponseBody
	 public void test(@PathVariable("id")long id) throws MessagingException {
	
	ss.autostock(id);
	
	}
	
	
	
	@PostMapping("/add")
	  public void createTutorial(@RequestBody Stock u) {
	 
	     ss.SaveStock(u);
		
	  }
	
	 @GetMapping("/retrieve-all-Stock")
	 @ResponseBody
	 public List<Stock> getStock() {
	 List<Stock> list = ss.getAllStock();	    
	        return list;
}
	 
	  @DeleteMapping("/Delete/{id}")
	  public void deleteTutorial(@PathVariable("id") long id) {
	      ss.DeleteStock(id);
	
	  }
	  
	  
	  @PutMapping("/Update/{id}")
	  public void updateLayer( @PathVariable("id") Long ID, @RequestBody Stock l)
	  {

		  Stock l1 =null;
		  l1=ss.getStockById(ID);
		 l1.setEntryQt(l.getEntryQt());
		 l1.setOperationDate(l.getOperationDate());
		 l1.setPurchaseDate(l.getPurchaseDate());
		 l1.setPurchasePrice(l.getPurchasePrice());
		  
	     ss.SaveStock(l1);
	  }
	

	  @PostMapping("/Setpro/{id}/{id2}")
	  public void setpro(@PathVariable("id") long LayerID,@PathVariable("id2") long id)
	  {
		  ss.setprod(LayerID, id);

	  }
	  
	  
	  @GetMapping("/MoneyLostByYear/{eventDate}")
		 @ResponseBody
		 public String Money(@PathVariable("eventDate") int eventDate) {
		 float sum=0;
		  List<Stock> l = ss.getAllStock();
		 for(Stock s2 : l)
		 {
			 if(s2.getPurchaseDate().getYear()+1900==eventDate)
			 {
				 sum=sum+s2.getPurchasePrice();
			 }
			 
		 }
		  
		  
		  return "You've lost"+sum;
	  }
	  
	  
	  @GetMapping("/MoneyLostByMonth/{eventDate}")
		 @ResponseBody
		 public String MoneyMonth(@PathVariable("eventDate") int eventDate) {
		 float sum=0;
		  List<Stock> l = ss.getAllStock();
		 for(Stock s2 : l)
		 {
			
			 if(s2.getPurchaseDate().getMonth()+1==eventDate)
			 {
				 sum=sum+s2.getPurchasePrice();
			 }
			 
		 }
		System.err.println(  ss.MostNeededStock());
		  
		  return "You've lost"+sum;
	  }
	  
	  
	  @GetMapping("/MostNeededPro")
		 @ResponseBody
		 public String MostNeededPro() {
		
		  
		  return ss.MostNeededStock();
	  }
	  
	  
}
