package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class RiskAssessmentLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private LoanRequest loanRequest;
}
