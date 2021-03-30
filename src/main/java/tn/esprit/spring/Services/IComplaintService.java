package tn.esprit.spring.Services;

import java.sql.Date;
import java.util.List;

import tn.esprit.spring.Entities.Complaint;
import tn.esprit.spring.Entities.Product;
import tn.esprit.spring.Entities.User;
import tn.esprit.spring.Entities.Order;

public interface IComplaintService {
	
	public int addcomplaint(Complaint c);
	
	public int updatecomplaint(Complaint newone);
	
	public int removecomplaint(Long id);
	
	public Complaint getcomplaint(Long id);
	
	public List<Complaint> getallcomplaints();
	
	public void setresponseoncomplaint(long id,String response);
	
	public double givebackmoney(long prodid,long complaintid);
	
	public boolean fixproduct(Date date,long complaintid);
}
