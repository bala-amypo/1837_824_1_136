package com.example.demo.service;

import com.example.demo.entity.LoanRequest;

public interface LoanRequestService {
    LoanRequest submitLoanRequest(LoanRequest request);
    LoanRequest getRequestById(Long id);
}
