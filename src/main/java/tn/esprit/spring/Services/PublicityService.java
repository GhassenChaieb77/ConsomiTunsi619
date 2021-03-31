package tn.esprit.spring.Services;

import java.util.Date;
import java.util.List;

import org.apache.catalina.valves.CrawlerSessionManagerValve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Entities.Product;
import tn.esprit.spring.Entities.Publicity;
import tn.esprit.spring.Entities.User;
import tn.esprit.spring.Repository.CartRepository;
import tn.esprit.spring.Repository.CategoryRepository;
import tn.esprit.spring.Repository.OrderRepository;
import tn.esprit.spring.Repository.ProductRepository;
import tn.esprit.spring.Repository.PublicityRepository;

@Service
public class PublicityService implements IPublicityService {
	
	@Autowired
	PublicityRepository pubRepo;
	
	
	
	@Autowired
	ProductRepository productRepo;
	
	@Autowired
	CategoryRepository categoryRepo;

	@Override
	public Publicity addPublicity(Publicity p) {
		return pubRepo.save(p);
	}

	@Override
	public List<Publicity> dispalyPublicities() {
		return (List<Publicity>) pubRepo.findAll();
	}

	@Override
	public void deletePublicity(long id) {
		pubRepo.deleteById(id);
		
	}

	@Override
	public Publicity updatePublicity(Publicity p) {
		return pubRepo.save(p);
	}

	@Override
	public Publicity getPublicityById(long id) {
		return pubRepo.findById(id).get();
	}
	
	@Override
	public void deletePub() {
		Date d = new Date(System.currentTimeMillis());
		List<Publicity> l= (List<Publicity>) pubRepo.findAll();
		
		for(int i=0;i<l.size();i++)
		{
			if(d.after(l.get(i).getEndDate()))
				pubRepo.delete(l.get(i));
		}
		
	}




}
