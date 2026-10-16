package com.example.survey.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.survey.service.ImageStorageService;
import org.springframework.web.bind.annotation.ModelAttribute;
import java.io.IOException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.example.survey.entity.StoreEntity;
import com.example.survey.form.SurveyForm;
import com.example.survey.service.AgeService;
import com.example.survey.service.FrequencyService;
import com.example.survey.service.PurposeService;
import com.example.survey.service.StoreService;
import com.example.survey.service.SurveyService;
import com.example.survey.SurveyDto;

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
	private final ImageStorageService imageStorageService;   // フィールドに追加
	
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

	
	
		
	

	@PostMapping("/complete")
public String complete(@ModelAttribute("form") SurveyForm form, @RequestParam(required = false) String action, Model model) {
log.info("完了処理で受信した画像パス: {}", form.getImagePath());
log.info("画像パスの形式が正しいか: {}",
    imageStorageService.isValidPath(form.getImagePath()));
	// 画像のパスが書き換えられていたら捨てる
	if (!imageStorageService.isValidPath(form.getImagePath())) {
		form.setImagePath(null);
	}

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

	@GetMapping("/response")
	public String response(Model model) {
		model.addAttribute("list", surveyService.findAll());
		return "response";
	}

	@PostMapping("/confirm")
public String confirm(@Valid @ModelAttribute("form") SurveyForm form, BindingResult result,
        @RequestParam(required = false) MultipartFile imageFile, Model model) {

    // 戻る操作で引き回された値が書き換えられていたら捨てる
    if (!imageStorageService.isValidPath(form.getImagePath())) {
        form.setImagePath(null);
    }

    // 入力エラーがなければ、画像をアップロード
    if (!result.hasErrors() && imageFile != null && !imageFile.isEmpty()) {
        try {
            form.setImagePath(imageStorageService.upload(imageFile));
        } catch (IllegalArgumentException e) {
            result.rejectValue("imagePath", "image.invalid", e.getMessage());
        } catch (IOException e) {
            log.error("画像アップロード失敗", e);
            result.rejectValue("imagePath", "image.failed", "画像のアップロードに失敗しました");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            result.rejectValue("imagePath", "image.failed", "画像のアップロードに失敗しました");
        }
    }
log.info("バリデーションエラー: {}", result.getAllErrors());
log.info("画像ファイル: {}",
    imageFile == null ? "null" : imageFile.getOriginalFilename());
log.info("画像サイズ: {}",
    imageFile == null ? 0 : imageFile.getSize());
log.info("画像パス: {}", form.getImagePath());
   if (result.hasErrors()) {
			model.addAttribute("ageList", ageService.findAll());
			model.addAttribute("purposeList", purposeService.findAll());
			model.addAttribute("frequencyList", frequencyService.findAll());
			model.addAttribute("store", setStore(form.getStoreId()));
			return "survey";
		}
		
		SurveyDto dto = surveyService.setDto(form);
model.addAttribute("dto", dto);

if (dto.getImagePath() != null
        && !dto.getImagePath().isBlank()) {
    model.addAttribute(
        "imageUrl",
        imageStorageService.publicUrl(dto.getImagePath())
    );
}

		log.info("form{}", form);
		log.info("??{}", form.getStoreId());

		return "confirm";
}
}
