package com.mindex.challenge.data;

public class ReportingStructure {
    public Employee employee;
    public Integer numberOfReports;

    public ReportingStructure(Employee employee, Integer numberOfReports) {
        this.employee = employee;
        this.numberOfReports = numberOfReports;
    }
}