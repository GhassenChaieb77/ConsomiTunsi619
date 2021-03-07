package tn.esprit.spring.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.Entities.Product;
import tn.esprit.spring.Entities.Subject;
import tn.esprit.spring.Services.IProductService;
import tn.esprit.spring.Services.ISubjectService;


@RestController
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
		public List<Subject> getSubjects1() {
		 List<Subject> list = subjectService.SubjectAlaUne();
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

    	
     	// http://localhost:8081/SpringMVC/servlet/deleteSubjectRedandant/
    	@DeleteMapping("/deleteSubjectRedandant/{subject-id}")
    	@ResponseBody
    	 public void deleteSubjectRedandant(@PathVariable("subject-id") String subjectId) {
    	 subjectService.deleteSubjectRedandant(subjectId);
    	 }
    	
     	// http://localhost:8081/SpringMVC/servlet/deleteSubjectSansInteraction/
    	@DeleteMapping("/deleteSubjectSansInteraction/{subject-id}")
    	@ResponseBody
    	 public void deleteSubjectSansInteraction(@PathVariable("subject-id") String subjectId) {
    	 subjectService.deleteSubjectSansInteraction(subjectId);
    	 }
    	
    	

}