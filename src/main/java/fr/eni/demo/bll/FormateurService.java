package fr.eni.demo.bll;

import java.util.List;

import fr.eni.demo.bo.Formateur;
import fr.eni.demo.exception.BusinessException;

public interface FormateurService {
	void add(Formateur formateur) throws BusinessException;

	List<Formateur> getFormateurs();
	
	Formateur findByEmail(String emailFormateur);
	
	void update(Formateur formateur) throws BusinessException;
	
	void updateCoursFormateur(String emailFormateur, long idCours);
}
