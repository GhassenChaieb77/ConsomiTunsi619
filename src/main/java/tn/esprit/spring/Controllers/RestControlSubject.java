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
import tn.esprit.spring.Entities.Subject;
import tn.esprit.spring.Services.ISubjectService;


@RestController
public class RestControlSubject {

	@Autowired
	ISubjectService subjectService;
	
// http://localhost:8081/SpringMVC/servlet/retrieve-all-subjects
        @GetMapping("/retrieve-all-subjects")
		@ResponseBody
		public List<Subject> getSubjects() {
		 List<Subject> list = subjectService.retrieveAllSubject();
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
  
}