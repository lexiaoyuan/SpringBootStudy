package com.springboot.service;

import com.springboot.dao.DepartmentDao;
import com.springboot.pojo.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentDao departmentDao;

    public Collection<Department> getDepartments() {
        return departmentDao.getDepartments();
    }

    public Department getDepartmentById(Integer id) {
        return departmentDao.getDepartmentById(id);
    }
}
