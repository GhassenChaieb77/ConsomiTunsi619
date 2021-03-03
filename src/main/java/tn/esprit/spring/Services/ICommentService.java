package tn.esprit.spring.Services;

import java.util.List;
import tn.esprit.spring.Entities.Comment;

public interface ICommentService {
	
	public List<Comment> retrieveAllComment();
	public Comment addComment(Comment c);
	public void deleteComment(String id);
	public Comment updateComment(Comment c);
	// void affecterCommentAuser(int commentId, int userId);

	//public List<String> getAllCommentBySubject(int CommentId);

	

}
