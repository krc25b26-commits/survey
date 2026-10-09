package com.example.survey.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.survey.entity.AgeEntity;
import com.example.survey.repository.AgeRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AgeService {
	private final AgeRepository ageRepository;
	
	public List<AgeEntity> findAll() {
		return ageRepository.findAll();
	}
	
}
