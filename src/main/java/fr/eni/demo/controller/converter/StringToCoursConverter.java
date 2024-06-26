package fr.eni.demo.controller.converter;

import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import fr.eni.demo.bll.CoursService;
import fr.eni.demo.bo.Cours;

import org.springframework.core.convert.converter.Converter;
/** 
 * 
 * Le converter sera appelé afin d'injecter un cours 
 * dans la liste de cours formateur
 * 
 * */

@Component
public class StringToCoursConverter implements Converter<String, Cours>{
	private CoursService coursService;
	
	

	public StringToCoursConverter(CoursService coursService) {
		this.coursService = coursService;
	}



	@Override
	public Cours convert(String idCours) {
		
		return coursService.findById(Long.parseLong(idCours));
	}

	
	
	
}
