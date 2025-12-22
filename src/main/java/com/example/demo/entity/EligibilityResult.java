package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class EligibilityResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean eligible;
    private Double maxEligibleAmount;

    @OneToOne
    private LoanRequest loanRequest;

    public void setEligible(Boolean eligible) {
        this.eligible = eligible;
    }

    public void setMaxEligibleAmount(Double maxEligibleAmount) {
        this.maxEligibleAmount = maxEligibleAmount;
    }

    public void setLoanRequest(LoanRequest loanRequest) {
        this.loanRequest = loanRequest;
    }
}
