package com.example.service;

import com.example.model.Department;
import com.example.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final JmsTemplate jmsTemplate;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository, JmsTemplate jmsTemplate) {
        this.departmentRepository = departmentRepository;
        this.jmsTemplate = jmsTemplate;
    }

    // Сохранение или обновление департамента
    public Department saveDepartment(Department department) {
        Department savedDepartment = departmentRepository.save(department);
        sendJmsMessage("Department saved: " + savedDepartment.getId());
        return savedDepartment;
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
        sendJmsMessage("Department deleted: " + id);
    }

    // Отправка JMS-сообщений
    private void sendJmsMessage(String message) {
        jmsTemplate.convertAndSend("changeQueue", message);
    }
}
