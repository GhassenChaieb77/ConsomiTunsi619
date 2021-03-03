package tn.esprit.spring.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Entities.Category;
import tn.esprit.spring.Entities.Product;
import tn.esprit.spring.Repository.CategoryRepository;
import tn.esprit.spring.Repository.ProductRepository;

@Service
public class CategoryService implements ICategoryService{

	@Autowired
	CategoryRepository categoryRepo;
	
	@Autowired
	ProductRepository productRepo;
	
	@Autowired
	ProductService productser;
	

	@Override
	public String addCategory(Category c) {
		categoryRepo.save(c);
		return c.getName();
	}

	@Override
	public List<Category> dispalyCategories() {
		return (List<Category>) categoryRepo.findAll();
		
		
	}

	@Override
	public void deleteCategory(long id ) {
		categoryRepo.deleteById(id);
		
	}

	@Override
	public Category updateCategory(Category c) {
		return categoryRepo.save(c);
	}

	@Override
	public void affectCategoryToProduct(long ProcId, long cateId) {
		Category c = categoryRepo.findById(cateId).get();
		Product p = productRepo.findById(ProcId).get();
		
		p.setCategory(c);
		productRepo.save(p);
		
	}

	@Override
	public void disaffectCategoryToProduct(long ProcId, long cateId) {
		Category c = categoryRepo.findById(cateId).get();

		int nb = c.getProducts().size();
		for(int index = 0; index < nb; index++)
		{
			if(c.getProducts().get(index).getId() == ProcId)
			{
				c.getProducts().remove(index);
				productRepo.save(productRepo.findById((long) index).get());
				break;
			}
		}
		
	}

	@Override
	public List<Product> getProductsByCategoryName(String name) {

      return categoryRepo.getCategotyByNameJPQL(name).getProducts();


	}
}