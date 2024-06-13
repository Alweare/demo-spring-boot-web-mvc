package fr.eni.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/formateurs")
public class FormateurController {
	
	
	@GetMapping
	public String afficherFormateurs() {
		System.out.println("Nous chargerons les formateurs de l'ENI");
		return "view-formateurs";
	}
	@GetMapping("/detail")
	public String detailFormateurParParametre(@RequestParam(name ="email", 
	required = false, defaultValue = "ahah@campus-eni.fr") String emailFormateur) {
		System.out.println("emailFormateur = " + emailFormateur);
		
		return "view-formateur-detail";
	}
	
	@PostMapping("/detail")
	public String mettreAJourFormateur( @RequestParam("prenom") String prenom,
										@RequestParam("nom"   ) String nom,
										@RequestParam("email" ) String email) {
		System.out.println("Prénom =" + prenom);
		System.out.println("Nom = " + nom);
		System.out.println("Email = " + email);
		
		return "view-formateurs";
	}

}
