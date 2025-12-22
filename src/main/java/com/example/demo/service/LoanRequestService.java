package com.example.demo.service;

import com.example.demo.entity.LoanRequest;
import java.util.List;

public interface LoanRequestService {

    List<LoanRequest> getRequestsByUser(Long userId);

    List<LoanRequest> getAllRequests();
}
