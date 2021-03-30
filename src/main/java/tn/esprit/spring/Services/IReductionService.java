package tn.esprit.spring.Services;

import java.util.Date;
import java.util.List;

import tn.esprit.spring.Entities.Product;
import tn.esprit.spring.Entities.Reduction;

public interface IReductionService {

    public List<Reduction> displayReductions();
	public Reduction affectReductionToProduct(long id, Date start,Date end, Reduction r);
	public void disaffect(long id);
	public List<Product> affectReductionToCategory(String name, Date start,Date end, Reduction r);
	
	public List<Reduction> getReductions(long id);
	public Product getProductsByReduction(Date start,Date end);
	public List<Product> detailll(long id);
	
	
	
	
}
