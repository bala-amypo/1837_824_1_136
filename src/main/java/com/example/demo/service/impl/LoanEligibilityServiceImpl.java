package com.example.demo.service.impl;

import com.example.demo.entity.EligibilityResult;
import com.example.demo.entity.LoanRequest;
import com.example.demo.repository.EligibilityResultRepository;
import com.example.demo.repository.LoanRequestRepository;
import com.example.demo.service.LoanEligibilityService;
import org.springframework.stereotype.Service;

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

    @Override
    public EligibilityResult evaluateEligibility(Long requestId) {
        LoanRequest loan = loanRepo.findById(requestId).orElseThrow();

        EligibilityResult result = new EligibilityResult();
        result.setLoanRequest(loan);
        result.setEligible(true);
        result.setMaxEligibleAmount(loan.getRequestedAmount() * 0.8);

        return repo.save(result);
    }
}
