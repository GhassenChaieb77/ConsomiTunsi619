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
import tn.esprit.spring.Entities.Comment;
import tn.esprit.spring.Entities.Subject;
import tn.esprit.spring.Services.ICommentService;



@RestController
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

	
	/*// http://localhost:8081/SpringMVC/servlet/affecterCommentAuser/6/1
    @PutMapping(value = "/affecterCommentAuser/{idcomment}/{iduser}") 
	public void affecterCommentAuser(@PathVariable("idcomment")int commentId, @PathVariable("iduser")int userId) {
    	commentService.affecterCommentAuser(commentId, userId);
	}*/
	
}
