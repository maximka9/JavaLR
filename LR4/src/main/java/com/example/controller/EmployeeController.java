package com.example.controller;

import com.example.model.Employee;
import com.example.service.DepartmentService;
import com.example.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    // Отображаем список сотрудников
    @GetMapping
    public String listEmployees(Model model) {
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees);
        return "employee/list";
    }

    // Форма для добавления нового сотрудника
    @GetMapping("/add")
    public String addEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "employee/add";
    }

    // Сохраняем нового сотрудника или обновляем существующего
    @PostMapping("/add")
    public String saveEmployee(@ModelAttribute Employee employee) {
        employeeService.saveEmployee(employee);
        return "redirect:/employees";
    }

    // Форма для редактирования существующего сотрудника
    @GetMapping("/edit/{id}")
    public String editEmployeeForm(@PathVariable("id") Long id, Model model) {
        Optional<Employee> employeeOptional = employeeService.getEmployeeById(id);
        if (employeeOptional.isPresent()) {
            model.addAttribute("employee", employeeOptional.get());
            model.addAttribute("departments", departmentService.getAllDepartments());
            return "employee/edit";
        }
        return "redirect:/employees";
    }

    // Обновляем данные сотрудника
    @PostMapping("/edit/{id}")
    public String updateEmployee(@PathVariable("id") Long id, @ModelAttribute Employee employee) {
        employee.setId(id);
        employeeService.saveEmployee(employee);
        return "redirect:/employees";
    }

    // Удаляем сотрудника
    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Long id) {
        employeeService.deleteEmployee(id);
        return "redirect:/employees";
    }
}

