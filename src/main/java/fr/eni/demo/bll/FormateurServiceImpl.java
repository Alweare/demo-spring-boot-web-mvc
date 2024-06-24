package fr.eni.demo.bll;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.eni.demo.bo.Cours;
import fr.eni.demo.bo.Formateur;
import fr.eni.demo.dal.CoursDAO;
import fr.eni.demo.dal.FormateurDAO;

@Service
public class FormateurServiceImpl implements FormateurService {
	private FormateurDAO formateurDAO;
	private CoursDAO coursDAO;

	public FormateurServiceImpl(FormateurDAO formateurDAO, CoursDAO coursDAO) {
		this.formateurDAO = formateurDAO;
		this.coursDAO = coursDAO;
	}

	@Override
	public void add(Formateur formateur) {
		
		
		formateurDAO.create(formateur);
		formateur.getListeCours().forEach(c -> coursDAO.insertCoursFormateur(c.getId(), formateur.getEmail()));
	}

	@Override
	public List<Formateur> getFormateurs() {
		return formateurDAO.findAll();
	}

	@Override
	public Formateur findByEmail(String emailFormateur) {
		Formateur f = formateurDAO.read(emailFormateur);
		List<Cours> listeCours = coursDAO.findByFormateur(emailFormateur);
		if(listeCours != null ) {
			f.setListeCours(listeCours);
		}
		return f;
	}

	public void update(Formateur formateur) {
		formateurDAO.update(formateur);
	}

	@Override
	public void updateCoursFormateur(String emailFormateur, long idCours) {
		//Mise à jour au niveau BO
		Formateur f = formateurDAO.read(emailFormateur);
		Cours c = coursDAO.read(idCours);	
		f.getListeCours().add(c);
		
		//Mise à jour en base
		coursDAO.insertCoursFormateur(idCours, emailFormateur);
	}

}
