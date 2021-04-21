package tn.esprit.spring.Services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

import tn.esprit.spring.Entities.DeliveryAgent;
import tn.esprit.spring.Entities.Order;
import tn.esprit.spring.Entities.User;
import tn.esprit.spring.Repository.DeliveryAgentRepository;
import tn.esprit.spring.Repository.OrderRepository;
@Service
public class DeliveryAgentServiceImpl implements IDeliveryAgentService {

	@Autowired 
	DeliveryAgentRepository deliveryagantrepo;
	
	@Autowired 
	OrderRepository orderrepo;
	
	@Override
	public int adddeliveryagent(DeliveryAgent da) {
		deliveryagantrepo.save(da);
		return 0;
	}

	@Override
	public int removedeliveryagent(Long id) {
	
			deliveryagantrepo.delete(deliveryagantrepo.findById(id).get());
			return 0;
		
		
	}

	@Override
	public int updatedeliveryagent(Long id, DeliveryAgent newone) {
			deliveryagantrepo.save(newone);
			return 0;
		
	}

	@Override
	public DeliveryAgent getdeliveryagent(Long id) {
		
			return deliveryagantrepo.findById(id).get();
	}

	@Override
	public List<DeliveryAgent> getalldeliveryagents() {
		return (List<DeliveryAgent>) deliveryagantrepo.findAll();
	}

	@Override
	public double calculatedeliveryagentbonus(Long id) {
		
		for(DeliveryAgent da:getalldeliveryagents())
		{
			if(da.getId()==id)
			{
				return  da.getTraveledpath()*0.1;			
			}
			
		}
		return 0;
	}

	@Override
	public double getmaxdistanceofanagent() {
		
		double max = getalldeliveryagents().get(0).getTraveledpath();
		for(int i=1;i<getalldeliveryagents().size();i++)
		{
			if(getalldeliveryagents().get(i).getTraveledpath()>max)
			{
				max=getalldeliveryagents().get(i).getTraveledpath();
			}
		}
		return max;
	}

	@Override
	public DeliveryAgent getdeliveryagentofthemonth() {
		
		for(DeliveryAgent da:getalldeliveryagents())
		{
			if(da.getTraveledpath()==getmaxdistanceofanagent())
			{
				return da;
			}
		}
		return null;
	}

	@Override
	public double affectbonustoanagent(){
		for(DeliveryAgent da:getalldeliveryagents())
		{
			if(getdeliveryagentofthemonth().getId()==da.getId())
			{
				deliveryagantrepo.updatebonustodeliveryagent(da.getId(),getdeliveryagent(da.getId()).getBalance()+calculatedeliveryagentbonus(da.getId())+200);
			
			}
			else
			{
				deliveryagantrepo.updatebonustodeliveryagent(da.getId(),getdeliveryagent(da.getId()).getBalance()+calculatedeliveryagentbonus(da.getId()));
			
			}
		
		}
	
		return 0;
	}

	@Override
	public float getshippingcost(Order order) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public double getlongfromadress(String adress) throws Exception
	{
		String url = "http://api.positionstack.com/v1/forward"
				+ "?access_key=fa925d046424591ae5c55c34b8b1d4fd"
				+"&country=TN"
				+"&limit=1&query="+adress;
	     URL obj = new URL(url);
	     HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	     // optional default is GET
	     con.setRequestMethod("GET");
	     //add request header
	     con.setRequestProperty("User-Agent", "Mozilla/5.0");
	     
	     int responseCode = con.getResponseCode();
	     
	     //System.out.println("\nSending 'GET' request to URL : " + url);
	     //System.out.println("Response Code : " + responseCode);
	     BufferedReader in = new BufferedReader(
	             new InputStreamReader(con.getInputStream()));
	     String inputLine;
	     StringBuffer response = new StringBuffer();
	     while ((inputLine = in.readLine()) != null) {
	     	response.append(inputLine);
	     }
	     in.close();
	     //print in String
	     //System.out.println(response.toString());
	     //Read JSON response and print
	     JSONObject json = new JSONObject (response.toString ());
	     try{
	    	 Object longitude =json.getJSONArray("data").getJSONObject(0).get("longitude");
	    	 return Double.parseDouble(longitude.toString());
	     }
	     catch(JSONException ex)
	     {
	    	 System.out.println("API Response was null");
	     }
	     return 0;
	}
	@Override
	public double getlatfromadress(String adress) throws Exception
	{
		String url = "http://api.positionstack.com/v1/forward"
				+ "?access_key=fa925d046424591ae5c55c34b8b1d4fd"
				+"&country=TN"
				+"&limit=1&query="+adress;
	     URL obj = new URL(url);
	     HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	     Thread.sleep(25);
	     // optional default is GET
	     con.setRequestMethod("GET");
	     //add request header
	     con.setRequestProperty("User-Agent", "Mozilla/5.0");
	     
	     int responseCode = con.getResponseCode();
	     
	     //System.out.println("\nSending 'GET' request to URL : " + url);
	     //System.out.println("Response Code : " + responseCode);
	     BufferedReader in = new BufferedReader(
	             new InputStreamReader(con.getInputStream()));
	     String inputLine;
	     StringBuffer response = new StringBuffer();
	     while ((inputLine = in.readLine()) != null) {
	     	response.append(inputLine);
	     }
	     in.close();
	     //print in String
	     //System.out.println(response.toString());
	     //Read JSON response and print
	     JSONObject json = new JSONObject (response.toString ());
	     try{
	    	 Object latitude =json.getJSONArray("data").getJSONObject(0).get("latitude");
	    	 return Double.parseDouble(latitude.toString());
	     }
	     catch(JSONException ex)
	     {
	    	 System.out.println("API Response was null");
	     }
	     return 0;
	}
	
