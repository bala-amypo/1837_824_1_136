package com.example.demo.repository;

import com.example.demo.entity.EligibilityResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoanEligibilityRepository extends JpaRepository<EligibilityResult, Long> {

    Optional<EligibilityResult> findByLoanRequestId(Long loanRequestId);
}
