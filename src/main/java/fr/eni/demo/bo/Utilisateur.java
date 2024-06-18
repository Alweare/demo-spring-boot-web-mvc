package fr.eni.demo.bo;


import java.io.Serializable;

public class Utilisateur implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String pseudo;
	
	
	public Utilisateur() {
	}


	public Utilisateur(String pseudo) {
		super();
		this.pseudo = pseudo;
	}


	public String getPseudo() {
		return pseudo;
	}


	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}


	@Override
	public String toString() {
		return String.format("Utilisateur [pseudo=%s]", pseudo);
	}

}
