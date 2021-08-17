package com.kyle.languages.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kyle.languages.models.Language;
import com.kyle.languages.services.LanguageService;

@Controller
public class LanguagesController {
	private final LanguageService languageService;

	public LanguagesController(LanguageService languageService) {
		this.languageService = languageService;
	}

	// READ ALL

	@RequestMapping("/languages")
	public String index(@ModelAttribute("language") Language language, Model model) {
		List<Language> languages = languageService.allLanguages();
		model.addAttribute("languages", languages);
		return "/languages/index.jsp";
	}
	
	
	//SHOW ONE
	
	@RequestMapping("/languages/{id}")
	public String showLanguage(@PathVariable("id") Long id, Model model) {
		Language language = languageService.findLanguage(id);
		model.addAttribute("language", language);
		return "/languages/show.jsp";
	}
	

	// CREATE

//	@RequestMapping("/languages/new")
//	public String newLanguage(@ModelAttribute("language") Language language) {
//		return "/languages/new.jsp";
//	}

	@RequestMapping(value = "/languages", method = RequestMethod.POST)
	public String create(@Valid @ModelAttribute("language") Language language, Model model, BindingResult result) {

		if (result.hasErrors()) {
			return "/languages/index.jsp";
		} else {
			languageService.createLanguage(language);
			return "redirect:/languages";
		}
	}

	// EDIT

	@RequestMapping("/languages/{id}/edit")
	public String edit(@ModelAttribute("language") Language language, @PathVariable("id") Long id, Model model) {
		Language lang = languageService.findLanguage(id);
		model.addAttribute("language", lang);
		return "/languages/edit.jsp";
	}

	@RequestMapping(value = "/languages/{id}", method = RequestMethod.PUT)
	public String update(@Valid @ModelAttribute("language") Language language, BindingResult result) {
		if (result.hasErrors()) {
			return "/languages/edit.jsp";
		} else {
			languageService.updateLanguage(language);
			return "redirect:/languages";
		}
	}

	// DELETE

	@RequestMapping(value = "/languages/{id}", method = RequestMethod.DELETE)
	public String destroy(@PathVariable("id") Long id) {
		languageService.deleteLanguage(id);
		return "redirect:/languages";
	}
}
