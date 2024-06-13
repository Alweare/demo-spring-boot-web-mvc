package fr.eni.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.eni.demo.bll.FormateurService;
import fr.eni.demo.bo.Formateur;

@Controller
@RequestMapping("/formateurs")
public class FormateurController {
	
	private FormateurService formateurService;
	
	
	
	
	public FormateurController(FormateurService formateurService) {
		this.formateurService = formateurService;
	}
	
	@GetMapping
	public String afficherFormateurs(Model model) {
		System.out.println("Nous chargerons les formateurs de l'ENI");
		List<Formateur> formateurs = this.formateurService.getFormateurs();
		model.addAttribute("formateurs", formateurs);

		return "view-formateurs";
	}
	@GetMapping("/detail")
	public String detailFormateurParParametre(@RequestParam(name ="email", 
												required = false, 
												defaultValue = "ahah@campus-eni.fr") 
												String emailFormateur, 
												Model model) {
		
		
		Formateur f = this.formateurService.findByEmail(emailFormateur);
		model.addAttribute("formateur",f); // 1 objet formateur avec son prenom, nom et son email
		
		System.out.println("emailFormateur = " + emailFormateur);
		
		System.out.println(f.getPhoto());
		
		return "view-formateur-detail";
	}
	
	@PostMapping("/detail")
	public String mettreAJourFormateur( @RequestParam("prenom") String prenom,
										@RequestParam("nom"   ) String nom,
										@RequestParam("email" ) String email) {
		System.out.println("Prénom =" + prenom);
		System.out.println("Nom = " + nom);
		System.out.println("Email = " + email);
		//redirige vers l'adresse responsable de l'affichage des formateurs
		return "redirect:/formateurs";
	}

}
