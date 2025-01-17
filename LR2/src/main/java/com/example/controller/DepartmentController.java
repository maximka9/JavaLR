package com.example.controller;

import com.example.model.Department;
import com.example.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    // Отображаем список департаментов
    @GetMapping
    public String listDepartments(Model model) {
        List<Department> departments = departmentService.getAllDepartments();
        model.addAttribute("departments", departments);
        return "department/list"; // Шаблон для отображения списка департаментов
    }

    // Форма для добавления нового департамента
    @GetMapping("/add")
    public String addDepartmentForm(Model model) {
        model.addAttribute("department", new Department());
        return "department/add"; // Шаблон для добавления департамента
    }

    // Сохраняем новый департамент
    @PostMapping("/add")
    public String saveDepartment(@ModelAttribute Department department) {
        departmentService.saveDepartment(department);  // Сохраняем департамент
        return "redirect:/departments"; // После добавления перенаправляем на список департаментов
    }

    // Форма для редактирования существующего департамента
    @GetMapping("/edit/{id}")
    public String editDepartmentForm(@PathVariable("id") Long id, Model model) {
        Optional<Department> departmentOptional = departmentService.getDepartmentById(id);
        if (departmentOptional.isPresent()) {
            model.addAttribute("department", departmentOptional.get());
            return "department/edit"; // Шаблон для редактирования департамента
        }
        return "redirect:/departments"; // Если департамент не найден, перенаправляем на список
    }

    // Обновляем данные департамента
    @PostMapping("/edit/{id}")
    public String updateDepartment(@PathVariable("id") Long id, @ModelAttribute Department department) {
        department.setId(id); // Устанавливаем ID, чтобы обновить существующий департамент
        departmentService.saveDepartment(department);  // Сохраняем департамент
        return "redirect:/departments"; // Перенаправляем на список департаментов
    }

    // Удаляем департамент
    @GetMapping("/delete/{id}")
    public String deleteDepartment(@PathVariable("id") Long id) {
        departmentService.deleteDepartment(id);
        return "redirect:/departments"; // Перенаправляем на список департаментов после удаления
    }
}
