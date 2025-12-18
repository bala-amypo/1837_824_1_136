 package com.example.demo.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.sql.Timestamp;

@Entity
public class EligibilityResult{
    @Id
     @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String loanRequest;
    private Boolean isEligible;
    private Double maxEligibleAmount;
    private Double EstimatedEmi;
    private String riskLevel;
    private String rejectionReason;
    private Timestamp calculatedAt;
    public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}

public String getLoanRequest() {
    return loanRequest;
}

public void setLoanRequest(String loanRequest) {
    this.loanRequest = loanRequest;
}

public Boolean getIsEligible() {
    return isEligible;
}

public void setIsEligible(Boolean isEligible) {
    this.isEligible = isEligible;
}

public Double getMaxEligibleAmount() {
    return maxEligibleAmount;
}

public void setMaxEligibleAmount(Double maxEligibleAmount) {
    this.maxEligibleAmount = maxEligibleAmount;
}

public Double getEstimatedEmi() {
    return EstimatedEmi;
}

public void setEstimatedEmi(Double EstimatedEmi) {
    this.EstimatedEmi = EstimatedEmi;
}

public String getRiskLevel() {
    return riskLevel;
}

public void setRiskLevel(String riskLevel) {
    this.riskLevel = riskLevel;
}

public String getRejectionReason() {
    return rejectionReason;
}

public void setRejectionReason(String rejectionReason) {
    this.rejectionReason = rejectionReason;
}

public Timestamp getCalculatedAt() {
    return calculatedAt;
}

public void setCalculatedAt(Timestamp calculatedAt) {
    this.calculatedAt = calculatedAt;
}
public EligibilityResult(Long id,String loanRequest,Boolean isEligible,Double maxEligibleAmount,Double EstimatedEmi,String riskLevel,String rejectionReason,Timestamp calculatedAt){
    this.id=id;
    this.loanRequest=loanRequest;
    this.isEligible=isEligible;
    this.maxEligibleAmount=maxEligibleAmount;
    this.EstimatedEmi=EstimatedEmi;
    this.riskLevel=riskLevel;
    this.rejectionReason=rejectionReason;
    this.calculatedAt=calculatedAt;

}
public EligibilityResult(){

}
}