	@Override
	public double distancebetweenadresses(String adress1,String adress2)throws Exception
	{
		 // Radius of the earth
		double lat1=getlatfromadress(adress1);
		double lat2=getlatfromadress(adress2);
		double lon1=getlongfromadress(adress1); 
		double lon2=getlongfromadress(adress2);
		double newlat1 = Math.toRadians(lat1); 
		double newlong1 = Math.toRadians(lon1); 
		double newlat2 = Math.toRadians(lat2); 
		double newlong2 = Math.toRadians(lon2); 
	      while(lat1!=0 && lat2!=0 && lon1!=0 && lon2!=0)
	      {
	    // Haversine Formula 
	     double dlong = newlong2 - newlong1; 
	     double dlat = newlat2 - newlat1; 
	  
	     double ans = Math.pow(Math.sin(dlat / 2), 2) +  
	                          Math.cos(lat1) * Math.cos(lat2) *  
	                          Math.pow(Math.sin(dlong / 2), 2); 
	  
	    ans = 2 * Math.asin(Math.sqrt(ans)); 
	  
	    // Radius of Earth in  
	    // Kilometers, R = 6371 
	    // Use R = 3956 for miles 
	     double r = 6371; 
	      
	    // Calculate the result 
	    ans = ans * r; 
	  
	    return ans; 
	    }
	      return distancebetweenadresses(adress1, adress2);
	}
		
	@Override
	public DeliveryAgent getclosestdeliveryagent(Order o) throws Exception
	{
		List<DeliveryAgent> availableagents=new ArrayList<>();
		for(DeliveryAgent da:getalldeliveryagents())
		{
			if(da.isIsavailable())
			{
				availableagents.add(da);
			}
		}
		double min=distancebetweenadresses(availableagents.get(0).getPosition(), o.getAddress());
		for(int i=1;i<availableagents.size();i++)
		{
			if(distancebetweenadresses(availableagents.get(i).getPosition(),o.getAddress())<min)
			{
				min=distancebetweenadresses(availableagents.get(i).getPosition(),o.getAddress());
				return availableagents.get(i);
			}
		}
		return availableagents.get(0);
		
	}
	@Override
	public String affectordertodeliveryagent(Order order) throws Exception {
		DeliveryAgent da = getclosestdeliveryagent(order);
		if(da!=null)
		{
			if(da.getOrders().size()==2)
			{
					deliveryagantrepo.affectDeliveryAgenttoorder(da.getId(), order.getId());
					deliveryagantrepo.updateDeliveryAgentAvailabilityById(false,da.getId());
					Order or=orderrepo.getorderbyid(order.getId());
					sendsmstouser(or,da.getFirstname(),da.getLastname(),da.getPhonenumber());
					//sendsmstouser(orderrepo.getorderbyid(order.getId()));			
					getfarestorderandaffectdistance(da);
					return "affected full";
			}
			else if(da.getOrders().size()<=1)
			{
				deliveryagantrepo.affectDeliveryAgenttoorder(da.getId(), order.getId());
				Order or=orderrepo.getorderbyid(order.getId());
				sendsmstouser(or,da.getFirstname(),da.getLastname(),da.getPhonenumber());
				//sendsmstouser(orderrepo.getorderbyid(order.getId()));
				return "affected not full";
			}
			return "no close";
		}
		else
		{
			return "error occured";
		}
		
	}

	@Override
	public float calculatefraislivraison(Order o) throws Exception {
		double distance = distancebetweenadresses(o.getAddress(),o.getDeliveryagent().getPosition());
		if(distance<100)
		{
			return 10;
		}
		else if(distance>100 && distance <250)
		{
			return 20;
		}
		else
			return 30;
	}

	@Override
	public void setalldistancetozero() {
		List<DeliveryAgent> deliveryagents = getalldeliveryagents();
		for(DeliveryAgent da : deliveryagents)
		{
			deliveryagantrepo.updatedistanceofdeliveryagent(da.getId(),0);
		}
	}

	@Override
	public double getfarestorderandaffectdistance(DeliveryAgent da) throws Exception {
		List<Order> orders = da.getOrders();
		double maxdistance = distancebetweenadresses(da.getPosition(),orders.get(0).getAddress());
		for(int i=1;i<orders.size();i++)
		{
			if(distancebetweenadresses(orders.get(i).getAddress(),da.getPosition())>maxdistance)
			{
				maxdistance=distancebetweenadresses(orders.get(i).getAddress(),da.getPosition());
			}
		}
		deliveryagantrepo.updatedistanceofdeliveryagent(da.getId(),da.getTraveledpath()+maxdistance);
		return maxdistance;
	}

	@Override
	public String sendsmstouser(Order o,String f,String l,long a) {
		
		Twilio.init("AC0b6824a932917c38425c42a5b4e6f445","a174aae925ce9c6e3309a4d8429ad1f9");
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+21656096624"),
                new com.twilio.type.PhoneNumber("+12342310318"),
                "your order is being shipped by "+f+l+" you can get in touch with him by contacting : "+a)
            .create();
		return message.getSid();
        
	}
	
	}


