package com.example.demo.service.impl;

import com.example.demo.entity.LoanRequest;
import com.example.demo.repository.LoanRequestRepository;
import com.example.demo.service.LoanRequestService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanRequestServiceImpl implements LoanRequestService {

    private final LoanRequestRepository repo;

    public LoanRequestServiceImpl(LoanRequestRepository repo) {
        this.repo = repo;
    }

    @Override
    public LoanRequest submitLoanRequest(LoanRequest request) {
        return repo.save(request);
    }

    @Override
    public LoanRequest getRequestById(Long id) {
        return repo.findById(id).orElseThrow();
    }

    @Override
    public List<LoanRequest> getRequestsByUser(Long userId) {
        return repo.findByUserId(userId);
    }

    @Override
    public List<LoanRequest> getAllRequests() {
        return repo.findAll();
    }
}
