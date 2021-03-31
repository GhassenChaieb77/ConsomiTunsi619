package tn.esprit.spring.Services;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import javax.mail.internet.MimeMessage;

import tn.esprit.configuration.Util;
import tn.esprit.spring.Entities.Category;
import tn.esprit.spring.Entities.Layer;
import tn.esprit.spring.Entities.Product;
import tn.esprit.spring.Entities.Stock;
import tn.esprit.spring.Entities.Supplier;
import tn.esprit.spring.Entities.User;
import tn.esprit.spring.Repository.CategoryRepository;
import tn.esprit.spring.Repository.LayerRepository;
import tn.esprit.spring.Repository.ProductRepository;
import tn.esprit.spring.Repository.StockRepository;
import tn.esprit.spring.Repository.SupplierRepository;
import tn.esprit.spring.Repository.UserRepository;
@Service
public class StockServiceImpa implements StockService {
	@Autowired
	StockRepository stockrepository;
	@Autowired
	ProductServiceImpa ps;
    @Autowired
    private JavaMailSender sender;
    @Autowired
    SupplierRepository s;
    @Autowired
    CategoryRepository c;
    @Autowired
	ProductRepository p1;
    @Autowired
    LayerRepository l1;
    
    Util poop;
    
    List<Product> list= new ArrayList<>();
	@Override
	public void SaveStock(Stock s2) {
		stockrepository.save(s2);		
	}

	@Override
	public void DeleteStock(long id) {
		stockrepository.deleteById(id);		
	}

	@Override
	public void Update(Stock s1) {
		stockrepository.save(s1);	
	}

	@Override
	public List<Stock> getAllStock() {
		List<Stock> Stocks  = (List<Stock>) stockrepository.findAll();
		for(Stock s2 : Stocks)
		{
			System.out.print(1);
		
		}
		
		return Stocks;
	

	}	
	
	
	public void autostock (long id) throws javax.mail.MessagingException
	{
		List<Product> p = ps.getAllProduct();
		
		Supplier s1=s.findById(id);
		for (int i = 0; i < p.size(); i++) 
		{
			if (p.get(i).getQuantity()==0)
			{
        
				MimeMessage message = (MimeMessage) sender.createMimeMessage();
		        MimeMessageHelper helper = new MimeMessageHelper((javax.mail.internet.MimeMessage) message);

		        helper.setTo("4d5380d665-ad7b6e@inbox.mailtrap.io");
				helper.setText("we need more ");
				helper.setSubject("We are out of stock");
		        sender.send((javax.mail.internet.MimeMessage) message);
		        System.err.println("test");
		      p.get(i).setQuantity(500);
		      p1.save(p.get(i));
		      
		        LocalDateTime localDateTime = LocalDateTime.now();

		        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneOffset.systemDefault());

		        Instant instant = zonedDateTime.toInstant();

		        Date date = Date.from(instant);
       Stock s=new Stock(500,500*p.get(i).getPrice(),date,date);
       s.setProducts(p);
       stockrepository.save(s);
			
		}
	}
	
	
   
	}
	
	
	public Stock getStockById(long id) {
		return stockrepository.findById(id);
	}
        
    
	public void setprod(long id,long id2)
	{
		Stock l=stockrepository.findById(id);
		Product pr = p1.findById(id2).get();
		list.add(pr);
		System.out.println(list.size());
		l.setProducts(list);
		stockrepository.save(l);
		
		
	}
	
	
	
	public String MostNeededStock()
	{   String ch;
		List <Stock> st=(List<Stock>) stockrepository.findAll();
		List<Product> l2 = new ArrayList();
		for(Stock so :st)
		{
			List<Product> l3 = so.getProducts();
			for(Product p1:l3)
			{
				l2.add(p1);
			}
		}
		 
		return poop.mostCommon(l2).getName();
	}
				
	
	
	

}
