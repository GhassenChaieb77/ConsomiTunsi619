package tn.esprit.spring.aspect;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import tn.esprit.spring.Services.IProductService;
import tn.esprit.spring.Services.IPublicityService;

@Component
public class SchedulerPublicity {
	
	@Autowired
	IPublicityService ipubService;
	
	@Autowired
	IProductService iprodService;
	
	@Scheduled(fixedRate =86400000)
	   public void fixedRateSch() {
	       
	          iprodService.affectAutomatic();
	          ipubService.deletePub();
	   }
}
