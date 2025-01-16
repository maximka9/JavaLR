package com.javalar.controller;

import com.javalar.model.Department;
import com.javalar.service.DepartmentService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/addDepartment")
public class AddDepartmentController extends HttpServlet {

    @Inject
    private DepartmentService departmentService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");

        if (name == null || name.isEmpty()) {
            request.setAttribute("errorMessage", "Department name is required.");
            request.getRequestDispatcher("/WEB-INF/jsp/addDepartment.jsp").forward(request, response);
            return;
        }

        Department department = new Department();
        department.setName(name);
        departmentService.create(department);

        response.sendRedirect("departments");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/addDepartment.jsp").forward(request, response);
    }
}
