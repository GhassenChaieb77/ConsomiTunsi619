package tn.esprit.spring.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Entities.Subject;
import tn.esprit.spring.Entities.Comment;
import tn.esprit.spring.Repository.CommentRepository;
import tn.esprit.spring.Repository.SubjectRepository;


@Service
public class SubjectServiceImpl implements ISubjectService{

	@Autowired
	SubjectRepository subjectRepository;
	@Autowired
	CommentRepository commentRepository;
	
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
}





