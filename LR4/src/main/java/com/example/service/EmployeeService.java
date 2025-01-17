package com.example.service;

import com.example.model.Employee;
import com.example.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final JmsTemplate jmsTemplate;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, JmsTemplate jmsTemplate) {
        this.employeeRepository = employeeRepository;
        this.jmsTemplate = jmsTemplate;
    }

    // Сохранение или обновление сотрудника
    public Employee saveEmployee(Employee employee) {
        Employee savedEmployee = employeeRepository.save(employee);
        sendJmsMessage("Employee saved: " + savedEmployee.getId());
        return savedEmployee;
    }

    // Удаление сотрудника
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
        sendJmsMessage("Employee deleted: " + id);
    }

    // Получение всех сотрудников
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Получение сотрудника по ID
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    // Отправка JMS-сообщений
    private void sendJmsMessage(String message) {
        jmsTemplate.convertAndSend("changeQueue", message);
    }
}
