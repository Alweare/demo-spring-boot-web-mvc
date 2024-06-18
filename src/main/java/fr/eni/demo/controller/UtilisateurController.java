package fr.eni.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"utilisateurSession"})
public class UtilisateurController {
	
	@GetMapping("/utilisateurs")
	public String afficherContexte() {
		
		return "contexte/view-contexte";
		
	}
	

}
