package tn.esprit.spring.Services;

import org.springframework.data.repository.query.Param;

import tn.esprit.spring.Entities.*;

public interface IOrderLineService {
	
	public OrderLine AddProductbyQuantity(int quantity,Long idPro); 
	public OrderLine UpdateQuantity(int Newquantity, Long id);
	public OrderLine GetOrderLineByProduct(Long id);
	public <List>OrderLine GetOrderLinesByCart(Long cart_id);
	public Product GetProductByOrderLine(Long id);
	public float GetOrderLinePrice(Long OrdLId,Long Proid);
	public int GetTotalQuantiy();
	public float GetPriceTotal();
	public void RemoveOrderLine(Long OrderLId);
}
