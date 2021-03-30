package tn.esprit.spring.Services;

import tn.esprit.spring.Entities.*;

public interface IBillService {

	
	public Bill CreateAndAffectBillToOrder (Long order_id, Long user_id) ;

	
}
