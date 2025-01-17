package com.example.service;

import com.example.model.Department;
import com.example.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    // Сохранение или обновление департамента
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    // Получение всех департаментов
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    // Получение департамента по ID
    public Optional<Department> getDepartmentById(Long id) {
        return departmentRepository.findById(id);
    }

    // Удаление департамента
    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }
}
