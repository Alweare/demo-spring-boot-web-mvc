package fr.eni.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import fr.eni.demo.bo.Utilisateur;

@Controller
@RequestMapping("/contexte/SessionEtRequest")
@SessionAttributes({"utilisateurSession"})
public class ContexteController {

	//méthode mettant à jour un attribut de session 
	// et créant un attribut dans le context de request
	@GetMapping
	public String addUtilisateur(@ModelAttribute("utilisateurSession") Utilisateur utilisateurSession, 
			@RequestParam(name = "pseudo", required = false) String pseudo, Model model) {
		System.out.println("Ajouter un utilisateur en request + MAJ de l'utilisateur en session");
		//ajouer utilisateur
		Utilisateur utilisateurRequest = new Utilisateur(pseudo);
		model.addAttribute("utilisateurRequest", utilisateurRequest);
		
		
		//MAJ du pseudo de l'utilisateur en session
		utilisateurSession.setPseudo(pseudo);


		return "contexte/view-contexte";

	}
	@GetMapping("/recuperer")
	public String recupereUtilisateurs(@ModelAttribute("utilisateurSession") Utilisateur utilisateurSession,
			@ModelAttribute("utilisateurRequest") Utilisateur utilisateurRequest) {
		System.out.println("utilisateurSession = " + utilisateurSession);
	System.out.println("utilisateurRequest = " + utilisateurRequest);
		
		return "contexte/view-contexte";
		
	}

	@ModelAttribute("utilisateurSession")
	public Utilisateur addUtilisateurSession() {
		System.out.println("Ajout d'un utilisateur en session");
		return new Utilisateur();

	}

}
