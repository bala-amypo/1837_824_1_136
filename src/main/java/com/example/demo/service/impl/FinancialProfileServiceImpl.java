package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import com.example.demo.service.FinancialProfileService;
import com.example.demo.entity.FinancialProfile;
import com.example.demo.repository.FinancialProfileRepository;

@Service
public class FinancialProfileServiceImpl implements FinancialProfileService {

    private final FinancialProfileRepository repo;

    public FinancialProfileServiceImpl(FinancialProfileRepository repo) {
        this.repo = repo;
    }

    public FinancialProfile createOrUpdateProfile(FinancialProfile profile) {
        return repo.save(profile);
    }

    public FinancialProfile getProfileByUser(Long userId) {
        return repo.findAll().stream()
                .filter(p -> p.getUser().getId().equals(userId))
                .findFirst()
                .orElse(null);
    }
}
