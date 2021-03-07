package tn.esprit.spring.Services;

import java.util.List;
import tn.esprit.spring.Entities.Comment;
import tn.esprit.spring.Entities.Subject;

public interface ICommentService {
	
	public List<Comment> retrieveAllComment();
	public Comment addComment(Comment c);
	public void deleteComment(String id);
	public Comment updateComment(Comment c);
	//void affecterCommentAuser(int commentId, int userId);
	public List<Comment> RecentComment();

	

}
