package com.example.survey.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "survey_response")
public class SurveyResponseEntity {
//	店舗
	@ManyToOne
	@JoinColumn(name = "store_id")
	private StoreEntity store;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer responseId;
//	名前
	private String name;
//	年齢
	@ManyToOne
	@JoinColumn(name = "age_id")
	private  AgeEntity age;
//	頻度
	@ManyToOne
	@JoinColumn(name = "frequency_id")
	private FrequencyEntity frequency;
//	料理の満足度
	@Column(name = "food_satisfaction")
	private Integer food;
//	接客の満足度
	@Column(name = "service_satisfaction")
	private Integer service;
//	店内の清潔さ
	@Column(name = "cleanliness")
	private Integer cleanliness;
//	おすすめのメニュー
	@Column(name = "recommended_menu")
	private String menu;
//	バカうまハンバーグの注文
	@Column(name = "hamburg_order")
	private Boolean hamburgOrder;
//	バカうまハンバーグの満足度
	@Column(name = "hamburg_satisfaction")
	private Integer hamburgSatisfaction;
//	意見・感想
	@Column(name = "comment")
	private String comment;
//	メールアドレス
	@Column(name = "email")
	private String email;
//	日時
	@Column(name = "created_at")
	private String date;
	
}
