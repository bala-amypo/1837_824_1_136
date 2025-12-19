package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.LoanEligibilityService;
import org.springframework.stereotype.Service;

@Service
public class LoanEligibilityServiceimpl implements LoanEligibilityService {

    private final LoanRequestRepository loanRequestRepository;
    private final FinancialProfileRepository profileRepository;
    private final EligibilityResultRepository eligibilityResultRepository;

    public LoanEligibilityServiceimpl(LoanRequestRepository loanRequestRepository,
                                      FinancialProfileRepository profileRepository,
                                      EligibilityResultRepository eligibilityResultRepository) {
        this.loanRequestRepository = loanRequestRepository;
        this.profileRepository = profileRepository;
        this.eligibilityResultRepository = eligibilityResultRepository;
    }

    @Override
    public EligibilityResult evaluateEligibility(Long loanRequestId) {

        if (eligibilityResultRepository.findByLoanRequestId(loanRequestId).isPresent()) {
            throw new BadRequestException("Eligibility already evaluated");
        }

        LoanRequest loanRequest = loanRequestRepository.findById(loanRequestId)
                .orElseThrow(() -> new ResourceNotFoundException("Loan request not found"));

        FinancialProfile profile = profileRepository.findByUserId(
                loanRequest.getUser().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Financial profile not found"));

        double totalObligations =
                profile.getMonthlyExpenses() +
                (profile.getExistingLoanEmi() == null ? 0 : profile.getExistingLoanEmi());

        double dti = totalObligations / profile.getMonthlyIncome();

        boolean eligible = profile.getCreditScore() >= 650 && dti <= 0.5;

        String riskLevel;
        if (dti < 0.3 && profile.getCreditScore() >= 750) {
            riskLevel = "LOW";
        } else if (dti <= 0.5) {
            riskLevel = "MEDIUM";
        } else {
            riskLevel = "HIGH";
        }

        double maxEligibleAmount = eligible
                ? profile.getMonthlyIncome() * 20
                : 0;

        double estimatedEmi = eligible
                ? (maxEligibleAmount / loanRequest.getTenureMonths()) * 1.1
                : 0;

        EligibilityResult result = new EligibilityResult(
                loanRequest,
                eligible,
                maxEligibleAmount,
                estimatedEmi,
                riskLevel,
                eligible ? null : "Not eligible based on risk"
        );

        return eligibilityResultRepository.save(result);
    }

    @Override
    public EligibilityResult getByLoanRequestId(Long loanRequestId) {
        return eligibilityResultRepository.findByLoanRequestId(loanRequestId)
                .orElseThrow(() -> new ResourceNotFoundException("Eligibility result not found"));
    }
}
