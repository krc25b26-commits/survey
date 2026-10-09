package com.example.survey;

import java.util.List;

import com.example.survey.entity.StoreEntity;

import lombok.Data;

@Data
public class SurveyDto {
	
	StoreEntity store;
	
	String name;
	
	String ageGroupLabel;
	
	List<String> purposeNames;
	
	String frequencyLabel;
	
	Integer food;
	
	Integer service;
	
	Integer cleanliness;
	
	String menu;
	
	String comment;
	
	String email;
	
	Boolean agreementFlg;
	
	Boolean hamburgOrder;
	
	Integer hamburgSatisfaction;
}
