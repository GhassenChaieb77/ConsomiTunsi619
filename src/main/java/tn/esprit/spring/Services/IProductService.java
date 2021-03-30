package tn.esprit.spring.Services;

import java.util.List;

import tn.esprit.spring.Entities.Category;
import tn.esprit.spring.Entities.Product;
import tn.esprit.spring.Entities.Publicity;
import tn.esprit.spring.Entities.Reduction;


public interface IProductService {

	public Product addProduct(Product p);// verifiey code 
	public List<Product> dispalyProducts();
	public void deleteProduct(long id);
	public Product updateProduct(Product p);
	
	public Product getProductById(long id);
	public String getProductNameById(long id);	
	public List<Product> getAllProductByNamesJPQL(String name);
	public String getProductCategoryById(long id);
	public List<Product> getAllProductByCategoryJPQLId(long id);
	public List<Product> getAllProductByCategoryJPQLName(String name);
	public List<Product> getAllProductJPQL();
	public List<Product> detailProduct(long id);

	
	public void affectProductToPub(long ProcId, long pubId);
	public void affectAutomatic();
	public void disaffectProductToPub(long pubId);
	public List<Publicity> getPubByProductId(long id);
	
	//public Product reductionForProduct(long id,int x);
	//public List<Product> reductionForProductsByCategory(String name,int x);
}