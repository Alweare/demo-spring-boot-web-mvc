package fr.eni.demo.dal;

import java.util.List;

import fr.eni.demo.bo.Formateur;

public interface FormateurDAO {
	void create(Formateur formateur);

	List<Formateur> findAll();
	
	Formateur read(String emailFormateur);
}
