package com.springboot.service;

import com.springboot.dao.EmployeeDao;
import com.springboot.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    public void addEmployee(Employee employee){
        employeeDao.addEmployee(employee);
    }

    public Collection<Employee> getAll() {
        return employeeDao.getAll();
    }

    public Employee getEmployeeById(Integer id) {
        return employeeDao.getEmployeeById(id);
    }

    public void deleteEmployeeById(Integer id) {
        employeeDao.deleteEmployeeById(id);
    }
}
