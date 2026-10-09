package com.example.survey.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.survey.entity.PurposeResponseEntity;
import com.example.survey.entity.PurposeResponseId;

public interface PurposeResponseRepository
        extends JpaRepository<PurposeResponseEntity, PurposeResponseId> {

}