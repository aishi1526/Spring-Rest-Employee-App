package com.app.dao;

import org.springframework.data.repository.CrudRepository;

import com.app.model.Employee;

public interface EmployeeDAO extends CrudRepository<Employee, Integer> {

}
