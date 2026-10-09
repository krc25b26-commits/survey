package com.example.survey.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.survey.SurveyDto;
import com.example.survey.entity.AgeEntity;
import com.example.survey.entity.FrequencyEntity;
import com.example.survey.entity.PurposeEntity;
import com.example.survey.entity.StoreEntity;
import com.example.survey.entity.SurveyResponseEntity;
import com.example.survey.form.SurveyForm;
import com.example.survey.repository.AgeRepository;
import com.example.survey.repository.FrequencyRepository;
import com.example.survey.repository.PurposeRepository;
import com.example.survey.repository.SurveyResponseRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class SurveyService {
	private final SurveyResponseRepository surveyResponseRepository;
	private final AgeRepository ageRepository;
	private final FrequencyRepository frequencyRepository;
	private final PurposeRepository purposeRepository;
	private final ModelMapper modelMapper;
	
	public SurveyResponseEntity setEntity(SurveyForm form) {
		SurveyResponseEntity entity = new SurveyResponseEntity();

		modelMapper.map(form, entity);

		AgeEntity ageEntity = new AgeEntity();
		FrequencyEntity frequencyEntity = new FrequencyEntity();
		PurposeEntity purposeEntity = new PurposeEntity();
		StoreEntity storeEntity = new StoreEntity();

		ageEntity.setId(form.getAge());
		frequencyEntity.setId(form.getFrequency());
		purposeEntity.setId(form.getFrequency());
		storeEntity.setId(form.getStoreId());

		entity.setAge(ageEntity);
		entity.setFrequency(frequencyEntity);
		entity.setStore(storeEntity);
		entity.setDate(getTime());

		return entity;
	}
	
	public Integer findByResponseId() {
		return surveyResponseRepository.findMaxId();
	}
	
	public SurveyDto setDto(SurveyForm form) {
		
		SurveyDto dto = new SurveyDto();
		
		
		modelMapper.map(form, dto);
		
		AgeEntity age = ageRepository.findById(form.getAge()).orElseThrow();
		dto.setAgeGroupLabel(age.getLabel());
		
		List<String> purpose = new ArrayList<>();
		for(Integer id : form.getPurpose()) {
			purpose.add(purposeRepository.findById(id).orElseThrow().getName());
		}
		dto.setPurposeNames(purpose);
		
		FrequencyEntity frequency = frequencyRepository.findById(form.getFrequency()).orElseThrow();
		dto.setFrequencyLabel(frequency.getLabel());
		
		log.info("dto：{}", dto);
		
		return dto;
		
	}

	//	DBに保存
	public void save(SurveyResponseEntity entity) {
		surveyResponseRepository.save(entity);
	}

	//	時刻取得
	private String getTime() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

		return now.format(f);
	}
}
