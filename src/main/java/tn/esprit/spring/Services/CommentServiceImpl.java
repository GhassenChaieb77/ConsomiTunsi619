package tn.esprit.spring.Services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.Entities.Comment;
import tn.esprit.spring.Entities.Product;
import tn.esprit.spring.Entities.SauvegardeLDComment;
import tn.esprit.spring.Entities.SauvegardeLDSubject;
import tn.esprit.spring.Entities.Subject;
import tn.esprit.spring.Entities.User;
import tn.esprit.spring.Repository.CommentRepository;
import tn.esprit.spring.Repository.SauvegardeLDCommentRepository;
import tn.esprit.spring.Repository.UserRepository;

@Service
public class CommentServiceImpl implements ICommentService{

	@Autowired
	CommentRepository commentRepository;
	
	@Autowired
	UserServiceImp u;
	
	@Autowired
	EmailConfigSubject emailconfig;

	@Autowired
	SauvegardeLDCommentRepository SauvegardeLDCommentrepository;
	
	@Override
	public List<Comment> retrieveAllComment() {
		List<Comment> comments  = (List<Comment>) commentRepository.findAll();

		return comments;
	}
	

	@Override
	public Comment addComment(Comment c) {
        User user= u.getUserInfo();
        String comm="";
		List <String> interdit= Arrays.asList("owww","love"); 
        for (int i= 0; i < interdit.size(); i++)
        {	
        	
		if (c.getComment().contains(interdit.get(i)))	
		{
		    int nb= interdit.get(i).length(); 
		    for (int j= 0; j < nb ; j++)
		    	{comm= comm+"*";}
			 c.setComment(c.getComment().replace(interdit.get(i), comm));
		c.setUser(user);
        commentRepository.save(c);
        }
        else 
        	c.setUser(user);
        commentRepository.save(c);
		}
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



	



	@Override
	@Transactional
	public Comment updatelikescomment(int commentId) {
		Comment comment = commentRepository.findById((long) commentId).get();
        User user= u.getUserInfo();
		if (user.getId() == (comment.getUser().getId()))
			{System.out.println("1");
		return comment ;}
		else 
		{
		List<Long> likeusers = (List<Long>) SauvegardeLDCommentrepository.userlikes();
		List<Long> commentlikes = (List<Long>) SauvegardeLDCommentrepository.commentlikes();
		if (likeusers.contains(user.getId()) && (commentlikes.contains(comment.getId())))
			{System.out.println("3");
			return comment ;}
		
		
		else 
		{
			
		int nb = comment.getLikesComment();
        int a= nb+1;
        comment.setLikesComment(a);
     // create mail sender
      		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
      		mailSender.setHost(this.emailconfig.getHost());
      		mailSender.setPort(this.emailconfig.getPort());
      		mailSender.setUsername(this.emailconfig.getUsername());
      		mailSender.setPassword(this.emailconfig.getPassword());

      		// create email instance
      		SimpleMailMessage mailMessage = new SimpleMailMessage();
      		mailMessage.setFrom("e-dalel@gmail.com");
      		mailMessage.setTo(comment.getUser().getEmail());
      		mailMessage.setSubject("New like ");
      		mailMessage.setText("new like form " + user.getFirstName() + user.getLastName() + " in your Comment <<" + comment.getComment() +">>" );

      		// send mail
      		mailSender.send(mailMessage);
        
        commentRepository.save(comment);
	    SauvegardeLDComment suc= new SauvegardeLDComment();
	    suc.setUserLDcomment((long)user.getId());
	    suc.setUsercomment((long)commentId);
	    SauvegardeLDCommentrepository.save(suc);
	 
		return comment;
		}
		
		}
	}



	@Override
	@Transactional
	public Comment updatedislikescomment(int commentId) {
		Comment comment = commentRepository.findById((long) commentId).get();
        User user= u.getUserInfo();

		if (user.getId() == (comment.getUser().getId()))
		
		return comment ;
		
		else 
		{
		List<Long> likeusers = (List<Long>) SauvegardeLDCommentrepository.userlikes();
		List<Long> commentlikes = (List<Long>) SauvegardeLDCommentrepository.commentlikes();
		if (likeusers.contains(user.getId()) && (commentlikes.contains(comment.getId())))
			return comment ;
		else 
		{
			
		int nb = comment.getDislikesComment();
        int a= nb+1;
        comment.setDislikesComment(a);
     
        commentRepository.save(comment);
	    SauvegardeLDComment suc= new SauvegardeLDComment();
	    suc.setUserLDcomment((long)user.getId());
	    suc.setUsercomment((long)commentId);
	    SauvegardeLDCommentrepository.save(suc);
		return comment;
		} }
	}



	@Override
	public List<String> RecentComment(long subjectId) {
		
		return commentRepository.RecentComment(subjectId);
		
	}


	@Override
	public void affecterCommentAuser(int commentId) {
		//User user = userRepository.findById((long) userId).get();
        User user= u.getUserInfo();

		Comment comment = commentRepository.findById((long) commentId).get();
		comment.setUser(user);
		commentRepository.save(comment);
	}



	@Override
	public List<String> persComment(long subjectId) {
		
		return commentRepository.pertComment(subjectId);
	}







	@Override
	@Transactional
	public Comment deleteDislikesComment(int commentId) {
		Comment comment = commentRepository.findById((long) commentId).get();
        User user= u.getUserInfo();
		List<Long> likeusers = (List<Long>) SauvegardeLDCommentrepository.userlikes();
		List<Long> commentlikes = (List<Long>) SauvegardeLDCommentrepository.commentlikes();
		if (likeusers.contains(user.getId()) && (commentlikes.contains(comment.getId())))
		{
			int nb = comment.getDislikesComment();
			int a= nb - 1;
			comment.setDislikesComment(a);
			commentRepository.save(comment);
	        SauvegardeLDCommentrepository.DeleteCommentsauv(commentId);
			
		}	
		return comment;
	}



	@Override
	@Transactional
	public Comment deletelikesComment(int commentId) {
		Comment comment = commentRepository.findById((long) commentId).get();
        User user= u.getUserInfo();
		List<Long> likeusers = (List<Long>) SauvegardeLDCommentrepository.userlikes();
		List<Long> commentlikes = (List<Long>) SauvegardeLDCommentrepository.commentlikes();
		if (likeusers.contains(user.getId()) && (commentlikes.contains(comment.getId())))
		{
			int nb = comment.getLikesComment();
			int a= nb - 1;
			comment.setLikesComment(a);
			commentRepository.save(comment);
	        SauvegardeLDCommentrepository.DeleteCommentsauv(commentId);;
			
		}	
		return comment;
	}



	@Override
	public void disaffectCommentAuser(long commentId) {
        User user= u.getUserInfo();
		
		user.getComments().remove(commentId);
		commentRepository.findById(commentId).get().setUser(null);
		commentRepository.save(commentRepository.findById(commentId).get());		
	}


	@Override
	public void disaffectCommentASubject(long commentId) {
		 Subject s = commentRepository.findById(commentId).get().getSubject();
			
			s.getComments().remove(commentId);
			commentRepository.findById(commentId).get().setSubject(null);
			commentRepository.save(commentRepository.findById(commentId).get());				
	}

}
