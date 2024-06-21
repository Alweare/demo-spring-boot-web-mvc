package fr.eni.demo.dal;

import java.util.List;

import org.springframework.stereotype.Repository;

import fr.eni.demo.bo.Formateur;
@Repository
public class FormateurDAOImpl implements FormateurDAO{
	
	private static final String INSERT = "INSERT INTO FORMATEURS (email,nom,prenom) VALUES (:email,:nom,:prenom)";
	private static final String FIND_BY_EMAIL ="SELECT email, nom,prenom FROM FORMATEURS WHERE email =:email";
	private static final String UPDATE = "UPDATE FORMATEURS SET nom = :nom, prenom=:prenom WHERE email= :email";
	private static final String FINDAL_ALL = "SELECT email, nom,prenom FROM FORMATEURS";

	@Override
	public void create(Formateur formateur) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Formateur read(String emailFormateur) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Formateur formateur) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Formateur> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
