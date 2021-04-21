package tn.esprit.spring.Services;

import java.util.ArrayList;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.Entities.Subject;
import tn.esprit.spring.Entities.User;
import tn.esprit.spring.Entities.Category;
import tn.esprit.spring.Entities.Comment;
import tn.esprit.spring.Entities.Product;
import tn.esprit.spring.Entities.SauvegardeLDSubject;
import tn.esprit.spring.Entities.SaveSearch;
import tn.esprit.spring.Repository.CommentRepository;
import tn.esprit.spring.Repository.ProductRepository;
import tn.esprit.spring.Repository.SauvegardeLDSubjectRepository;
import tn.esprit.spring.Repository.SaveSearchRepository;
import tn.esprit.spring.Repository.SubjectRepository;
import tn.esprit.spring.Repository.UserRepository;


@Service
public class SubjectServiceImpl implements ISubjectService {

	@Autowired
	SubjectRepository subjectRepository;
	@Autowired
	CommentRepository commentRepository;
	@Autowired
	ProductRepository productRepository;

	@Autowired
	EmailConfigSubject emailconfig;

	@Autowired
	UserServiceImp u;

	@Autowired
	SauvegardeLDSubjectRepository SauvegardeLDSubjectrepository;

	@Autowired
	SaveSearchRepository saveSearchRepository;

	@Override
	public List<Subject> retrieveAllSubject() {
		List<Subject> subjects = (List<Subject>) subjectRepository.findAll();
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
		for (Comment comment : subjectEntity.getComments()) {
			commentContent.add(comment.getComment());
		}
		return commentContent;
	}

