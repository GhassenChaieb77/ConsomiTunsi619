package tn.esprit.spring.Services;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Entities.Product;
import tn.esprit.spring.Entities.Publicity;
import tn.esprit.spring.Entities.Reduction;
import tn.esprit.spring.Entities.ReductionKey;
import tn.esprit.spring.Repository.CategoryRepository;
import tn.esprit.spring.Repository.ProductRepository;
import tn.esprit.spring.Repository.ReductionRepository;

@Service
public class ReductionService implements IReductionService {
	
	@Autowired
	ProductRepository productRepo;
	
	@Autowired
	CategoryRepository categoryRepo;
	
	@Autowired
	ReductionRepository redRepo;
	

	@Override
	public Reduction affectReductionToProduct(long id, Date start,Date end, Reduction r) {
		
		
		Product p = productRepo.findById(id).get();
		  ReductionKey redKey = new ReductionKey();
	      redKey.setStartDate(start);
	      redKey.setEndDate(end);
	      if(start.after(end))
	      {
	    	  r.setReductionKey(null);
	      }
	      else
	      {
	      r.setReductionKey(redKey);
		  r.setSale(p.getPrice() - ((p.getPrice()*r.getReduction())/100));
		  r.setProduct(p);
		  redRepo.save(r);
	      }
	return r; 
	}
	
	@Override
	public List<Product> affectReductionToCategory(String name,Date start,Date end,Reduction r) {
		List<Product> l = productRepo.getAllProductByCategoryname(name);
		 ReductionKey redKey = new ReductionKey();
	      redKey.setStartDate(start);
	      redKey.setEndDate(end);
		int nb = l.size();
		for (int index = 1; index < nb; index++)
		{
			
		      r.setReductionKey(redKey);
			  r.setSale(l.get(index).getPrice() - ((l.get(index).getPrice()*r.getReduction())/100));
			  r.setProduct(l.get(index));
			  redRepo.save(r);
			
		}
		return l;
	}


	@Override
	public Product getProductsByReduction(Date start, Date end) {
		
		Product p= new Product();
		List<Reduction> r=redRepo.getReductionByDate();
		for (int i=0;i<r.size();i++)
		{
			if(r.get(i).getReductionKey().getStartDate().equals(start))
			p=r.get(i).getProduct();
		}
	
		return p;
	}



	@Override
	public List<Product> detailll(long id) {
		List<Product> p= new ArrayList<Product>();
        Product pro=productRepo.findById(id).get();
        List<Reduction> lr= (List<Reduction>) redRepo.findAll();
		Reduction r= redRepo.getReductionByProductId(id);
        p.add(pro);
		for(int i=0;i<lr.size();i++)
		{    
			   if(lr.get(i).getReduction()==r.getReduction() || lr.get(i).getProduct().getCategory().getName().equals(pro.getCategory().getName()))
			   {
				   if(p.contains(lr.get(i).getProduct()))
				   {
				     p.remove(lr.get(i).getProduct());
				   }
				      p.add(lr.get(i).getProduct());						
			  	}		
		}
        
		return p;
	}


	@Override
	public List<Reduction> displayReductions() {
	
		return (List<Reduction>) redRepo.findAll();
	}


	@Override
	public List<Reduction> getReductions(long id) {
		return redRepo.getReductionsByProductId(id);
	}

	@Override
	public void disaffect(long id) {
		// TODO Auto-generated method stub
		
	}




}
