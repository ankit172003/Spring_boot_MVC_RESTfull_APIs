package com.SP.week2.SpringBootWeb.repositories;

import com.SP.week2.SpringBootWeb.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Long>{


}
