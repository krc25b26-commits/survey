package com.example.survey.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.survey.entity.SurveyResponseEntity;
import com.example.survey.form.SurveyForm;

@Configuration
public class AppConfig {
	
	@Bean // このメソッドの戻り値を Bean として登録する
	public ModelMapper modelMapper() {

		ModelMapper modelMapper = new ModelMapper();

		// Entity → Form 方向でジャンルをスキップする設定
		// （型が異なるため自動マッピングしようとするとエラーになる）
		modelMapper.typeMap(SurveyForm.class, SurveyResponseEntity.class)
				.addMappings(mapper -> {
					mapper.skip(SurveyResponseEntity::setAge);
					mapper.skip(SurveyResponseEntity::setFrequency);
				});
		

		return modelMapper;
	}

}
