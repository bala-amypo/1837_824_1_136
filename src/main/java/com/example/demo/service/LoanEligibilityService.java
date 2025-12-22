package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class LoanEligibilityService {

    public String checkEligibility(Long income) {
        if (income >= 25000) {
            return "Eligible for Loan";
        } else {
            return "Not Eligible for Loan";
        }
    }
}
