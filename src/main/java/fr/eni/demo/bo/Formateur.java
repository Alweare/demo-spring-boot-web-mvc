package fr.eni.demo.bo;

public class Formateur {
	private String nom;
	private String prenom;
	private String email;
	private String photo;

	public Formateur() {
	}

	public Formateur(String nom, String prenom, String email,String photo) {
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.photo = photo;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Formateur [nom=" + nom + ", prenom=" + prenom + ", email=" + email + "]";
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

}
