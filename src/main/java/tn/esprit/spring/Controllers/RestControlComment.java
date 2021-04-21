package tn.esprit.spring.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.spring.Entities.Comment;
import tn.esprit.spring.Entities.Subject;
import tn.esprit.spring.Repository.CommentRepository;
import tn.esprit.spring.Services.ICommentService;



@RestController
@RequestMapping("/comment")
public class RestControlComment {

	@Autowired
	ICommentService commentService;
	
	// http://localhost:8081/SpringMVC/servlet/retrieve-all-comments
    @GetMapping("/retrieve-all-comments")
	@ResponseBody
	public List<Comment> getComments() {
	 List<Comment> list = commentService.retrieveAllComment();
	return list;
    }
    
    /*// http://localhost:8081/SpringMVC/servlet/RecentComment/2
    @GetMapping("/RecentComment/{subject-id}")
	@ResponseBody
	public List<Comment> getComments1(@PathVariable("subject-id") int subjectId) {
	 List<Comment> list = commentService.RecentComment(subjectId);
	return list;
    }  */
    
 // Add comment : http://localhost:8081/SpringMVC/servlet/add-comment
	@PostMapping("/add-comment")
	@ResponseBody
	public Comment addComment(@RequestBody Comment c) {
	Comment comment = commentService.addComment(c);
	return c;
	}
	// http://localhost:8081/SpringMVC/servlet/remove-comment/{comment-id}
	@DeleteMapping("/remove-comment/{comment-id}")
	@ResponseBody
	 public void removeComment(@PathVariable("comment-id") String commentId) {
	 commentService.deleteComment(commentId);
	 }
	
	// http://localhost:8081/SpringMVC/servlet/modify-comment
	@PutMapping("/modify-comment")
	@ResponseBody
	public Comment modifyComment(@RequestBody Comment comment) {
	 return commentService.updateComment(comment);
	 }
	// http://localhost:8081/SpringMVC/servlet/updatelikescomment/1/
    @PutMapping(value = "/updatelikescomment/{idcomment}/") 
	public Comment updatelikescomment(@PathVariable("idcomment")int commentId) {
    	return commentService.updatelikescomment(commentId);
	}
    
	// http://localhost:8081/SpringMVC/servlet/updatedislikescomment/1/
    @PutMapping(value = "/updatedislikescomment/{idcomment}/") 
	public Comment updatedislikescomment(@PathVariable("idcomment")int commentId) {
    	return commentService.updatedislikescomment(commentId);
	}
    
 // http://localhost:8081/SpringMVC/servlet/deleteDislikesComment/1/
    @PutMapping(value = "/deleteDislikesComment/{idcomment}/") 
	public Comment deleteDislikesComment(@PathVariable("idcomment")int commentId) {
    	return commentService.deleteDislikesComment(commentId);
	}
    // http://localhost:8081/SpringMVC/servlet/deletelikesComment/1/
    @PutMapping(value = "/deletelikesComment/{idcomment}/") 
	public Comment deletelikesComment(@PathVariable("idcomment")int commentId) {
    	return commentService.deletelikesComment(commentId);
	}
    
	
	// http://localhost:8081/SpringMVC/servlet/affecterCommentAuser/1/
    @PutMapping(value = "/affecterCommentAuser/{idcomment}/") 
	public void affecterCommentAuser(@PathVariable("idcomment")int commentId) {
    	commentService.affecterCommentAuser(commentId);
	}
    
    // http://localhost:8081/SpringMVC/servlet/disaffectCommentAuser/1
    @PutMapping(value = "/disaffectCommentAuser/{idcomment}") 
	public void disaffectSubjectAProduct( @PathVariable("idcomment")int commentId) {
    	commentService.disaffectCommentAuser(commentId);
	}
    
    
    
    // URL : http://localhost:8081/SpringMVC/servlet/RecentComment/1
    @GetMapping(value = "RecentComment/{idsubject}")
    @ResponseBody
	public List<String> RecentComment(@PathVariable("idsubject") long subjectId) {

		return commentService.RecentComment(subjectId);
	}
    
    // URL : http://localhost:8081/SpringMVC/servlet/persComment/1
    @GetMapping(value = "persComment/{idsubject}")
    @ResponseBody
	public List<String> persComment(@PathVariable("idsubject") long subjectId) {

		return commentService.persComment(subjectId);
	}
    
    // http://localhost:8081/SpringMVC/servlet/disaffectCommentASubject/1
    @PutMapping(value = "/disaffectCommentASubject/{idcomment}") 
	public void disaffectCommentASubject( @PathVariable("idcomment")int commentId) {
    	commentService.disaffectCommentASubject(commentId);
	}
}
