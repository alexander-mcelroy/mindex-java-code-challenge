package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {

    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public ReportingStructure read(String employeeId) {
        LOG.debug("Reading reporting structure of employee with id [{}]", employeeId);

        Employee employee = employeeRepository.findByEmployeeId(employeeId);

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + employeeId);
        }
        
        List<Employee> path = new ArrayList<Employee>();
        path.add(employee);
        Integer numberOfReports = dfsCountReports(employee, path);
        return new ReportingStructure(employee, numberOfReports);
    }

    private Integer dfsCountReports(Employee employee, List<Employee> path) {
        Integer count = 0;
        List<Employee> directReports = employee.getDirectReports();
        if (directReports == null) 
            return 0;

        for(Employee drId : employee.getDirectReports()) {
            Employee dr = employeeRepository.findByEmployeeId(drId.getEmployeeId());
            if (dr == null) {
                throw new RuntimeException("Invalid employeeId: " + drId.getEmployeeId());
            } else if (path.contains(dr)) {
                throw new RuntimeException("non-DAG heirarchy, cycle discovered at employeeId: " + drId.getEmployeeId());
            }

            path.add(dr);
            count += 1 + dfsCountReports(dr, path);
        }
        return count;
    }
}
