package tn.esprit.spring.Controllers;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.Entities.Comment;
import tn.esprit.spring.Entities.Complaint;
import tn.esprit.spring.Repository.ComplaintRepository;
import tn.esprit.spring.Services.IComplaintService;
@RestController
@RequestMapping("/complaint")
public class RestControlComplaint {

	@Autowired
	IComplaintService complaintService;
	
	@Autowired
	ComplaintRepository complaintrepo;
	
	@GetMapping("/getallcomplaints")
	@ResponseBody
	public List<Complaint> getComplaints() {
	 List<Complaint> list = complaintService.getallcomplaints();
	return list;
    }
	
	@PostMapping("/addcomplaint")
	@ResponseBody
	public Complaint addComplaint(@RequestBody Complaint c) {
	complaintService.addcomplaint(c);
	return c;
	}
	// http://localhost:8081/SpringMVC/servlet/modify-comment
	@PutMapping("/modifycomplaint/{complaint-id}")
	@ResponseBody
	public Complaint modifyComplaint(@PathVariable("complaint-id") String id,@RequestBody Complaint complaint) {
	complaintService.updatecomplaint(complaint);
	return complaint;
	}
	@DeleteMapping("/removecomplaint/{complaint-id}")
	@ResponseBody
	public void removeComment(@PathVariable("complaint-id") String id) {
		complaintService.removecomplaint(Long.parseLong(id));
	}
	@PutMapping("/respond/{complaint-id}")
	@ResponseBody
	public String respondtocomplaint(@PathVariable("complaint-id") String id) {
	complaintService.setresponseoncomplaint(Long.parseLong(id),"Exchange");
	return "Done";
	}
	@PutMapping("/affectreclamationtoorder/{complaint-id}/{order-id}")
	@ResponseBody
	public String respondtocomplaint(@PathVariable("complaint-id") String compid,@PathVariable("order-id") String orderid) {
	complaintrepo.affectcomplainttoorder(Long.parseLong(compid), Long.parseLong(orderid)) ;
	return "Done";
	}
	@PutMapping("/fix/{complaint-id}")
	@ResponseBody
	public String fixprod(@PathVariable("complaint-id") String id) {
		Date d =Date.valueOf(LocalDate.now());
	complaintService.fixproduct(d,Long.parseLong(id));
	return "mail with date sent";
	}
	@PutMapping("/givebackmoney/{complaint-id}/{prod-id}")
	@ResponseBody
	public double givebackmoney(@PathVariable("complaint-id") String complaintid,@PathVariable("prod-id") String prodid) {
		return complaintService.givebackmoney(Long.parseLong(prodid),Long.parseLong(complaintid));
	
	}
}