package com.javalar.service;

import com.javalar.model.Employee;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class EmployeeService {

    @PersistenceContext
    private EntityManager em;

    public void create(Employee employee) {
        em.persist(employee);
    }

    public Employee findById(Long id) {
        return em.find(Employee.class, id);
    }

    public List<Employee> findAll() {
        return em.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
    }

    public void update(Employee employee) {
        em.merge(employee);  // Это обновит или создаст новый объект
    }

    public void delete(Long id) {
        Employee employee = findById(id);
        if (employee != null) {
            em.remove(employee);
        }
    }
}

