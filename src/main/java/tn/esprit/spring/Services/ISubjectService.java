package tn.esprit.spring.Services;

import java.util.List;

import tn.esprit.spring.Entities.Subject;


public interface ISubjectService {

	public List<Subject> retrieveAllSubject();
	public Subject addSubject(Subject s);
	public void deleteSubject(String id);
	public Subject updateSubject(Subject s);
	void affecterCommentASubject(int commentId, int subjectId);
	List<String> getAllCommentsContentsBySubject(int subjectId);
	//public void deleteCommentByContent(String commentContent);
}
