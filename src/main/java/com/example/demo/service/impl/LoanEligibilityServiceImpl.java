package com.example.demo.service.impl;

import com.example.demo.service.LoanEligibilityService;
import org.springframework.stereotype.Service;

@Service
public class LoanEligibilityServiceImpl implements LoanEligibilityService {

    @Override
    public String checkEligibility(Long income) {
        if (income >= 25000) {
            return "Eligible for Loan";
        }
        return "Not Eligible for Loan";
    }
}
