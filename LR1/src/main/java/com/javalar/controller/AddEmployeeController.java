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

@WebServlet("/addEmployee")
public class AddEmployeeController extends HttpServlet {

    @EJB
    private EmployeeService employeeService;

    @EJB
    private DepartmentService departmentService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String ageStr = request.getParameter("age");
        String salaryStr = request.getParameter("salary");
        String departmentStr = request.getParameter("department");

        if (name == null || name.isEmpty() || ageStr == null || salaryStr == null || departmentStr == null) {
            request.setAttribute("errorMessage", "All fields are required.");
            request.getRequestDispatcher("/WEB-INF/jsp/addEmployee.jsp").forward(request, response);
            return;
        }

        try {
            int age = Integer.parseInt(ageStr);
            double salary = Double.parseDouble(salaryStr);
            Long departmentId = Long.parseLong(departmentStr);
            Department department = departmentService.findById(departmentId);

            if (department == null) {
                request.setAttribute("errorMessage", "Department not found.");
                request.getRequestDispatcher("/WEB-INF/jsp/addEmployee.jsp").forward(request, response);
                return;
            }

            Employee employee = new Employee();
            employee.setName(name);
            employee.setAge(age);
            employee.setSalary(salary);
            employee.setDepartment(department);

            employeeService.create(employee);
            response.sendRedirect("employees");
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid number format.");
            request.getRequestDispatcher("/WEB-INF/jsp/addEmployee.jsp").forward(request, response);
        }
    }
}
