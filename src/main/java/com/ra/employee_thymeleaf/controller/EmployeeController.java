package com.ra.employee_thymeleaf.controller;

import com.ra.employee_thymeleaf.dao.IEmployee;
import com.ra.employee_thymeleaf.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

// http://localhost:8080/employee

@Controller
@RequestMapping
public class EmployeeController {
    @Autowired
    private IEmployee employeeDao;

    @GetMapping("/")
    public String home(Model model) {
        List<Employee> list = employeeDao.getEmployees();
        model.addAttribute("list", list);
        return "home";
    }

    @GetMapping("/add")
    public String add(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("employee") @Valid Employee employee, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("employee", employee);
            return "add";
        } else {
            employeeDao.insertEmployee(employee);
            return "redirect:/";
        }

    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") Integer id) {
        Employee employee = employeeDao.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "edit";
    }

    @PostMapping("/edit")
    public String edit(@Valid @ModelAttribute("employee") Employee employee, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("employee", employee);
            return "edit";
        } else {
            employeeDao.updateEmployee(employee);
            return "redirect:/";
        }
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        employeeDao.deleteEmployee(id);
        return "redirect:/";
    }

}
