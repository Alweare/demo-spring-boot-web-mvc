package fr.eni.demo.bll;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import fr.eni.demo.bo.Cours;
import fr.eni.demo.bo.Formateur;
import fr.eni.demo.dal.CoursDAO;
import fr.eni.demo.dal.FormateurDAO;
import fr.eni.demo.exception.BusinessException;
import jakarta.validation.constraints.Email;

@Service
public class FormateurServiceImpl implements FormateurService {
	private FormateurDAO formateurDAO;
	private CoursDAO coursDAO;

	public FormateurServiceImpl(FormateurDAO formateurDAO, CoursDAO coursDAO) {
		this.formateurDAO = formateurDAO;
		this.coursDAO = coursDAO;
	}

	@Override
	public void add(Formateur formateur) throws BusinessException {

		BusinessException be = new BusinessException();
		boolean isValid = checkEmail(formateur.getEmail(), be);
		isValid &= checkListeCours(formateur.getListeCours(), be);
		
		if(isValid) {
			try {
				formateurDAO.create(formateur);
				formateur.getListeCours().forEach(c -> coursDAO.insertCoursFormateur(c.getId(), formateur.getEmail()));
		
				
			} catch (DataAccessException e) {
				be.add("Un problème est survenu lors de la connexion à la base de données");
			}
			
		}else {
			throw be;
		}

		
}
private boolean checkEmail(String email, BusinessException be) {
	int nbEmail = formateurDAO.countEmail(email);
	
	boolean isValid = false;
	if(nbEmail == 0 ) {
		isValid = true;
	}else {
		be.add("Création impossible. L'email existe déjà");


	}


	return isValid;

}
private boolean checkListeCours(List<Cours> listeCours, BusinessException be) {

	boolean isValid = false;
	if(listeCours == null || listeCours.isEmpty()) {
		be.add("Création impossible. la lkiste des cours doit être renseignée");
	}else {
		List<Long> ids = listeCours.stream().map(Cours::getId).toList();

		int ndCoursTrouve =  coursDAO.countByIds(ids);
		if(ids.size() !=ndCoursTrouve) {
			be.add("Création impossible. L'affectation d'un cours inexistant n'est pas possible  ");
		}else {
		isValid = true;
		}
		
	}
	



		return isValid;

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

	public void update(Formateur formateur) throws BusinessException {
		BusinessException be = new BusinessException();
		
		try {
			
			formateurDAO.update(formateur);
		} catch (DataAccessException e) {
			be.add("Un problème est survenu lors de la connexion à la base de données");
			throw be;
		}
		
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
