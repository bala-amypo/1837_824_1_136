package com.example.demo.entity;
public class EligibilityResult{
    private Long id;
    private String loanRequest;
    private Boolean isEligible;
    private Double maxEligibleAmount;
    private Double EstimatedEmi;
    private String riskLevel;
    private String rejectionReason;
    private Timestamp calculatedAt;
}