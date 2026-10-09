package com.example.survey.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.survey.entity.FrequencyEntity;
import com.example.survey.repository.FrequencyRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class FrequencyService {
	private final FrequencyRepository frequencyRepository;
	
	public List<FrequencyEntity> findAll() {
		return frequencyRepository.findAll();
	}
}
