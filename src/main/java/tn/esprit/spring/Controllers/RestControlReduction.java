package tn.esprit.spring.Controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.Entities.Product;
import tn.esprit.spring.Entities.Reduction;
import tn.esprit.spring.Services.IProductService;
import tn.esprit.spring.Services.IReductionService;

@RestController
public class RestControlReduction {
	
	@Autowired
	IReductionService iredService;

	// http://localhost:8081/SpringMVC/servlet/affectReductionToCategory/food/2021-03-20/2021-03-25
			//{"name":"spring sale","reduction":20}
			
			@PostMapping("/affectReductionToCategory/{name}/{start}/{end}")
			@ResponseBody
			public List<Product> affectReductionToCategory(@PathVariable("name") String name,@PathVariable("start")  @DateTimeFormat(pattern = "yyyy-MM-dd") Date start,@PathVariable("end")  @DateTimeFormat(pattern = "yyyy-MM-dd") Date end, @RequestBody Reduction r)
			{
				return iredService.affectReductionToCategory(name,start,end,r);
				
			}
			
			//http://localhost:8081/SpringMVC/servlet/affectReductionToProduct/1/2021-03-20/2021-03-25
			//{"name":"spring sale","reduction":20}
			@PostMapping("/affectReductionToProduct/{id}/{start}/{end}")
			@ResponseBody
			public Reduction affectReductionToProduct(@PathVariable("id") long id,@PathVariable("start")  @DateTimeFormat(pattern = "yyyy-MM-dd") Date start,@PathVariable("end")  @DateTimeFormat(pattern = "yyyy-MM-dd") Date end,@RequestBody Reduction r )
			{
				return iredService.affectReductionToProduct(id,start,end,r);
				
			}
			
			//http://localhost:8081/SpringMVC/servlet/getAllReduction
			@GetMapping(value = "/getAllReduction")
			@ResponseBody
			public List<Reduction> getReductions() {
				
				return iredService.displayReductions();
			}

			//http://localhost:8081/SpringMVC/servlet/getProduct/2021-03-20/2021-03-25
			@GetMapping("/getProduct/{start}/{end}")
			@ResponseBody
			public Product getProductsByReduction(@PathVariable("start")  @DateTimeFormat(pattern = "yyyy-MM-dd") Date start,@PathVariable("end")  @DateTimeFormat(pattern = "yyyy-MM-dd") Date end)
			{
				return iredService.getProductsByReduction(start,end);
				
			}	
			 //http://localhost:8081/SpringMVC/servlet/getReduction/6
			@GetMapping(value = "/getReduction/{id}")
		    @ResponseBody
			public List<Reduction> getReduction(@PathVariable("id") long id) {
				
				return iredService.getReductions(id);
			}
			
			//http://localhost:8081/SpringMVC/servlet/detail/2
			@GetMapping(value = "/detail/{id}")
			@ResponseBody
			public List<Product> detail(@PathVariable("id") long id) {
				
				return iredService.detailll(id);
			}
}
