package tn.esprit.spring.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.Entities.DeliveryAgent;
import tn.esprit.spring.Services.IDeliveryAgentService;
import tn.esprit.spring.Services.IOrderService;

@RestController
@RequestMapping("/deliveryagent")
public class RestControlDeliveryAgent {

	@Autowired
	IDeliveryAgentService  deliveryaganetService;
	
	@Autowired
	IOrderService orderservice;
	
	@GetMapping("/getalldeliveryagents")
	@ResponseBody
	public List<DeliveryAgent> getDeliveryAgents() {
	 List<DeliveryAgent> list = deliveryaganetService.getalldeliveryagents();
	return list;
    }
	@PostMapping("/adddeliveryagent")
	@ResponseBody
	public DeliveryAgent addDeliveryAgent(@RequestBody DeliveryAgent da) {
	deliveryaganetService.adddeliveryagent(da);
	return da;
	}
	@PutMapping("/modifydeliveryagent/{deliveryagent-id}")
	@ResponseBody
	public DeliveryAgent modifyDeliveryAgent(@PathVariable("deliveryagent-id") String id,@RequestBody DeliveryAgent deliveryagent) {
		deliveryaganetService.updatedeliveryagent(Long.parseLong(id),deliveryagent);
	return deliveryagent;
	}
	@DeleteMapping("/removedeliveryagent/{deliveryagent-id}")
	@ResponseBody
	public void removeComment(@PathVariable("deliveryagent-id") String id) {
		deliveryaganetService.removedeliveryagent(Long.parseLong(id));
	}
	@GetMapping("/getdeliveryagentofmonth")
	@ResponseBody
	public DeliveryAgent getDeliveryofmonth() {
	return deliveryaganetService.getdeliveryagentofthemonth();
	}
	@PutMapping("/affectbonus")
	@ResponseBody
	public String affectbonustodeliveryagent() {
		deliveryaganetService.affectbonustoanagent();
		deliveryaganetService.setalldistancetozero();
		return "bonus affected to all agents";
	}
	@PutMapping("/affectagenttoorder/{order-id}")
	@ResponseBody
	public String affectdeliveryagenttoorder(@PathVariable("order-id") String id) throws Exception {
		return deliveryaganetService.affectordertodeliveryagent(orderservice.getorder(Long.parseLong(id)));
	 //deliveryaganetService.sendsmstouser(orderservice.getorder(Long.parseLong(id)));
	 
	}
	@GetMapping("/distance/{adress1}/{adress2}")
	@ResponseBody
	public double distance(@PathVariable("adress1") String adress1,@PathVariable("adress2") String adress2) throws Exception {
	return deliveryaganetService.distancebetweenadresses(adress1,adress2);
	}
	@GetMapping("/closest/{id}")
	@ResponseBody
	public DeliveryAgent distance(@PathVariable("id")String id) throws Exception {
	return deliveryaganetService.getclosestdeliveryagent(orderservice.getorder(Long.parseLong(id)));
	}
	@GetMapping("/adress")
	@ResponseBody
	public double adress() throws Exception {
	return deliveryaganetService.getlatfromadress("korba");
	}
	@GetMapping("/fraislivraison/{id}")
	@ResponseBody
	public double calculatefrais(@PathVariable("id")String id) throws Exception {
	return deliveryaganetService.calculatefraislivraison(orderservice.getorder(Long.parseLong(id)));
	}
	/*@GetMapping("/sms/{id}")
	@ResponseBody
	public String sendsms(@PathVariable("id")String id) throws Exception {
	return deliveryaganetService.sendsmstouser(orderservice.getorder(Long.parseLong(id)));
	}*/
	@GetMapping("/getbyid/{id}")
	@ResponseBody
	public DeliveryAgent getbyid(@PathVariable("id")String id) throws Exception {
	return deliveryaganetService.getdeliveryagent(Long.parseLong(id));
	}
}