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

@WebServlet("/editDepartment")
public class EditDepartmentController extends HttpServlet {

    @Inject
    private DepartmentService departmentService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        if (idParam != null) {
            Long id = Long.parseLong(idParam);
            Department department = departmentService.findById(id);
            if (department != null) {
                request.setAttribute("department", department);
                request.getRequestDispatcher("/WEB-INF/jsp/editDepartment.jsp").forward(request, response);
            } else {
                response.sendRedirect("departments");
            }
        } else {
            response.sendRedirect("departments");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");

        Department department = departmentService.findById(id);
        if (department != null) {
            department.setName(name);
            departmentService.update(department);
            response.sendRedirect("departments");
        } else {
            response.sendRedirect("departments");
        }
    }
}
