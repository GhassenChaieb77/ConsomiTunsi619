package tn.esprit.spring.Services;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.query.Param;

import tn.esprit.spring.Entities.*;

public interface IOrderService {
	
	public Order CreateOrder (String Adresse,String methode) ;
	public Order checkoutOff(Long order_id);
	public List<Order> getOrdersByUser();
	public Order getOrderByCartId(Long cart_id);
	public List<Order> getOrdersByYear(int year);
	public List <Order> getOrdersByMonth(int month);
	public Order AddOrderLine();
	public void checkoutOnline(Long order_id,float amount);
	public String getCouponCode();
	public Order CouponDiscount(Long order_id);
	public void checkoutOnlinewithCoupon(Long order_id,float amount);
	public List<Bill> getUserBill();

  
}
