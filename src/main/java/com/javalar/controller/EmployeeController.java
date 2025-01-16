package com.javalar.controller;

import com.javalar.service.EmployeeService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import com.javalar.model.Employee;

@WebServlet("/employees")
public class EmployeeController extends HttpServlet {

    @Inject
    private EmployeeService employeeService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Employee> employees = employeeService.findAll();
        System.out.println("Number of employees found: " + employees.size());
        for (Employee emp : employees) {
            System.out.println(emp.getName() + " - " + emp.getDepartment().getName());
        }
        request.setAttribute("employees", employees);
        request.getRequestDispatcher("/WEB-INF/jsp/employees.jsp").forward(request, response);
    }
}
