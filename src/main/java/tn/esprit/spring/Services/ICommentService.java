package tn.esprit.spring.Services;

import java.util.List;
import tn.esprit.spring.Entities.Comment;
import tn.esprit.spring.Entities.Subject;
import tn.esprit.spring.Entities.User;

public interface ICommentService {
	
	public List<Comment> retrieveAllComment();
	public Comment addComment(Comment c);
	public void deleteComment(String id);
	public Comment updateComment(Comment c);
	void affecterCommentAuser(int commentId);
	public void disaffectCommentAuser(long commentId) ;
	public void disaffectCommentASubject(long commentId);

	public List<String> RecentComment(long subjectId);
	public List<String> persComment(long subjectId);
	public Comment updatelikescomment(int commentId);
	public Comment updatedislikescomment(int commentId);
	public Comment deleteDislikesComment(int commentId);
	public Comment deletelikesComment(int commentId);


	

}
