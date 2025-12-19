package com.example.demo.service.impl;

import com.example.demo.entity.FinancialProfile;
import com.example.demo.entity.LoanRequest;
import com.example.demo.entity.RiskAssessmentLog;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.FinancialProfileRepository;
import com.example.demo.repository.LoanRequestRepository;
import com.example.demo.repository.RiskAssessmentLogRepository;
import com.example.demo.service.RiskAssessmentService;
import org.springframework.stereotype.Service;

@Service
public class RiskAssessmentServiceImpl implements RiskAssessmentService {

    private final LoanRequestRepository loanRequestRepository;
    private final FinancialProfileRepository profileRepository;
    private final RiskAssessmentLogRepository logRepository;

    public RiskAssessmentServiceImpl(LoanRequestRepository loanRequestRepository,
                                     FinancialProfileRepository profileRepository,
                                     RiskAssessmentLogRepository logRepository) {
        this.loanRequestRepository = loanRequestRepository;
        this.profileRepository = profileRepository;
        this.logRepository = logRepository;
    }

    @Override
    public RiskAssessmentLog assessRisk(Long loanRequestId) {

        if (!logRepository.findByLoanRequestId(loanRequestId).isEmpty()) {
            throw new BadRequestException("Risk already assessed");
        }

        LoanRequest request = loanRequestRepository.findById(loanRequestId)
                .orElseThrow(() -> new ResourceNotFoundException("Loan request not found"));

        FinancialProfile profile = profileRepository.findByUserId(request.getUser().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Financial profile not found"));

        double obligations = profile.getMonthlyExpenses() +
                (profile.getExistingLoanEmi() == null ? 0 : profile.getExistingLoanEmi());
        double dti = obligations / profile.getMonthlyIncome();

        RiskAssessmentLog log = new RiskAssessmentLog();
        log.setLoanRequestId(request.getId());
        log.setDtiRatio(dti);
        log.setCreditCheckStatus(profile.getCreditScore() >= 650 ? "APPROVED" : "REJECTED");

        return logRepository.save(log);
    }

    @Override
    public RiskAssessmentLog getByLoanRequestId(Long loanRequestId) {
        return logRepository.findByLoanRequestId(loanRequestId).stream()
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Risk assessment not found"));
    }
}
