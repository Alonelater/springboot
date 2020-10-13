package com.springboot03.controller;

import com.springboot03.dao.DepartmentDao;
import com.springboot03.dao.EmployeeDao;
import com.springboot03.entities.Department;
import com.springboot03.entities.Employee;
import com.sun.javafx.tk.TKPulseListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class EmployeeController {

    //查询结果
    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private DepartmentDao departmentDao;


    @RequestMapping(value = "/emps")
    public String emplist(Model model){
        Collection<Employee> all = employeeDao.getAll();
        model.addAttribute("emps",all);
        return "/emps/list";
    }
    @GetMapping("/emp")
    public String toAddPage(Model model){
        Collection<Department> all = departmentDao.getDepartments();
        model.addAttribute("depts",all);
        return "/emps/add";
    }

    @PostMapping("/emp")
    public String addEmp(Employee employee){
        System.out.println(employee);
       employeeDao.save(employee);
       return "redirect:/emps";
    }
    @RequestMapping("/emp/{id}")
    public String toUpdate(@PathVariable("id")int id,Model model){
        Employee employee = employeeDao.get(id);
        Collection<Department> all = departmentDao.getDepartments();
        model.addAttribute("depts",all);
        model.addAttribute("emp",employee);
        return "/emps/update";
    }
    @PostMapping("/update")
    public String updateEmp(Employee employee){
        employeeDao.save(employee);
        return "redirect:/emps";
    }
    @RequestMapping("/del/{id}")
    public String delEmp(@PathVariable("id")int id,Model model){
       employeeDao.delete(id);
        return "redirect:/emps";
    }
}
