package tn.esprit.spring.Services;

import java.util.List;

import tn.esprit.spring.Entities.Subject;
import tn.esprit.spring.Entities.User;


public interface ISubjectService {

	public List<Subject> retrieveAllSubject();
	public Subject addSubject(Subject s);
	public void deleteSubject(String id);
	public Subject updateSubject(Subject s);
	void affecterCommentASubject(int commentId, int subjectId);
	List<String> getAllCommentsContentsBySubject(int subjectId);
	public void deleteSubjectById(int subjectId);
	public void deletecommentById(int commentId);
	public void deleteSubjectRedandant(String subjectId); 
	public void deleteSubjectSansInteraction(String subjectId); 
	public Subject getSubjectById(int subjectId);
	public void affecterSubjectAProduct(int subjectId, int productId);
    //public int Getlikes();
	//public Subject addlikes(Subject s, User u);
	public List<Subject> SubjectAlaUne();

	



}
