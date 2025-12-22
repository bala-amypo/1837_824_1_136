package com.example.demo.controller;

import com.example.demo.entity.EligibilityResult;
import com.example.demo.service.LoanEligibilityService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eligibility")
public class EligibilityController {

    private final LoanEligibilityService service;

    public EligibilityController(LoanEligibilityService service) {
        this.service = service;
    }

    @GetMapping("/{requestId}")
    public EligibilityResult checkEligibility(@PathVariable Long requestId) {
        return service.getResultByRequest(requestId);
    }
}
