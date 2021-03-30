package tn.esprit.spring.Controllers;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.Entities.Product;
import tn.esprit.spring.Entities.Subject;
import tn.esprit.spring.Entities.User;
import tn.esprit.spring.Repository.UserRepository;
import tn.esprit.spring.Services.IProductService;
import tn.esprit.spring.Services.ISubjectService;



@RestController
@RequestMapping("/subject")
public class RestControlSubject {

	@Autowired
	ISubjectService subjectService;
	@Autowired
	IProductService productService;
	
	

	
	
// http://localhost:8081/SpringMVC/servlet/retrieve-all-subjects
        @GetMapping("/retrieve-all-subjects")
		@ResponseBody
		public List<Subject> getSubjects() {
		 List<Subject> list = subjectService.retrieveAllSubject();
		return list;
        }
        
  
        
     // http://localhost:8081/SpringMVC/servlet/SubjectAlaUne
        @GetMapping("/SubjectAlaUne")
		@ResponseBody
		public List<String> getSubjects1() {
		 List<String> list = subjectService.SubjectAlaUne();
		return list;
        }  
        
    // Add Subject : http://localhost:8081/SpringMVC/servlet/add-subject
    	@PostMapping("/add-subject")
    	@ResponseBody
    	public Subject addSubject(@RequestBody Subject s) {
    	Subject subject = subjectService.addSubject(s);
    	return s;
    	}
    	
    	// http://localhost:8081/SpringMVC/servlet/remove-subject/{subject-id}
    	@DeleteMapping("/remove-subject/{subject-id}")
    	@ResponseBody
    	 public void removeSubject(@PathVariable("subject-id") String subjectId) {
    	 subjectService.deleteSubject(subjectId);
    	 }
    	
    	// http://localhost:8081/SpringMVC/servlet/modify-subject
    	@PutMapping("/modify-subject")
    	@ResponseBody
    	public Subject modifySubject(@RequestBody Subject subject) {
    	 return subjectService.updateSubject(subject);
    	 }
    	
        // http://localhost:8081/SpringMVC/servlet/affecterCommentASubject/6/1
        @PutMapping(value = "/affecterCommentASubject/{idcomment}/{idsubject}") 
    	public void affecterCommentASubject(@PathVariable("idcomment")int commentId, @PathVariable("idsubject")int subjectId) {
        	subjectService.affecterCommentASubject(commentId, subjectId);
    	}
        
    	// http://localhost:8081/SpringMVC/servlet/getAllCommentsContentsBySubject/1
        @GetMapping(value = "getAllCommentsContentsBySubject/{idsubject}")
        @ResponseBody
    	public List<String> getAllCommentsContentsBySubject(@PathVariable("idsubject") int subjectId) {
    		return subjectService.getAllCommentsContentsBySubject(subjectId);
    	} 
        
        
    	// http://localhost:8081/SpringMVC/servlet/getAllProductBySubject/1
        @GetMapping(value = "getAllProductBySubject/{productId}")
        @ResponseBody
    	public List<Subject> getAllProductBySubject(@PathVariable("productId") long productId) {
    		return subjectService.getAllProductBySubject(productId);
    	}
        
       	// http://localhost:8081/SpringMVC/servlet/subByTitle/1/mouna
        @GetMapping(value = "subByTitle/{productId}/{title}")
        @ResponseBody
    	public List<Subject> subByTitle(@PathVariable("productId") long productId,@PathVariable("title") String title) {
    		return subjectService.subByTitle(productId, title);
    	}  
        
        
      	// http://localhost:8081/SpringMVC/servlet/subsearch/1/1/mouna
        @GetMapping(value = "subsearch/{productId}/{userId}/{title}")
        @ResponseBody
    	public List<Subject> subsearch(@PathVariable("productId") long productId,@PathVariable("userId") long userId,@PathVariable("title") String title) {
    		return subjectService.subsearch(productId, userId, title);
    	}  
        
     	// http://localhost:8081/SpringMVC/servlet/AllSubforuser/1/
        @GetMapping(value = "AllSubforuser/{userId}/")
        @ResponseBody
    	public List<Subject> AllSubforuser(@PathVariable("userId") long userId) {
    		return subjectService.AllSubforuser(userId);
    	}
  
        
     // http://localhost:8081/SpringMVC/servlet/deleteSubjectById/1
        @DeleteMapping("/deleteSubjectById/{idsubject}") 
    	@ResponseBody 
    	public void deleteSubjectById(@PathVariable("idsubject")int subjectId)
    	{
        	subjectService.deleteSubjectById(subjectId);
    	}
        
        
        // URL : http://localhost:8081/SpringMVC/servlet/deletecommentById/3
        @DeleteMapping("/deletecommentById/{idcomment}") 
    	@ResponseBody 
    	public void deletecommentById(@PathVariable("idcomment") int commentId) {
        	subjectService.deletecommentById(commentId);
    	}
        
