package tn.esprit.spring.Services;

import javax.transaction.TransactionScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.Entities.Cart;
import tn.esprit.spring.Entities.OrderLine;
import tn.esprit.spring.Entities.Product;
import tn.esprit.spring.Repository.CartRepository;
import tn.esprit.spring.Repository.OrderLineRepository;
import tn.esprit.spring.Repository.ProductRepository;


@Service
public class OrderLineService implements IOrderLineService {

	@Autowired
	OrderLineRepository orderlineRepo ;
	@Autowired
	ProductRepository productRepo;
	
	@Autowired
	CartRepository cartRepository ;
	
	@Transactional		
	@Override
	public OrderLine AddProductbyQuantity(int quantity, Long idPro) {
		Product p= productRepo.findById(idPro).get();	
		OrderLine OrderLineP= orderlineRepo.GetOrderLineByProduct(idPro);
		int qRest ;
		qRest= p.getQuantity()-quantity ;
		 if( p.getQuantity() == 0){
		    	System.out.println("no products available");
		    	return OrderLineP ;
		    	} 
		 if(OrderLineP != null){ 
			 if( p.getQuantity() > quantity)
			 {
				    OrderLineP.setProduct(p);  
				    OrderLineP.setQuantity(OrderLineP.getQuantity()+quantity) ;         
					p.setQuantity(qRest);
					productRepo.save(p);
					orderlineRepo.save(OrderLineP);
			 }
			 else{
				    OrderLineP.setQuantity(OrderLineP.getQuantity()+p.getQuantity());				    
				    OrderLineP.setProduct(p);	
					p.setQuantity(0);										
					//OrderLineP.setCart(cart);
					//cart.setProdpricetotal(orderlineRepo.GetTotaPrice());
				   // cartRepository.save(cart) ;
					orderlineRepo.save(OrderLineP);
			 }
			 return OrderLineP;
			 
		 }
		 else
		 {	 
			OrderLine NewOrderL= new OrderLine();		
			 if( p.getQuantity() > quantity)
			 {
				    NewOrderL.setQuantity(quantity) ;         
				    NewOrderL.setProduct(p);
				    p.setQuantity(qRest);
					productRepo.save(p);
					orderlineRepo.save(NewOrderL);
			 }
			 else{
				 NewOrderL.setQuantity(p.getQuantity());				    
				 NewOrderL.setProduct(p);	
					p.setQuantity(0);										
					//OrderP.setCart(cart);
					//cart.setProdpricetotal(orderlineRepo.GetTotaPrice());
				   // cartRepository.save(cart) ;
					orderlineRepo.save(NewOrderL);
			 }
			 return NewOrderL;
			
			 }
	  
	}


   
	@Override
	public OrderLine UpdateQuantity(int Newquantity, Long id) {
		OrderLine OL= orderlineRepo.findById(id).get();	
		Product p= productRepo.findById(OL.getProduct().getId()).get();
		
		
		int qRest ;
		
		if (p.getQuantity()<= Newquantity) {
			OL.setQuantity(OL.getQuantity()+p.getQuantity());
			p.setQuantity(0);
			productRepo.save(p);
			orderlineRepo.UpdateQuantity(Newquantity, id);
			return OL ;		
		}

			else{
		
		qRest= p.getQuantity()+(OL.getQuantity()-Newquantity) ;
		OL.setQuantity(Newquantity);
		p.setQuantity(qRest);
		productRepo.save(p);
		if (OL.getQuantity()==0) {	
			orderlineRepo.delete(OL);
		}
		orderlineRepo.UpdateQuantity(Newquantity, id);
		//pro+(old-new)
	    GetPriceTotal() ;
        orderlineRepo.save(OL);
		//cartRepository.save(ca);
		return OL ;
		}
		
	}


	@Override
	public void RemoveOrderLine(Long OrderLId) {
		
	   OrderLine OL= orderlineRepo.findById(OrderLId).get();
	   
			orderlineRepo.delete(OL);
		
		
	}
	@Override
	public OrderLine GetOrderLineByProduct(Long id) {
		// TODO Auto-generated method stub
		
		return orderlineRepo.GetOrderLineByProduct(id);
	}






	@Override
	public float GetOrderLinePrice(Long OrdLId,Long Proid) {
		// TODO Auto-generated method stub
		return orderlineRepo.GetOrderLinePrice(OrdLId,Proid);
		
	}



	@Override
	public int GetTotalQuantiy() {
		// TODO Auto-generated method stub
		return orderlineRepo.GetTotalQuantiy() ;
	}



	@Override
	public float GetPriceTotal() {
		// TODO Auto-generated method stub
		return orderlineRepo.GetTotaPrice();
	}



	@Override
	public <List>OrderLine GetOrderLinesByCart(Long cart_id) {
		// TODO Auto-generated method stub
		
		return orderlineRepo.GetOrderLinesByCart(cart_id);
	}



	@Override
	public Product GetProductByOrderLine(Long id) {
		// TODO Auto-generated method stub
		return orderlineRepo.GetProductByOrderLine(id);
	}
	
	

 

}
