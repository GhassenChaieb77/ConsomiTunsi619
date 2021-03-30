package tn.esprit.spring.Services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.json.JSONException;

import net.minidev.json.parser.ParseException;
import tn.esprit.spring.Entities.DeliveryAgent;
import tn.esprit.spring.Entities.Order;

public interface IDeliveryAgentService {

	public int adddeliveryagent(DeliveryAgent da);
	
	public int removedeliveryagent(Long id);
	
	public int updatedeliveryagent(Long id,DeliveryAgent newone);
	
	public DeliveryAgent getdeliveryagent(Long id);
	
	public List<DeliveryAgent> getalldeliveryagents();
	
	public double calculatedeliveryagentbonus(Long id);
	
	public double getmaxdistanceofanagent();
	
	public DeliveryAgent getdeliveryagentofthemonth();
	
	public double affectbonustoanagent();
	
	public float getshippingcost(Order order);
	
	public String affectordertodeliveryagent(Order order) throws Exception;
	
	public double getlongfromadress(String adress)throws Exception;
	
	public double getlatfromadress(String adress)	throws  Exception;
	
	public double distancebetweenadresses(String adress1,String adress2) throws  Exception;
	
	public DeliveryAgent getclosestdeliveryagent(Order o) throws Exception;

	public float calculatefraislivraison(Order o)throws Exception;
	
	public void setalldistancetozero();
	
	public double getfarestorderandaffectdistance(DeliveryAgent da)throws Exception;
	
	public String sendsmstouser(Order o);
}