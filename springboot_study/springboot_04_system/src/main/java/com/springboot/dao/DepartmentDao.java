package com.springboot.dao;

import com.springboot.pojo.Department;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class DepartmentDao {

    // 模拟数据库中数据

    private static Map<Integer, Department> departments;

    static {
        departments = new HashMap<>(); //创建一个部门表
        departments.put(101, new Department(101, "外交部"));
        departments.put(102, new Department(102, "工信部"));
        departments.put(103, new Department(103, "教育部"));
        departments.put(104, new Department(104, "公安部"));
        departments.put(105, new Department(105, "财政部"));

    }

    // 操作数据库
    // 获得所有部门的信息
    public Collection<Department> getDepartments() {
        return departments.values();
    }

    // 通过id查询部门信息
    public Department getDepartmentById(Integer id) {
        return departments.get(id);
    }
}