        // http://localhost:8081/SpringMVC/servlet/getSubjectById/1
        @GetMapping(value = "getSubjectById/{idsubject}")
        @ResponseBody
    	public Subject getSubjectById(@PathVariable("idsubject") int subjectId) {

    		return subjectService.getSubjectById(subjectId);
    	}
    	
    	// http://localhost:8081/SpringMVC/servlet/affecterSubjectAProduct/19/1
       @PutMapping(value = "/affecterSubjectAProduct/{idsubject}/{idproduct}") 
    	public void affecterSubjectAProduct(@PathVariable("idsubject")int subjectId, @PathVariable("idproduct")int productId)
    	{
    	   subjectService.affecterSubjectAProduct(subjectId, productId);
    	}        
             

    	/// http://localhost:8081/SpringMVC/servlet/deleteSubjectRedandant/1
   	@PutMapping("/deleteSubjectRedandant/{idproduct}")
   	 public void deleteSubjectRedandant(@PathVariable("idproduct")long productId) {
   	 subjectService.deleteSubjectRedandant(productId);
   	 }
    	
     	
    	
     	// http://localhost:8081/SpringMVC/servlet/deleteSubjectSansInteraction/
    	@PutMapping("/deleteSubjectSansInteraction")
    	 public void deleteSubjectSansInteraction() {
    	 subjectService.deleteSubjectSansInteraction();
    	 }
    
    	
    	// http://localhost:8081/SpringMVC/servlet/deleteSubjectSansComment/
        @PutMapping(value = "/deleteSubjectSansComment") 
    	public void deleteSubjectSansComment( ) {
        	subjectService.deleteSubjectSansComment( );
    	}
    	
        
    	
    	
    	
    	// http://localhost:8081/SpringMVC/servlet/updatelikes/1/1
        @PutMapping(value = "/updatelikes/{idsubject}/{iduser}") 
    	public Subject updatelikes(@PathVariable("idsubject")int subjectId, @PathVariable("iduser")int userId) {
        	return subjectService.updatelikes(subjectId, userId);
        	
        
    	}
        
    	
        // http://localhost:8081/SpringMVC/servlet/updateDislikes/1/1
        @PutMapping(value = "/updateDislikes/{idsubject}/{iduser}") 
    	public void updateDislikes(@PathVariable("idsubject")int subjectId, @PathVariable("iduser")int userId) {
        	 subjectService.updateDislikes(subjectId, userId);
    	}
     
        
    
        
     // http://localhost:8081/SpringMVC/servlet/deleteDislikes/1/1
        @PutMapping(value = "/deleteDislikes/{idsubject}/{iduser}") 
    	public void deleteDislikes(@PathVariable("idsubject")int subjectId, @PathVariable("iduser")int userId) {
        	subjectService.deleteDislikes(subjectId, userId);
    	}
        // http://localhost:8081/SpringMVC/servlet/deletelikes/1/1
        @PutMapping(value = "/deletelikes/{idsubject}/{iduser}") 
    	public void deletelikes(@PathVariable("idsubject")int subjectId, @PathVariable("iduser")int userId) {
        	subjectService.deletelikes(subjectId, userId);
    	}
        
   
     // http://localhost:8081/SpringMVC/servlet/updateRating/
        @PutMapping(value = "/updateRating") 
    	public void updateRating( ) {
        	subjectService.updateRating( );
    	}
        
    	// http://localhost:8081/SpringMVC/servlet/top/1
        @GetMapping(value = "top3/{idproduct}")
        @ResponseBody
    	public List<Subject> top(@PathVariable("idproduct") long productId) {
    		//return subjectService.top(productId);
        	List<Subject> list = subjectService.top(productId);
    			return list;
    		
    	} 
        
     // http://localhost:8081/SpringMVC/servlet/nbavis/1
        @GetMapping(value = "nbavis/{idsubject}")
        @ResponseBody
    	public float nbavis(@PathVariable("idsubject") long subjectId) {
        	float avis = subjectService.nbavis(subjectId);
    			return avis;} 
        
     // http://localhost:8081/SpringMVC/servlet/nbcomment/1
        @GetMapping(value = "nbcomment/{idsubject}")
        @ResponseBody
    	public int nbcomment(@PathVariable("idsubject") long subjectId) {
        	int nbcomment = subjectService.nbcomment(subjectId);
    			return nbcomment;} 
        
        // http://localhost:8081/SpringMVC/servlet/nbsubjectproduct/1
        @GetMapping(value = "nbsubjectproduct/{idproduct}")
        @ResponseBody
    	public int nbsubjectproduct(@PathVariable("idproduct") long productId) {
        	int nbsubjectproduct = subjectService.nbsubjectproduct(productId);
    			return nbsubjectproduct;}

}