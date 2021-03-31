package tn.esprit.spring.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import tn.esprit.spring.Entities.Supplier;
@Service
public interface SupplierService {
	
	
	

    public void SaveStock(Supplier s);
		
	public void DeleteStock (long id);
	
	public void Update(Supplier s);
	
	 public List<Supplier> getAlluser();

}
