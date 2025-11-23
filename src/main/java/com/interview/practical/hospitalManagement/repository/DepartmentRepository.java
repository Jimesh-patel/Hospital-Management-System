package com.interview.practical.hospitalManagement.repository;

import com.interview.practical.hospitalManagement.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}