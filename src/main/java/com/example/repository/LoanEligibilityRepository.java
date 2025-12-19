package com.example.demo.repository;

import com.example.demo.entity.LoanEligibilityRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EligibilityResultRepository extends JpaRepository<EligibilityResult, Long> {
    Optional<LoanEligibility> findByLoanRequestId(Long loanRequestId);
}
