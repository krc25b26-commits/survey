package com.example.survey.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.survey.entity.StoreEntity;
import com.example.survey.repository.StoreRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class StoreService {
	private final StoreRepository storeRepository;
	
	public List<StoreEntity> findAll() {
		return storeRepository.findAll();
	}
	
	public StoreEntity findById(Integer id) {
		return storeRepository.findById(id).orElse(null);
	}

}
