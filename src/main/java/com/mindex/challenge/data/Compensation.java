package com.mindex.challenge.data;

public class Compensation {
    private String compensationId;
    private Integer salary;
    private Integer effectiveDate;
    private Employee employee;

    public Compensation() {
    }  

    public static String makeCompensationId(String employeeId) {
        return "COMPENSATION-" + employeeId;
    }
    
    public String getCompensationId() {
        return compensationId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Integer getSalary() {
        return salary;
    }

    public Integer getEffectiveDate() {
        return effectiveDate;
    }

    public void setEmployee(Employee employee) {
        this.compensationId = makeCompensationId(employee.getEmployeeId());
        this.employee = employee;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public void setEffectiveDate(Integer effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
}