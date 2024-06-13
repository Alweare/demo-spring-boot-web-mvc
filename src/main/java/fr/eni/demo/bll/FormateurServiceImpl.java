package fr.eni.demo.bll;

import java.util.List;

//import org.springframework.stereotype.Service;

import fr.eni.demo.bo.Formateur;
import fr.eni.demo.dal.FormateurDAO;

//@Service
public class FormateurServiceImpl implements FormateurService {
	private FormateurDAO formateurDAO;
	
	public FormateurServiceImpl(FormateurDAO formateurDAO) {
		this.formateurDAO = formateurDAO;
	}
	
	@Override
	public void add(String nom, String prenom, String email) {
		Formateur formateur = new Formateur(nom, prenom, email);
		formateurDAO.create(formateur);
	}

	@Override
	public List<Formateur> getFormateurs() {
		return formateurDAO.findAll();
	}
}
