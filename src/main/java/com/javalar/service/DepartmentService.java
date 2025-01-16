package com.javalar.service;

import com.javalar.model.Department;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class DepartmentService {

    @PersistenceContext
    private EntityManager em;

    public void create(Department department) {
        em.persist(department);
    }

    public Department findById(Long id) {
        return em.find(Department.class, id);
    }

    public List<Department> findAll() {
        return em.createQuery("SELECT d FROM Department d", Department.class).getResultList();
    }

    public void update(Department department) {
        em.merge(department);
    }

    public void delete(Long id) {
        Department department = findById(id);
        if (department != null) {
            em.remove(department);
        }
    }
}
