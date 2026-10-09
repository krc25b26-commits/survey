package com.example.survey.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.survey.entity.PurposeEntity;
import com.example.survey.entity.PurposeResponseEntity;
import com.example.survey.repository.PurposeRepository;
import com.example.survey.repository.PurposeResponseRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PurposeService {
	private final PurposeRepository purposeRepository;
	private final PurposeResponseRepository purposeResponseRepository;
	
	
	public List<PurposeEntity> findAll() {
		return purposeRepository.findAll();
	}
	
	public void save(List<Integer> list, Integer responseId) {
		 PurposeResponseEntity purposeResponseEntity = new PurposeResponseEntity();
		for(Integer value : list) {
			
			purposeResponseEntity.setPurposeId(value);
			purposeResponseEntity.setResponseId(responseId);
			purposeResponseRepository.save(purposeResponseEntity);
		}
		
	}
}
