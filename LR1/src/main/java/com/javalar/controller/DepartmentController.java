package com.javalar.controller;

import com.javalar.service.DepartmentService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import com.javalar.model.Department;

@WebServlet("/departments")
public class DepartmentController extends HttpServlet {

    @Inject
    private DepartmentService departmentService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Department> departments = departmentService.findAll();
        request.setAttribute("departments", departments);
        request.getRequestDispatcher("/WEB-INF/jsp/departments.jsp").forward(request, response);
    }
}
