package com.example.demo.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.demo.service.RiskAssessmentLogService;
import com.example.demo.entity.RiskAssessmentLog;
import com.example.demo.repository.RiskAssessmentLogRepository;

@Service
public class RiskAssessmentLogServiceImpl implements RiskAssessmentLogService {

    private final RiskAssessmentLogRepository repo;

    public RiskAssessmentLogServiceImpl(RiskAssessmentLogRepository repo) {
        this.repo = repo;
    }

    public List<RiskAssessmentLog> getLogsByRequest(Long requestId) {
        return repo.findAll();
    }
}
