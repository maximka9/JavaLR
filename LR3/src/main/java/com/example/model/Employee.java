package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@Entity  // Добавляем аннотацию @Entity, чтобы Spring воспринимал этот класс как сущность
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Employee {

    @Id  // Аннотация для идентификатора сущности
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Стратегия генерации ID
    @XmlElement
    private Long id;

    @XmlElement
    private String name;

    @XmlElement
    private int age;

    @XmlElement
    private double salary;

    @XmlElement(name = "department")
    @ManyToOne  // Указывает на связь многие к одному с департаментом
    private Department department;

    // Конструктор без параметров
    public Employee() {
    }

    // Конструктор с параметрами
    public Employee(String name, int age, double salary, Department department) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.department = department;
    }

    // Геттеры и сеттеры

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
