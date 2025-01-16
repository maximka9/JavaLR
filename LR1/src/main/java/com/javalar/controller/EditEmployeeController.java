package com.javalar.controller;

import com.javalar.model.Department;
import com.javalar.model.Employee;
import com.javalar.service.DepartmentService;
import com.javalar.service.EmployeeService;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/editEmployee")
public class EditEmployeeController extends HttpServlet {

    @EJB
    private EmployeeService employeeService;

    @EJB
    private DepartmentService departmentService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        if (idParam != null) {
            Long id = Long.parseLong(idParam);
            Employee employee = employeeService.findById(id);
            request.setAttribute("employee", employee);
        }
        List<Department> departments = departmentService.findAll();
        request.setAttribute("departments", departments);

        request.getRequestDispatcher("/WEB-INF/jsp/editEmployee.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        double salary = Double.parseDouble(request.getParameter("salary"));
        Long departmentId = Long.parseLong(request.getParameter("department"));

        Department department = departmentService.findById(departmentId);

        if (department == null) {
            request.setAttribute("errorMessage", "Department not found.");
            request.getRequestDispatcher("/WEB-INF/jsp/editEmployee.jsp").forward(request, response);
            return;
        }

        Employee employee = new Employee();
        employee.setId(id);
        employee.setName(name);
        employee.setAge(age);
        employee.setSalary(salary);
        employee.setDepartment(department);

        employeeService.update(employee);

        response.sendRedirect("employees");
    }
}
