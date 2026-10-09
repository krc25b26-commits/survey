package com.example.survey.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.survey.entity.SurveyResponseEntity;

public interface SurveyResponseRepository extends JpaRepository<SurveyResponseEntity, Integer>{

	@Query(value = "SELECT MAX(response_id) FROM survey_response", nativeQuery = true)
	Integer findMaxId();
}
