package tn.esprit.spring.Services;

import java.util.List;
import java.util.stream.Stream;

import tn.esprit.spring.Entities.Product;
import tn.esprit.spring.Entities.Subject;
import tn.esprit.spring.Entities.User;


public interface ISubjectService {

	public List<Subject> retrieveAllSubject();
	public Subject addSubject(Subject s);
	public void deleteSubject(String id);
	public Subject updateSubject(Subject s);
	void affecterCommentASubject(int commentId, int subjectId);
	List<String> getAllCommentsContentsBySubject(int subjectId);
	List<Subject> getAllProductBySubject(long productId);
	public void deleteSubjectById(int subjectId);
	public void deletecommentById(int commentId);
	public List<Subject> deleteSubjectRedandant(long productId); 
	public List<Subject> deleteSubjectSansInteraction(); 
	public  List<Subject> deleteSubjectSansComment( ); 
	public Subject getSubjectById(int subjectId);
	public void affecterSubjectAProduct(int subjectId, int productId);
	public void disaffectSubjectAProduct(long subjectId) ;
	public List<String> SubjectAlaUne(long productId);
	public Subject updatelikes(int subjectId);
	public Subject updateDislikes(int subjectId);
	public Subject deleteDislikes(int subjectId);
	public Subject deletelikes(int subjectId);
    public List<Subject> updateRating(); 
	public List<Subject> top(long productId);
	public float nbavis(long subjectId);
	public int nbcomment(long subjectId);
	public int nbsubjectproduct(long productId);
	public List<Subject> subByTitle(long productId,String title);
	public List<Subject> subsearch(long productId,String title);
	public List<Subject> AllSubforuser();


	


	



}
