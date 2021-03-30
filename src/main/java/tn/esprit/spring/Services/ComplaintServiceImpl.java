package tn.esprit.spring.Services;

import java.sql.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.Entities.Complaint;
import tn.esprit.spring.Entities.Order;
import tn.esprit.spring.Entities.Product;
import tn.esprit.spring.Entities.User;
import tn.esprit.spring.Repository.ComplaintRepository;
import tn.esprit.spring.Repository.OrderRepository;
import tn.esprit.spring.Repository.ProductRepository;
import tn.esprit.spring.Repository.UserRepository;
@Service
public class ComplaintServiceImpl implements IComplaintService {
	
	@Autowired 
	ComplaintRepository cr;
	
	@Autowired 
	ProductRepository pr;
	
	@Autowired 
	OrderRepository or;
	
	@Autowired 
	UserRepository ur;
	
	@Override
	public int addcomplaint(Complaint c) {
		cr.save(c);
		return 0;
	}

	@Override
	public int updatecomplaint(Complaint newone) {
		cr.save(newone);
		return 0;
	}

	@Override
	public int removecomplaint(Long id) {
		cr.deleteById(id);
		return 0;
	}
	
	@Override
	public Complaint getcomplaint(Long id) {
		
		return cr.findById(id).get();
	}

	@Override
	public List<Complaint> getallcomplaints() {
		
		return (List<Complaint>) cr.findAll();
	}

	@Override
	public void setresponseoncomplaint(long id, String response) {
		cr.respondtocomplaint(id,response);
		
	}

	@Override
	public double givebackmoney(long prodid,long complaintid) {
		
		cr.respondtocomplaint(complaintid,"money back");
		double price=0;
		User user=cr.findById(complaintid).getOrder().getUser();
		for(Product p :pr.findAll())
		{
			if(p.getId()==prodid)
			{
				price=p.getPrice();
			}
		}
		ur.updateuserbalance(user.getId(),user.getBalance()+price);
		return price;
	}


	@Override
	public boolean fixproduct(Date d,long complaintid) {
		cr.respondtocomplaint(complaintid,"product will be fixed");
		Properties prop = new Properties();
		prop.put("mail.smtp.auth","true");
		prop.put("mail.smtp.starttls.enable","true");
		prop.put("mail.smtp.host","smtp.gmail.com");
		prop.put("mail.smtp.port","587");
		String myaccount="mohamedaziz.mathlouthi@esprit.tn";
		String password="abdou15121963bab";
		Session session=Session.getInstance(prop, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication(myaccount, password);
			}
		});
		try{
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myaccount));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(cr.findById(complaintid).getOrder().getUser().getEmail()));
			message.setSubject("Respond to your complaint");
			message.setText("Your product is fixed you can get it at our store at this date :"+d);
			Transport.send(message);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		return true;
	}

	

	

}
