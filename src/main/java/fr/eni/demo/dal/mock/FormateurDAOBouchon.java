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
		lstFormateurs.add(new Formateur("Baille", "Anne-Lise", "abaille@campus-eni.fr","https://images.unsplash.com/photo-1518708909080-704599b19972?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"));
		lstFormateurs.add(new Formateur("Gobin", "Stéphane", "sgobin@campus-eni.fr","https://images.unsplash.com/photo-1651607160925-bbe958e38115?q=80&w=1935&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"));
		// Ajout d’un formateur pour différencier les bouchons des couches DAL et BLL
		lstFormateurs.add(new Formateur("Trillard", "Julien", "jtrillard@campus-eni.fr","https://images.unsplash.com/photo-1705723119301-d5f9ee411f8e?q=80&w=1976&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"));
		lstFormateurs.add(new Formateur("Batard", "Al", "ahah@campus-eni.fr","https://static.wixstatic.com/media/984bb2_6e6b4519dc074438bfba219d76fb31a4~mv2.gif"));
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
