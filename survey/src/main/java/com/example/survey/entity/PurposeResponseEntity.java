package com.example.survey.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@IdClass(PurposeResponseId.class)
@Table(name = "purpose_response")
public class PurposeResponseEntity {

    @Id
    private Integer responseId;

    @Id
    private Integer purposeId;
}