package com.example.demo.service.impl;

import com.example.demo.entity.RiskAssessmentLog;
import com.example.demo.repository.RiskAssessmentLogRepository;
import com.example.demo.service.RiskAssessmentLogService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RiskAssessmentLogServiceImpl implements RiskAssessmentLogService {

    private final RiskAssessmentLogRepository repository;

    // ✅ CONSTRUCTOR NAME MUST MATCH CLASS NAME
    public RiskAssessmentLogServiceImpl(RiskAssessmentLogRepository repository) {
        this.repository = repository;
    }

    @Override
    public RiskAssessmentLog save(RiskAssessmentLog log) {
        return repository.save(log);
    }

    @Override
    public List<RiskAssessmentLog> getAll() {
        return repository.findAll();
    }
}
