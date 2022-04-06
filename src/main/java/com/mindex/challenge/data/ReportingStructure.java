package com.mindex.challenge.data;

public class ReportingStructure {
    private Employee employee;
    private Integer numberOfReports;

    public ReportingStructure(Employee employee, Integer numberOfReports) {
        this.employee = employee;
        this.numberOfReports = numberOfReports;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Integer getNumberOfReports() {
        return numberOfReports;
    }
}