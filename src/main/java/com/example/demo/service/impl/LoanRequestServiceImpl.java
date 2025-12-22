package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import com.example.demo.service.LoanRequestService;
import com.example.demo.entity.LoanRequest;
import com.example.demo.repository.LoanRequestRepository;

@Service
public class LoanRequestServiceImpl implements LoanRequestService {

    private final LoanRequestRepository repo;

    public LoanRequestServiceImpl(LoanRequestRepository repo) {
        this.repo = repo;
    }

    public LoanRequest submitLoanRequest(LoanRequest request) {
        return repo.save(request);
    }

    public LoanRequest getRequestById(Long id) {
        return repo.findById(id).orElseThrow();
    }
}
