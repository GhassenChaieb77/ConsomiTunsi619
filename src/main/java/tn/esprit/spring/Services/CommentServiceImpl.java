package tn.esprit.spring.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Entities.Comment;
import tn.esprit.spring.Entities.User;
import tn.esprit.spring.Repository.CommentRepository;
import tn.esprit.spring.Repository.UserRepository;

@Service
public class CommentServiceImpl implements ICommentService{

	@Autowired
	CommentRepository commentRepository;
	
	@Autowired
	UserRepository userRepository;

	
	@Override
	public List<Comment> retrieveAllComment() {
		List<Comment> comments  = (List<Comment>) commentRepository.findAll();
		return comments;
	}
	


	@Override
	public Comment addComment(Comment c) {
          commentRepository.save(c);
		return c;
	}



	@Override
	public Comment updateComment(Comment c) {
        commentRepository.save(c);
		return c;
	}

	@Override
	public void deleteComment(String id) {
       commentRepository.deleteById(Long.parseLong(id));
       
	}


	/*@Override
	public void affecterCommentAuser(int commentId, int userId) {
		User user = userRepository.findById((long) userId).get();
		Comment comment = commentRepository.findById((long) commentId).get();
		comment.setUser(user);
		commentRepository.save(comment);
	}*/

}
