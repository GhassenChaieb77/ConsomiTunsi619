package tn.esprit.spring.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Entities.Bill;
import tn.esprit.spring.Entities.Cart;
import tn.esprit.spring.Entities.Order;
import tn.esprit.spring.Entities.OrderLine;
import tn.esprit.spring.Entities.User;
import tn.esprit.spring.Repository.BillRepository;
import tn.esprit.spring.Repository.CartRepository;
import tn.esprit.spring.Repository.OrderLineRepository;
import tn.esprit.spring.Repository.OrderRepository;
import tn.esprit.spring.Repository.UserRepository;

@Service
public class BillService implements IBillService{

	@Autowired
	UserRepository userRepository ;
	@Autowired
	BillRepository billRepository ;

	@Autowired
	OrderLineRepository orderlineRepo ;
	
	@Autowired
	OrderRepository orderRepository ;
	
	@Override
	public Bill CreateAndAffectBillToOrder(Long order_id,Long user_id) {
		// TODO Auto-generated method stub
		Order or = orderRepository.findById(order_id).get();
		//Bill bill = billRepository.findById(bill_id).get() ;
       User user = userRepository.findById(user_id).get();
		
      
	 	 Bill bill = new Bill();
		 bill.setAdress(or.getAddress());
		 bill.setName("TO "+user.getLastName()+""
		    		+ " "+user.getFirstName());
	
			bill.setOrder(or);
			
		
        billRepository.save(bill);
      //  or.setBill(bill);
		//orderRepository.save(or);
		
      
		return bill;
		
	}

	

}
