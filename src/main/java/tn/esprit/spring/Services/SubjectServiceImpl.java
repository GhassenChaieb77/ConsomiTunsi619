package tn.esprit.spring.Services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.Entities.Subject;
import tn.esprit.spring.Entities.User;
import tn.esprit.spring.Entities.Comment;
import tn.esprit.spring.Entities.Product;
import tn.esprit.spring.Repository.CommentRepository;
import tn.esprit.spring.Repository.ProductRepository;
import tn.esprit.spring.Repository.SubjectRepository;


@Service
public class SubjectServiceImpl implements ISubjectService{

	@Autowired
	SubjectRepository subjectRepository;
	@Autowired
	CommentRepository commentRepository;
	@Autowired
	ProductRepository productRepository;
	
	@Override
	public List<Subject> retrieveAllSubject() {
		List<Subject> subjects= (List<Subject>) subjectRepository.findAll();
		return subjects;
	}
	
	

	@Override
	public Subject addSubject(Subject s) {
		subjectRepository.save(s);
		return s;
	}

	@Override
	public void deleteSubject(String id) {
		subjectRepository.deleteById(Long.parseLong(id));		
	}

	@Override
	public Subject updateSubject(Subject s) {
		subjectRepository.save(s);
		return s;
	}

	@Override
	public void affecterCommentASubject(int commentId, int subjectId) {
		Subject subject = subjectRepository.findById((long) subjectId).get();
		Comment comment = commentRepository.findById((long) commentId).get();
		comment.setSubject(subject);
		commentRepository.save(comment);		
	}

	@Override
	public List<String> getAllCommentsContentsBySubject(int subjectId) {
			Subject subjectEntity = subjectRepository.findById((long) subjectId).get();
			List<String> commentContent = new ArrayList<>();
			for(Comment comment : subjectEntity.getComments()){
				commentContent.add(comment.getComment());
			}
			return commentContent;
		}

	@Transactional
	public void deleteSubjectById(int subjectId) {
		subjectRepository.delete(subjectRepository.findById((long) subjectId).get());	

	}

	@Transactional
	public void deletecommentById(int commentId) {
		commentRepository.delete(commentRepository.findById((long) commentId).get());	
		
	}

	@Override
	public Subject getSubjectById(int subjectId) {
		return subjectRepository.findById((long) subjectId).get();
	}



	@Override
	public void affecterSubjectAProduct(int subjectId, int productId) {
		Subject subject = subjectRepository.findById((long) subjectId).get();
		Product product = productRepository.findById((long) productId).get();

		subject.setProduct(product);
		subjectRepository.save(subject);		
	}



	@Override
	public void deleteSubjectRedandant(String subjectId) {		
		List<Subject> subjects= (List<Subject>) subjectRepository.findAll();
		int nb =subjects.size();
		for(int i= 0; i < nb; i++) 
		{
			if ((subjects.get(i+1).getContent()).equals(subjects.get(i).getContent())) 
			{
				if ((subjects.get(i+1).getLikes()) > (subjects.get(i).getLikes()))
				subjectRepository.deleteById(subjects.get(i).getId());				
				else  if ((subjects.get(i+1).getLikes()) < (subjects.get(i).getLikes()))
					subjectRepository.deleteById(subjects.get(i+1).getId());
				
				else if ((subjects.get(i+1).getLikes()) == (subjects.get(i).getLikes()))
					if ((subjects.get(i+1).getDislikes()) < (subjects.get(i).getDislikes()))
						subjectRepository.deleteById(subjects.get(i).getId());
					else 
						subjectRepository.deleteById(subjects.get(i+1).getId());
			} 
		}
	}



	@Override
	public List<Subject> SubjectAlaUne() {
		List<Subject> subjects= (List<Subject>) subjectRepository.SubjectAlaUne();
		return subjects;
	}



	@Override
	public void deleteSubjectSansInteraction(String subjectId) {
		Date d = new Date(System.currentTimeMillis());
		List<Subject> subjects= (List<Subject>) subjectRepository.findAll();
		int nb =subjects.size();
		for(int i= 0; i < nb; i++) 
		if ((subjects.get(i).getDate().before(d)))				
		{ if (((subjects.get(i).getLikes()) == 0 ) && ((subjects.get(i).getDislikes()) == 0 ))
			subjectRepository.deleteById(subjects.get(i).getId());	
		}		
	}






    /*@Override
	public int Getlikes() {
		int c= subjectRepository.Getlikes();
		return c;
	}

	@Override
	public Subject addlikes(Subject s,User u) {
		
		int i=Getlikes()+1;
		s.setLikes(i);
		subjectRepository.save(s);
		return s;
	}*/


	
}





