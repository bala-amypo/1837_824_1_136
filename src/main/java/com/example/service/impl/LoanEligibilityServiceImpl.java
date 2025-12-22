package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import com.example.demo.service.LoanEligibilityService;
import com.example.demo.entity.*;
import com.example.demo.repository.*;

@Service
public class LoanEligibilityServiceImpl implements LoanEligibilityService {

    private final EligibilityResultRepository repo;
    private final LoanRequestRepository loanRepo;

    public LoanEligibilityServiceImpl(
            EligibilityResultRepository repo,
            LoanRequestRepository loanRepo) {
        this.repo = repo;
        this.loanRepo = loanRepo;
    }

    public EligibilityResult getResultByRequest(Long requestId) {
        LoanRequest loan = loanRepo.findById(requestId).orElseThrow();

        EligibilityResult result = new EligibilityResult();
        result.setLoanRequest(loan);
        result.setEligible(true);
        result.setMaxEligibleAmount(loan.getRequestedAmount() * 0.8);

        return repo.save(result);
    }
}
