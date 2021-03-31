package tn.esprit.spring.Controllers;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.assertj.core.internal.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.Entities.User;
import tn.esprit.spring.Repository.UserRepository;
//import tn.esprit.spring.Services.SupplierServiceImpa;
import tn.esprit.spring.Services.UserServiceImp;

@RestController
@RequestMapping("/user")
public class UserController {
	
	  @Autowired
	  UserServiceImp up;
	  /*@Autowired
	  SupplierServiceImpa test;*/

	
	@PostMapping("/add")
	  public void createTutorial(@RequestBody User u) {
	     up.saveUser(u);
		
	  }
	
	
	 @GetMapping("/retrieve-all-users")
	 @ResponseBody
	 public List<User> getUsers() {
	 List<User> list = up.getAlluser();
	 
	 return list;

}
	 
	 
	 @GetMapping("/login")
	 @ResponseBody
	 public String login(@RequestBody User userDetails) {

		 
		 return up.authenticate(userDetails.getEmail(), userDetails.getPassword());

}
	 
	 
	 
	  @DeleteMapping("/Delete/{id}")
	  public void deleteTutorial(@PathVariable("id") long id) {
	     System.out.println("test");
	      up.DeleteUser(id);
	     
	
	  }
	  
	  
	  @PutMapping("/Update/{id}")
	  public void updateUser( @PathVariable("id") Long userId, @RequestBody User userDetails)
	  {

	    User user =up.getUserById(userId);

	    user.setEmail(userDetails.getEmail());
	    user.setLastName(userDetails.getLastName());
	    user.setFirstName(userDetails.getFirstName());
	    up.Update(user);
	  }
	  
	  
	  @GetMapping("/thisuser")
		 @ResponseBody
		 public String getUser() {
 User u =up.getUserInfo();
		 
		 return u.getEmail() ;
	 
	  }
	  
	  
	  /*@GetMapping("/best")
		 @ResponseBody
		 public String best() {
		 
		 
		 return test.best();
	  }*/
	  
	  
	  @PutMapping("/forgot_password")
public String processForgotPassword(@RequestBody String email) throws MessagingException {
String token = RandomString.make(30);
up.updateResetPasswordToken(token, email);

return "done";

}
	  
	  @PutMapping("/reset_password/{token}")
 @ResponseBody
	  public String showResetPasswordForm(@PathVariable("token") String token, @RequestBody String password) {
	      User customer = up.getByResetPasswordToken(token);
	       System.err.println(customer.getEmail());
	      if (customer == null) {
	          return "Invalid Token";
	      }
	      else {
	    	  up.updatePassword(customer,password);
	      return "You have successfully changed your password";
	      }
	  }
	  
}
