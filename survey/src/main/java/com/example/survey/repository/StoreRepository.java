package com.example.survey.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.survey.entity.StoreEntity;

public interface StoreRepository extends JpaRepository<StoreEntity, Integer>{

}
