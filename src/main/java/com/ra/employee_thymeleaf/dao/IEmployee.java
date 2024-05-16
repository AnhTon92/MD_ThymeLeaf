package com.ra.employee_thymeleaf.dao;



import com.ra.employee_thymeleaf.model.Employee;

import java.util.List;

public interface IEmployee {
    public List<Employee> getEmployees();
    public Employee getEmployeeById(Integer employeeId);
    public boolean insertEmployee(Employee employee);
    public boolean updateEmployee(Employee employee);
    public boolean deleteEmployee(Integer employeeId);
    public List<Employee> getEmployeeByName(String name);

    Boolean existByPhone(String phone) ;


}

