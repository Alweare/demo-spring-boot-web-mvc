package fr.eni.demo.controller;

import java.text.Format;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import fr.eni.demo.bll.CoursService;
import fr.eni.demo.bll.FormateurService;
import fr.eni.demo.bo.Cours;
import fr.eni.demo.bo.Formateur;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/formateurs")
@SessionAttributes({"coursSession"})
public class FormateurController {

	private FormateurService formateurService;
	private CoursService coursService;




	public FormateurController(FormateurService formateurService, CoursService coursService) {
		this.formateurService = formateurService;
		this.coursService = coursService;
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


		return "view-formateur-detail";
	}

	@PostMapping("/detail")
	public String mettreAJourFormateur( @ModelAttribute("formateur") Formateur formateur) {
		
		
		this.formateurService.update(formateur);

		//redirige vers l'adresse responsable de l'affichage des formateurs
		return "redirect:/formateurs";
	}
	@PostMapping("/cours")
	public String ajouterCours(@RequestParam("email") String email, 
			@RequestParam("idCours") long idCours) {

		this.formateurService.updateCoursFormateur(email, idCours);

		return "redirect:/formateurs/detail?email=" + email;

	}
	
	@GetMapping("/creer")
	public String afficherCreationFormateur( Model model) {
		// Etape 1 : Création d'une instance de formateur
		Formateur formateur = new Formateur();
		
		//Etape 2 : Placer le formateur dans le model
		model.addAttribute("formateur", formateur);
		
		return "view-formateur-creation";
		
	}
	@PostMapping("/creer")
	public String creerFormateur(@Valid @ModelAttribute("formateur") Formateur formateur,
			BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "view-formateur-creation";
		}
		//appel du service en charge de la création du formateur
		this.formateurService.add(formateur);
		
		
		
		return "redirect:/formateurs";
		
	}


	@ModelAttribute("coursSession")
	public List<Cours> chargerCoursSession(){

		List<Cours> listeCours = this.coursService.getCours();
		System.out.println(listeCours);


		return listeCours;

	}

}
