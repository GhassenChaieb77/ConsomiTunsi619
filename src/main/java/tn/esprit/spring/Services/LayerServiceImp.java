package tn.esprit.spring.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Entities.Category;
import tn.esprit.spring.Entities.Layer;
import tn.esprit.spring.Entities.Product;
import tn.esprit.spring.Entities.Supplier;
import tn.esprit.spring.Entities.User;
import tn.esprit.spring.Repository.CategoryRepository;
import tn.esprit.spring.Repository.LayerRepository;
import tn.esprit.spring.Repository.ProductRepository;
import tn.esprit.spring.Repository.UserRepository;
@Service
public class LayerServiceImp implements LayerService {

	@Autowired
	LayerRepository r;
	@Autowired
	CategoryRepository c;
	@Autowired
	ProductRepository p;
	 List<Product> list= new ArrayList<>();
	
	@Override
	public Layer saveLayer(Layer l) {
     return r.save(l);	
	}

	@Override
	public void DeleteLayer(long id) {
         r.deleteById(id);		
	}

	@Override
	public void Update(Layer l) {
		r.save(l);		
	}

	@Override
	public List<Layer> getAllLayer() {
		List<Layer> l  = (List<Layer>) r.findAll();
	
		
		return l;	
		
	}

	@Override
	public Layer getLayerById(long id) {
		return r.findById(id);
	}
	
	
	
	public List<Product> GetP(long id)
	{
		Layer l=r.findById(id);
		
		
		return l.getCategory().getProducts();
		
	}
	
	
	public void Setcategory(long id,long id2)
	{
		Layer l=r.findById(id);
		Category c1=c.findById(id2).get();
		l.setCategory(c1);
		System.err.println(l.getCategory().getName());
		r.save(l);
		
		
	}
	
	public void setprod(long id,long id2)
	{
		Layer l=r.findById(id);
		Product pr = p.findById(id2).get();
		list.add(pr);
		l.setProducts(list);
		r.save(l);
		
		
	}

	
	public void AutoLayer(long id)
	{
		int n=0;
		Category c1=c.findById(id).get();
		List<Product> p =c1.getProducts();
		Layer l=r.findByCategoryId(id);
		List<Product> p2= l.getProducts();
		if(l.getCapacityNow()<l.getCapacity()) {
			for(Product s : p)
			{ 
				if(!p2.contains(s))
					
						{ 
					if(s.getQuantity()!=0 && s.getQuantity() <l.getCapacity())
						{
					System.err.println(s.getName());
					p2.add(s);
					n=n+s.getQuantity();
						}
						}
		     }
		}
		l.setProducts(p2);
		l.setCapacityNow(n);
		r.save(l);
		p2.clear();
		
	}
	


}