	@Transactional
	public void deleteSubjectById(int subjectId) {
		subjectRepository.delete(subjectRepository.findById((long) subjectId).get());
		SauvegardeLDSubjectrepository.DeleteSubjectsauv((long) subjectId);

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
	public List<Subject> deleteSubjectRedandant(long productId) {
		// Product product = productRepository.findById(productId).get();
		List<Subject> subjects = (List<Subject>) subjectRepository.allsubbyprduct(productId);
		int nb = subjects.size();
		int i = 0;
		do
		// for(int i=1 ; i<nb-1; i++)
		{
			if (subjects.get(i).getTitle().contentEquals(subjects.get(i + 1).getTitle())) {
				String ch1 = subjects.get(i).getContent();
				String ch2 = subjects.get(i + 1).getContent();
				int occ = 0;
				int occ1 = 0;
				int occ2 = 0;
				String[] tabcontent1 = ch1.split(" ");
				String[] tabcontent2 = ch2.split(" ");
				for (String a : tabcontent1)
					for (String b : tabcontent1)
						if (a == b)
							occ++;
				// 100%
				if ((occ == tabcontent1.length) && (occ <= tabcontent2.length)) {
					subjectRepository.deleteById(subjects.get(i).getId());
				} else if ((occ <= tabcontent1.length) && (occ == tabcontent2.length)) {
					subjectRepository.deleteById(subjects.get(i + 1).getId());
				}

				// fou9 e 50%
				else if ((occ > tabcontent1.length / 2) && (occ > tabcontent2.length / 2)) {
					occ1 = occ - (tabcontent1.length / 2);
					occ2 = occ - (tabcontent2.length / 2);
					if (occ1 > occ2) {
						subjectRepository.deleteById(subjects.get(i + 1).getId());
					} else {
						subjectRepository.deleteById(subjects.get(i).getId());
					}
				}
			}
			i++;
		} while (i < nb - 1);
		
		return subjects;
	}

	@Override
	public List<String> SubjectAlaUne(long productId) {
		List<String> subjects = (List<String>) subjectRepository.SubjectAlaUne(productId);
		return subjects;
	}

	@Override
	public List<Subject> deleteSubjectSansInteraction() {
		Date d = new Date(System.currentTimeMillis());
		List<Subject> subjects = (List<Subject>) subjectRepository.findAll();
		int nb = subjects.size();
		for (int i = 0; i < nb; i++)
			if (((d.getDay()) - (subjects.get(i).getDate().getDay()) != 0) && ((subjects.get(i).getLikes()) == 0.0)
					&& ((subjects.get(i).getDislikes()) == 0.0)) {
				subjectRepository.deleteById(subjects.get(i).getId());
			} else if (((d.getMonth()) - (subjects.get(i).getDate().getMonth()) != 0)
					&& ((subjects.get(i).getLikes()) == 0.0) && ((subjects.get(i).getDislikes()) == 0.0)) {
				subjectRepository.deleteById(subjects.get(i).getId());
			} else if (((d.getYear()) - (subjects.get(i).getDate().getYear()) != 0)
					&& ((subjects.get(i).getLikes()) == 0.0) && ((subjects.get(i).getDislikes()) == 0.0)) {
				subjectRepository.deleteById(subjects.get(i).getId());
			}
		return subjects;
	}

	@Override
	@Transactional
	public Subject updatelikes(int subjectId) {
		Subject subject = subjectRepository.findById((long) subjectId).get();
        User user= u.getUserInfo();
		List<Long> likeusers = (List<Long>) SauvegardeLDSubjectrepository.userlikes();
		List<Long> subjectlikes = (List<Long>) SauvegardeLDSubjectrepository.subjectlikes();
		if (likeusers.contains(user.getId()) && (subjectlikes.contains(subject.getId())))
			return subject;
		else {
			float nb = subject.getLikes();
			float a = nb + 1;
			subject.setLikes(a);
			float newrating = subject.getLikes() / (subject.getLikes() + subject.getDislikes()) * 5;
			subject.setRating(newrating);
			subjectRepository.save(subject);
			SauvegardeLDSubject su = new SauvegardeLDSubject();
			su.setUserLDsubject((long) user.getId());
			su.setUsersubject((long) subjectId);
			SauvegardeLDSubjectrepository.save(su);
		}
		
		// create mail sender
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(this.emailconfig.getHost());
		mailSender.setPort(this.emailconfig.getPort());
		mailSender.setUsername(this.emailconfig.getUsername());
		mailSender.setPassword(this.emailconfig.getPassword());

		// create email instance
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom("e-dalel@gmail.com");
		mailMessage.setTo("connectetuser@gmail.com");
		mailMessage.setSubject("New like ");
		mailMessage.setText("new like form " + user.getFirstName() + user.getLastName() + " --> go and check the site ");

		// send mail
		mailSender.send(mailMessage);
		return subject;

	}

	@Override
	@Transactional
	public Subject updateDislikes(int subjectId) {
		Subject subject = subjectRepository.findById((long) subjectId).get();
        User user= u.getUserInfo();
		List<Long> likeusers = (List<Long>) SauvegardeLDSubjectrepository.userlikes();
		List<Long> subjectlikes = (List<Long>) SauvegardeLDSubjectrepository.subjectlikes();
		if (likeusers.contains(user.getId()) && (subjectlikes.contains(subject.getId())))
			return subject;
		else {
			float nb = subject.getDislikes();
			float a = nb + 1;
			subject.setDislikes(a);
			if (subject.getLikes() > 0) {
				float newrating = subject.getLikes() / (subject.getLikes() + subject.getDislikes()) * 5;
				subject.setRating(newrating);
			}
			subjectRepository.save(subject);
			SauvegardeLDSubject su = new SauvegardeLDSubject();
			su.setUserLDsubject((long) user.getId());
			su.setUsersubject((long) subjectId);
			SauvegardeLDSubjectrepository.save(su);
		}
		return subject;
	}

	@Override
	@Transactional
	public Subject deleteDislikes(int subjectId) {
		Subject subject = subjectRepository.findById((long) subjectId).get();
        long user= u.getUserInfo().getId();
		List<Long> likeusers = (List<Long>) SauvegardeLDSubjectrepository.userlikes();
		List<Long> subjectlikes = (List<Long>) SauvegardeLDSubjectrepository.subjectlikes();
		if (likeusers.contains(user) && (subjectlikes.contains(subject.getId()))) {
			float nb = subject.getDislikes();
			float a = nb - 1;
			subject.setDislikes(a);
			subjectRepository.save(subject);

			if (subject.getLikes() > 0) {
				float newrating = subject.getLikes() / (subject.getLikes() + subject.getDislikes()) * 5;
				subject.setRating(newrating);
				subjectRepository.save(subject);

			}

			SauvegardeLDSubjectrepository.DeleteSubjectsauv(subjectId);

		}
		
		return subject;
	}

	@Override
	@Transactional
	public Subject deletelikes(int subjectId) {
		Subject subject = subjectRepository.findById((long) subjectId).get();
        long user= u.getUserInfo().getId();
		List<Long> likeusers = (List<Long>) SauvegardeLDSubjectrepository.userlikes();
		List<Long> subjectlikes = (List<Long>) SauvegardeLDSubjectrepository.subjectlikes();
		if (likeusers.contains(user) && (subjectlikes.contains(subject.getId()))) {
			float nb = subject.getLikes();
			float a = nb - 1;
			subject.setLikes(a);
			subjectRepository.save(subject);

			if (subject.getLikes() > 0) {
				float newrating = subject.getLikes() / (subject.getLikes() + subject.getDislikes()) * 5;
				subject.setRating(newrating);
				subjectRepository.save(subject);

			}

			SauvegardeLDSubjectrepository.DeleteSubjectsauv(subjectId);
		}
		
		return subject;
	}
	
	//@Scheduled(fixedRate = 3000)
	@Override
	@Transactional
	public List<Subject> deleteSubjectSansComment() {
		Date d = new Date(System.currentTimeMillis());
		List<Subject> subjects = (List<Subject>) subjectRepository.findAll();

		int nb = subjects.size();

		for (int i = 0; i < nb; i++) {
			if (((d.getDay()) - (subjects.get(i).getDate().getDay()) != 0)
					&& (subjects.get(i).getComments().size() == 0)) {
				subjectRepository.delete(subjects.get(i));
			} else if (((d.getMonth()) - (subjects.get(i).getDate().getMonth()) != 0)
					&& (subjects.get(i).getComments().size() == 0)) {
				subjectRepository.delete(subjects.get(i));
			} else if (((d.getYear()) - (subjects.get(i).getDate().getYear()) != 0)
					&& (subjects.get(i).getComments().size() == 0)) {
				subjectRepository.delete(subjects.get(i));
			}

		}
		return subjects;
	}

	@Override
	public List<Subject> updateRating() {
		List<Subject> subjects = new ArrayList<>();
		subjects = (List<Subject>) subjectRepository.findAll();
		for (Subject s : subjects) {
			// Subject subject = subjectRepository.findById(s.getId()).get();
			float nblikes = s.getLikes();
			float nbdislikes = s.getDislikes();
			float rating = (nblikes / (nblikes + nbdislikes)) * 5;
			s.setRating(rating);
			subjectRepository.save(s);
		}
		return subjects;

	}

	@Override
	public List<Subject> top(long productId) {

		List<Subject> subjects = (List<Subject>) subjectRepository.topthree(productId);
		List<Subject> top3 = new ArrayList<>();
		int nb = subjects.size();
		for (int i = 0; i < nb; i++) {
			if (top3.size() <= 2) {
				top3.add(subjects.get(i));
			}
		}

		return (List<Subject>) top3;

	}

	@Override
	public float nbavis(long subjectId) {

		Subject s = subjectRepository.findById(subjectId).get();
		float avis = s.getLikes() + s.getDislikes();

		return avis;
	}

	@Override
	public int nbcomment(long subjectId) {
		Subject s = subjectRepository.findById(subjectId).get();
		int nb = s.getComments().size();

		return nb;
	}

	@Override
	public int nbsubjectproduct(long productId) {
		List<Subject> subjects = (List<Subject>) subjectRepository.topthree(productId);
		return subjects.size();
	}

	@Override
	public List<Subject> getAllProductBySubject(long productId) {
		List<Subject> subjects = (List<Subject>) subjectRepository.allsubbyprduct(productId);
		return subjects;

	}

	@Override
	public List<Subject> subByTitle(long productId, String title) {
		List<Subject> subjects = (List<Subject>) subjectRepository.subBYTitle(productId, title);

		return subjects;
	}

	@Override
	public List<Subject> subsearch(long productId, String title) {
        User user= u.getUserInfo();
		List<Subject> subjects = (List<Subject>) subjectRepository.subBYTitle(productId, title);
		SaveSearch s = new SaveSearch();
		s.setTitlesub(title);
		s.setUserid(user.getId());
		saveSearchRepository.save(s);
		return subjects;
	}

	@Override
	public List<Subject> AllSubforuser() {
		 User user= u.getUserInfo();
		List<SaveSearch> search = saveSearchRepository.searchBYuser(user.getId());
		List<Subject> subcible = new ArrayList<>();
		List<Subject> subs = (List<Subject>) subjectRepository.findAll();

		for (Subject c : subs) {
			for (SaveSearch s : search)
				if (c.getTitle().equals(s.getTitlesub()))
					subcible.add(c);
			for (int i = 0; i > subcible.size(); i++) {
				if (subcible.get(i).getId() == subcible.get(i + 1).getId())
					subcible.remove(i);
			}

		}

		for (Subject c : subs)
			{for (SaveSearch s : search)
				if (!(c.getTitle().equals(s.getTitlesub())))
					subcible.add(c);
				else 
					for (int i = 0; i > subcible.size(); i++) {
						if (subcible.get(i).getId() == subcible.get(i + 1).getId())
							subcible.remove(i);
						}
					}
		
		
		
		
		return subcible;
	}

	@Override
	public void disaffectSubjectAProduct(long subjectId) {
       Product p = subjectRepository.findById(subjectId).get().getProduct();
		
		p.getSubjects().remove(subjectId);
		subjectRepository.findById(subjectId).get().setProduct(null);
		subjectRepository.save(subjectRepository.findById(subjectId).get());		
	}
}
