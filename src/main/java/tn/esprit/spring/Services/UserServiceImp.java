package tn.esprit.spring.Services;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Entities.Role;
import tn.esprit.spring.Entities.User;
import tn.esprit.spring.Repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService  {
	
	
	@Autowired
	 UserRepository rr;
    @Autowired
    private  JavaMailSender sender;
    

    @Autowired
	EmailConfigSubject emailconfig;
    
    private AuthenticationManager authenticationManager;
	@Override
	public void saveUser(User u) {
		System.err.println("Work3");
		u.setPassword(new BCryptPasswordEncoder().encode(u.getPassword()));
		u.setRole(Role.CUSTOMER);
		System.err.println(u.getRole());
		rr.save(u);
	}

	

	
	  public User findUserByEmail(String email) {
			return rr.findByEmail(email);
		    }
	
	
	 public User getUserInfo(){

		 return this.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
			
		    }
	  
	  
	 



	@Override
	public void DeleteUser(long id) {
		rr.deleteById(id);
		
	}
	
	@Override
	public void Update(User user) {
			//user.setPassword(encoder.encode(user.getPassword()));
			//user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
			rr.save(user);        
	}



	@Override
	public User getUserById(long id) {
		
		  return rr.findById(id);	
		  
	
	}


	@Override
	public List<User> getAlluser() {
		
		List<User> users  = (List<User>) rr.findAll();
		for(User user : users)
		{
			System.out.print(1);
		
		}
		
		return users;
	

	}

	@Override
	public boolean isUserAlreadyPresent(User user) {
		User v =rr.findByEmail(user.getEmail());
		if(v!=null)
				{
		return true;
	}
		return false;

}


	public String authenticate(String login, String password) throws Exception  {
		  try {
	            authenticationManager.authenticate(
	                    new UsernamePasswordAuthenticationToken(login, password)
	            );
	        } catch (Exception ex) {

	        }
	        return login;
	    }
		


	public  String updateResetPasswordToken(String token, String email) throws MessagingException   {
        User customer = rr.findByEmail(email);
        System.err.println(customer.getEmail());
        customer.setResetToken(token);
		rr.save(customer);
		String resetPasswordLink = "http://localhost:8081/SpringMVC/servlet/user/reset_password/" + token;
		/*MimeMessage message = (MimeMessage) sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper((javax.mail.internet.MimeMessage) message);
		helper.setTo(email);
		helper.setText(resetPasswordLink);
		helper.setSubject("forget password");
		sender.send((javax.mail.internet.MimeMessage) message);*/
		
		
		// create mail sender
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(this.emailconfig.getHost());
		mailSender.setPort(this.emailconfig.getPort());
		mailSender.setUsername(this.emailconfig.getUsername());
		mailSender.setPassword(this.emailconfig.getPassword());

		// create email instance
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom("e-dalel@gmail.com");
		mailMessage.setTo(email);
		mailMessage.setSubject("forget password");
		mailMessage.setText(resetPasswordLink);

		// send mail
		mailSender.send(mailMessage);
		
		
		return "done";
    }
     
    public  User getByResetPasswordToken(String token) {
        return rr.findByResetToken(token);
    }
     
    public  void updatePassword(User customer, String newPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
        customer.setPassword(encodedPassword);
         
        customer.setResetToken(null);
        rr.save(customer);
    }
    
	
    
}
	


