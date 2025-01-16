package com.javalar.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/addDepartmentForm")
public class AddDepartmentFormController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Отправляем пользователя на страницу с формой добавления департамента
        request.getRequestDispatcher("/WEB-INF/jsp/addDepartment.jsp").forward(request, response);
    }
}
