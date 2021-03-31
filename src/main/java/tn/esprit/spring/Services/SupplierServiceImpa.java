package tn.esprit.spring.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Entities.Supplier;
import tn.esprit.spring.Entities.User;
import tn.esprit.spring.Repository.SupplierRepository;
@Service
public class SupplierServiceImpa implements SupplierService {

	@Autowired
	SupplierRepository ss;

	
	@Override
	public void SaveStock(Supplier s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void DeleteStock(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Update(Supplier s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Supplier> getAlluser() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public String best()
	{
		Supplier best = new Supplier("test","test@test.com");
		String ch="test";
		List<Supplier> Suppliers  = (List<Supplier>) ss.findAll();
		for(Supplier s : Suppliers)
		{
			System.err.println(s.getStocks().size());
			if(best.getStocks()==null)
			{
				best=s;
				
			}
			
			 else if (s.getStocks().size()> best.getStocks().size())
			{
				 best=s;
			}
		
		}
		
		
		
		return best.getName();
		
	}
	

}
