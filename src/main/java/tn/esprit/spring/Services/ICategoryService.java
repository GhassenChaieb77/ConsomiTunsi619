package tn.esprit.spring.Services;

import java.util.List;

import tn.esprit.spring.Entities.Category;
import tn.esprit.spring.Entities.Product;

public interface ICategoryService {

	public long addCategory(Category c);
	public List<Category> dispalyCategories();
	public void deleteCategory(long id );
	public Category updateCategory(Category c);
	public void affectCategoryToProduct(long ProcId, long cateId);
	public void disaffectCategoryToProduct(long ProcId, long cateId);
	public String getCategotyByName(String name);
	
	
}