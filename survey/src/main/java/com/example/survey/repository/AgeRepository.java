package com.example.survey.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.survey.entity.AgeEntity;

public interface AgeRepository extends JpaRepository<AgeEntity, Integer>{

}
