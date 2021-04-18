package tn.esprit.spring.Controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;

import org.assertj.core.internal.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

import tn.esprit.configuration.JwtUtils;
import tn.esprit.configuration.LoginRequest;
import tn.esprit.configuration.MyUserDetails;
import tn.esprit.spring.Entities.Supplier;
import tn.esprit.spring.Entities.Tokens;
import tn.esprit.spring.Entities.User;
import tn.esprit.spring.Repository.SupplierRepository;
import tn.esprit.spring.Repository.TokenReopsitory;
import tn.esprit.spring.Repository.UserRepository;
import tn.esprit.spring.Services.SupplierServiceImpa;
//import tn.esprit.spring.Services.SupplierServiceImpa;
import tn.esprit.spring.Services.UserServiceImp;

@RestController
@RequestMapping("/user")
public class UserController {
	
	  @Autowired
	  UserServiceImp up;
	  @Autowired
	  SupplierRepository test;
	  @Autowired
	  UserRepository ur;
	  @Autowired
		JwtUtils jwtUtils;
		@Autowired
		TokenReopsitory tokenReopsitory;
		@Autowired
		AuthenticationManager authenticationManager;
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
	      if (customer != null) {
	    	  
	    	  up.updatePassword(customer,password);
		      return "You have successfully changed your password";	     
		      }
	      
	      return "wrong token";

	  }
	  
	  
	  @GetMapping("/retrieve-all-supp")
		 @ResponseBody
		 public List<Supplier> getsupp() {
		
		 
		 return  (List<Supplier>) test.findAll();

	}
	  
	  @PostMapping("/addsup")
	  public void create(@RequestBody Supplier s) {
		  test.save(s);
	  }
	  
	  
	  @DeleteMapping("/Deletesup/{id}")
	  public void delete(@PathVariable("id") long id) {
	      test.deleteById(id);
	     
	
	  }
	  
	  
	  
	  @PostMapping("/signin")
		public String authenticateUser(@RequestBody LoginRequest loginRequest) {
			User u=ur.findByEmail(loginRequest.getUsername());
			Authentication 
			 authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtUtils.generateJwtToken(authentication);

		//	MyUserDetails userDetails =  (MyUserDetails )authentication.getPrincipal();
			///List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
					//.collect(Collectors.toList());
			//User user=ur.findById(userDetails.getId()).get();
			Tokens t = new Tokens();
			t.setName(jwt);
			t.setUserId(u.getId());
			tokenReopsitory.save(t);
			//JwtResponse jt	= new JwtResponse(jwt, u.getId(), u.getEmail(), u.getRole().toString());

			return "token " + t.getName();
	}
	
	
	  
	
	  
	  
	  
		
}
