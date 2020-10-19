package com.springboot.dao;

import com.springboot.pojo.Department;
import com.springboot.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class EmployeeDao {

    // 模拟数据库中数据
    private static Map<Integer, Employee> employees;

    @Autowired
    private DepartmentDao departmentDao;  //每个员工有一个所属部门

    static {
        employees = new HashMap<>();  // 创建一个员工表
        employees.put(1001, new Employee(1001, "AA", "A10012345@qq.com", 0, new Department(101, "外交部")));
        employees.put(1002, new Employee(1002, "BB", "B10012345@qq.com", 1, new Department(101, "工信部")));
        employees.put(1003, new Employee(1003, "CC", "C10012345@qq.com", 0, new Department(101, "教育部")));
        employees.put(1004, new Employee(1004, "DD", "D10012345@qq.com", 1, new Department(101, "公安部")));
        employees.put(1005, new Employee(1005, "EE", "E10012345@qq.com", 0, new Department(101, "财政部")));
    }

    // 操作数据库
    // id自增
    private static Integer initId = 1006;
    // 增加一名员工
    public void addEmployee(Employee employee){
        if(employee.getId() == null){
            employee.setId(initId++);
        }
        employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));
        employees.put(employee.getId(), employee);
    }

    // 查询全部员工信息
    public Collection<Employee> getAll() {
        return employees.values();
    }

    // 通过id查询员工
    public Employee getEmployeeById(Integer id) {
        return employees.get(id);
    }

    // 通过id删除员工
    public void deleteEmployeeById(Integer id) {
        employees.remove(id);
    }

}
