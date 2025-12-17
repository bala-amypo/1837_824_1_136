package com.example.demo.entity;
public class LoanRequest{
    private Long id;
    private String user;
    private Double reqestedAmount;
    private Integer tenureMonths;
    private String purpose;
    private String status;
    private Timestamp appliedAt;
    public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}

public String getUser() {
    return user;
}

public void setUser(String user) {
    this.user = user;
}

public Double getReqestedAmount() {
    return reqestedAmount;
}

public void setReqestedAmount(Double reqestedAmount) {
    this.reqestedAmount = reqestedAmount;
}

public Integer getTenureMonths() {
    return tenureMonths;
}

public void setTenureMonths(Integer tenureMonths) {
    this.tenureMonths = tenureMonths;
}

public String getPurpose() {
    return purpose;
}

public void setPurpose(String purpose) {
    this.purpose = purpose;
}

public String getStatus() {
    return status;
}

public void setStatus(String status) {
    this.status = status;
}

public Timestamp getAppliedAt() {
    return appliedAt;
}

public void setAppliedAt(Timestamp appliedAt) {
    this.appliedAt = appliedAt;
}

}