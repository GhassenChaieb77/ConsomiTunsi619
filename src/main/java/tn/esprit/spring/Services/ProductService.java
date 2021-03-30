package tn.esprit.spring.Services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import tn.esprit.spring.Entities.Category;
import tn.esprit.spring.Entities.Product;
import tn.esprit.spring.Entities.Publicity;
import tn.esprit.spring.Entities.Reduction;
import tn.esprit.spring.Repository.CategoryRepository;
import tn.esprit.spring.Repository.ProductRepository;
import tn.esprit.spring.Repository.PublicityRepository;

@Service
public class ProductService implements IProductService {
	
	@Autowired
	ProductRepository productRepo;
	
	@Autowired
	CategoryRepository categoryRepo;
	
	@Autowired
	PublicityRepository pubRepo;
	
	
	@Override
	public Product addProduct(Product p) {
		
		if(p.getCode().startsWith("619"))
			productRepo.save(p) ;

		return p;
		
		
	}

	@Override
	public List<Product> dispalyProducts() {
		return  (List<Product>) productRepo.findAll();
	}

	@Override
	public void deleteProduct(long id) {
		productRepo.deleteById(id);
		//productRepo.delete(productRepo.findById(id).get());
	}

	@Override
    public Product updateProduct( Product p) {
        return productRepo.save(p);
        
    }

	@Override
	public String getProductNameById(long id) {
		productRepo.findById(id).get().getName();
		return  null;
		
	}

	@Override
	public String getProductCategoryById(long id) {
		
		return productRepo.findById(id).get().getCategory().getName();
		
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

		return  productRepo.getAllProductByCategoryname(name);
	}

	@Override
	public void affectProductToPub(long ProcId, long pubId) {
		Publicity pub = pubRepo.findById(pubId).get();
		Product p = productRepo.findById(ProcId).get();
		
		pub.setProduct(p);
		pubRepo.save(pub);
		
	}

	@Override
	public void disaffectProductToPub(long pubId) {
     
    Product p =pubRepo.findById(pubId).get().getProduct();      
       p.getPublicities().remove(pubId);	
       pubRepo.findById(pubId).get().setProduct(null);
		pubRepo.save(pubRepo.findById(pubId).get());
		}
		

	@Override
	public List<Publicity> getPubByProductId(long id) {
		return productRepo.findById(id).get().getPublicities();
	}

	@Override
	public List<Product> detailProduct(long id) {
		List<Product> p= new ArrayList<Product>();
        Product pro=productRepo.findById(id).get();
        p.add(pro);
		List<Product> l= (List<Product>) productRepo.findAll();
		for(int i=0;i<l.size();i++)
		{    
			   if(l.get(i).getCategory().getName().equals(pro.getCategory().getName()) )
			   {

				   if(p.contains(l.get(i)))
				   {
				     p.remove(l.get(i));
				   }
				      p.add(l.get(i));		  		
		   }		
		}
		return p;
	}

	@Override
	public void affectAutomatic() 
	{
		List<Product> l=(List<Product>) productRepo.findAll();
		List<Publicity> lp= (List<Publicity>) pubRepo.findAll();
		Date d= new Date(System.currentTimeMillis());
		for(int i=0;i<l.size();i++)
		{
			for(int index=0;index<lp.size();index++)
			{
				
				if(l.get(i).getCategory().getName().equals(lp.get(index).getType()) || d.after(lp.get(index).getStartDate()))
				{
					lp.get(index).setProduct(l.get(i));
					pubRepo.save(lp.get(index)); 
				}
				
			}
	
		}
		
	}




	
	/*public Product reductionForProduct(long id, int x) {
		// TODO Auto-generated method stub
		return null;
	}

	
	/*public List<Product> reductionForProductsByCategory(String name, int x) {
		List<Product> l = productRepo.getAllProductByCategoryname(name);
		int nb = l.size();
		for (int index = 0; index < nb; index++)
		{
			if(l.get(index).getDatesale().isAfter(LocalDate.now())){
				
				l.get(index).setDatesale(null);
				l.get(index).setSale(0);
				productRepo.save(l.get(index));
				
			}
			else
			{
			l.get(index).setSale((l.get(index).getPrice() - ((l.get(index).getPrice()*x)/100)));
			l.get(index).setDatesale(LocalDate.now());
			productRepo.save(l.get(index));			
		    }
			}
		return l;
	}
*/

}
