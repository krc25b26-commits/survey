package com.example.survey.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.survey.entity.PurposeEntity;

public interface PurposeRepository extends JpaRepository<PurposeEntity, Integer>{

}
