package tn.esprit.spring.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Entities.Product;
import tn.esprit.spring.Repository.ProductRepository;
@Service
public class ProductServiceImpa {
	
	
	
	@Autowired
	ProductRepository p;
	
	public List<Product> getAllProduct() {


		List<Product> l  = (List<Product>) p.findAll();
		
		return l;
	
	
	}

}
