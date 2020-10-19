package com.springboot.controller;

import com.springboot.pojo.Employee;
import com.springboot.service.DepartmentService;
import com.springboot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/employeeList")
    public String employeeList(Model model) {
        model.addAttribute("employeeList", employeeService.getAll());
        return "employee/list";
    }

    @GetMapping("/addEmployee")
    public String toAddEmployee(Model model) {
        model.addAttribute("departments", departmentService.getDepartments());
        return "employee/add";
    }

    @PostMapping("/addEmployee")
    public String addEmployee(Employee employee) {
        employeeService.addEmployee(employee);
        return "redirect:/employeeList";
    }

    @GetMapping("/updateEmployee/{id}")
    public String toUpdateEmployee(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("employee", employeeService.getEmployeeById(id));
        model.addAttribute("departments", departmentService.getDepartments());
        return "employee/update";
    }

    @PostMapping("/updateEmployee/{id}")
    public String updateEmployee(@PathVariable("id") Integer id, Employee employee) {
        employee.setId(id);
        employeeService.addEmployee(employee);
        return "redirect:/employeeList";
    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id) {
        employeeService.deleteEmployeeById(id);
        return "redirect:/employeeList";
    }
}
