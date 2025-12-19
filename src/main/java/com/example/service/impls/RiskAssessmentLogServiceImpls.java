package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.RiskAssessmentService;
import org.springframework.stereotype.Service;

@Service
public class RiskAssessmentServiceimpl implements RiskAssessmentService {

    private final LoanRequestRepository loanRequestRepository;
    private final FinancialProfileRepository profileRepository;
    private final RiskAssessmentLogRepository riskLogRepository;

    public RiskAssessmentServiceimpl(LoanRequestRepository loanRequestRepository,
                                     FinancialProfileRepository profileRepository,
                                     RiskAssessmentLogRepository riskLogRepository) {
        this.loanRequestRepository = loanRequestRepository;
        this.profileRepository = profileRepository;
        this.riskLogRepository = riskLogRepository;
    }

    @Override
    public RiskAssessmentLog assessRisk(Long loanRequestId) {

        if (!riskLogRepository.findByLoanRequestId(loanRequestId).isEmpty()) {
            throw new BadRequestException("Risk already assessed");
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

        String creditStatus =
                profile.getCreditScore() >= 700 ? "APPROVED"
                : profile.getCreditScore() >= 600 ? "PENDING_REVIEW"
                : "REJECTED";

        RiskAssessmentLog log = new RiskAssessmentLog(
                loanRequestId,
                dti,
                creditStatus
        );

        return riskLogRepository.save(log);
    }

    @Override
    public RiskAssessmentLog getByLoanRequestId(Long loanRequestId) {
        return riskLogRepository.findByLoanRequestId(loanRequestId)
                .stream()
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Risk log not found"));
    }
}
