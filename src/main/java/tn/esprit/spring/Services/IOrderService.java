package tn.esprit.spring.Services;


import tn.esprit.spring.Entities.*;
public interface IOrderService {
	
	public void saveOrder(long cart_id,long user_id,int quatity,float price,String paymentMethod);
	
	public void deleteOrder(Order order, long user_id);
	

}
