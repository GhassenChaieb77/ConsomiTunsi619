package tn.esprit.spring.Services;

import java.util.List;

import tn.esprit.spring.Entities.Stock;

public interface StockService {
	
	
	
    public void SaveStock(Stock s);
		
	public void DeleteStock (long id);
	
	public void Update(Stock s);
	
	 public List<Stock> getAllStock();


	

}
