package tn.esprit.spring.Services;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.query.Param;

import tn.esprit.spring.Entities.*;

public interface IOrderService {
	
	public Order CreateOrder (Long user_id,String Adresse,String methode) ;
	public Order checkoutOff(Long user_id,Long order_id);
	public List<Order> getOrdersByUser(Long user_id);
	public Order getOrderByCartId(Long cart_id);
	public List<Order> getOrdersByYear(int year);
	public List <Order> getOrdersByMonth(int month);
	public Order AddOrderLine(Long user_id);
	public void checkoutOnline(Long user_id,Long order_id,float amount);
	public String getCouponCode(Long user_id);
	public Order CouponDiscount(Long user_id,Long order_id);
	public void checkoutOnlinewithCoupon(Long user_id,Long order_id,float amount);

  
}
