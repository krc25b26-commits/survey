package com.example.survey.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.survey.entity.StoreEntity;
import com.example.survey.form.SurveyForm;
import com.example.survey.service.AgeService;
import com.example.survey.service.FrequencyService;
import com.example.survey.service.PurposeService;
import com.example.survey.service.StoreService;
import com.example.survey.service.SurveyService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/survey")
public class SurveyController {

	private final AgeService ageService;
	private final FrequencyService frequencyService;
	private final PurposeService purposeService;
	private final StoreService storeService;
	private final SurveyService surveyService;
	
	public StoreEntity setStore(Integer id) {
		return storeService.findById(id);
	}

	@GetMapping
	public String survey(@RequestParam(required = false) Integer id, Model model) {
		log.info("store：{}", setStore(id));
		
		//store_idが存在しなければエラー
	    if (setStore(id) == null) {
	    	
	        return "error/404";  
	    }
	    
		model.addAttribute("ageList", ageService.findAll());
		model.addAttribute("purposeList", purposeService.findAll());
		model.addAttribute("frequencyList", frequencyService.findAll());
		model.addAttribute("store", setStore(id));
		model.addAttribute("form", new SurveyForm());
		
		return "survey";
	}
	
	@PostMapping
	public String back(@ModelAttribute("form") SurveyForm form, Model model) {
		log.info("form : {}", form);
		model.addAttribute("ageList", ageService.findAll());
		model.addAttribute("purposeList", purposeService.findAll());
		model.addAttribute("frequencyList", frequencyService.findAll());
		model.addAttribute("store", setStore(form.getStoreId()));
		return "survey";
	}

	@PostMapping("/confirm")
	public String confirm(@Valid @ModelAttribute("form") SurveyForm form, BindingResult result, Model model) {
		
		if (result.hasErrors()) {
			model.addAttribute("ageList", ageService.findAll());
			model.addAttribute("purposeList", purposeService.findAll());
			model.addAttribute("frequencyList", frequencyService.findAll());
			model.addAttribute("store", setStore(form.getStoreId()));
			return "survey";
		}
		
		model.addAttribute("dto", surveyService.setDto(form));

		log.info("form{}", form);
		log.info("??{}", form.getStoreId());

		return "confirm";
	}

	@PostMapping("/complete")
	public String complete(@ModelAttribute("form") SurveyForm form, @RequestParam(required = false) String action, Model model) {
		
		log.info("{}", form);
		log.info("{}", surveyService.setEntity(form));
		log.info("id:{}", surveyService.findByResponseId());
		
		surveyService.save(surveyService.setEntity(form));
		purposeService.save(form.getPurpose(), surveyService.findByResponseId());

		return "redirect:/survey/complete";
	}
	
	@GetMapping("/complete")
	public String complete() {
		return "complete";
	}
}
