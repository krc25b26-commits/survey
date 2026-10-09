package com.example.survey.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class PurposeResponseId implements Serializable {

    private Integer responseId;

    private Integer purposeId;
}