package com.javalar.model;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Employee.class)
public abstract class Employee_ {

	public static volatile SingularAttribute<Employee, String> name;
	public static volatile SingularAttribute<Employee, Long> id;
	public static volatile SingularAttribute<Employee, Double> salary;
	public static volatile SingularAttribute<Employee, Department> department;
	public static volatile SingularAttribute<Employee, Integer> age;

	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String SALARY = "salary";
	public static final String DEPARTMENT = "department";
	public static final String AGE = "age";

}

