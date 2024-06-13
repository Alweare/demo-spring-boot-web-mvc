package fr.eni.demo.dal.mock;

import java.util.*;

import org.springframework.stereotype.Repository;

//import org.springframework.stereotype.Repository;

import fr.eni.demo.bo.Formateur;
import fr.eni.demo.dal.FormateurDAO;

@Repository
public class FormateurDAOBouchon implements FormateurDAO {

	// Solution temporaire - gestion d'une liste de formateur locale
	private static List<Formateur> lstFormateurs;

	public FormateurDAOBouchon() {
		lstFormateurs = new ArrayList<Formateur>();
		lstFormateurs.add(new Formateur("Baille", "Anne-Lise", "abaille@campus-eni.fr",null));
		lstFormateurs.add(new Formateur("Gobin", "Stéphane", "sgobin@campus-eni.fr",null));
		// Ajout d’un formateur pour différencier les bouchons des couches DAL et BLL
		lstFormateurs.add(new Formateur("Trillard", "Julien", "jtrillard@campus-eni.fr",null));
		lstFormateurs.add(new Formateur("Batard", "Al", "ahah@campus-eni.fr","<img src='https://static.wixstatic.com/media/984bb2_6e6b4519dc074438bfba219d76fb31a4~mv2.gif'/>"));
	}

	@Override
	public void create(Formateur formateur) {
		lstFormateurs.add(formateur);
	}

	@Override
	public List<Formateur> findAll() {
		return lstFormateurs;
	}

	@Override
	public Formateur read(String emailFormateur) {
		return lstFormateurs.stream().filter(f -> f.getEmail().equals(emailFormateur)).findFirst().orElse(null);
	}
}
