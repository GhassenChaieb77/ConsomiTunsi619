package tn.esprit.spring.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Entities.Category;
import tn.esprit.spring.Entities.Product;
import tn.esprit.spring.Repository.CategoryRepository;
import tn.esprit.spring.Repository.ProductRepository;

@Service
public class ProductService implements IProductService {
	
	@Autowired
	ProductRepository productRepo;
	
	@Autowired
	CategoryRepository categoryRepo;
	
	
	@Override
	public Product addProduct(Product p) {
		
		return productRepo.save(p) ;
	}

	@Override
	public List<Product> dispalyProducts() {
		return (List<Product>) productRepo.findAll();
	}

	@Override
	public void deleteProduct(long id) {
		productRepo.deleteById(id);
		//productRepo.delete(productRepo.findById(id).get());
	}

	@Override
	public Product updateProduct(long id) {
		
		return productRepo.save(productRepo.findById(id).get()) ;
		
	}

	@Override
	public String getProductNameById(long id) {
		productRepo.findById(id).get().getName();
		return  null;
		
	}

	@Override
	public String getProductCategoryById(long id) {
		
		productRepo.findById(id).get().getCategory().getName();
		return null;
	}

	@Override
	public List<Product> getAllProductByNamesJPQL(String name) {
		
		return productRepo.ProductNames(name) ;
	}

	@Override
	public List<Product> getAllProductByCategoryJPQLId(long id) {
		
		return productRepo.getAllProductByCategory(id);
	}

	@Override
	public Product getProductById(long id) {
		return productRepo.findById(id).get();
	}

	@Override
	public List<Product> getAllProductJPQL() {
		
		return productRepo.getAllProduct();
	}

	@Override
	public List<Product> getAllProductByCategoryJPQLName(String name) {

return productRepo.getAllProductByCategoryname(name);
	}


}
