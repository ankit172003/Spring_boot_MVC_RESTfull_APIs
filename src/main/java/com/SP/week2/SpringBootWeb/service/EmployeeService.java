package com.SP.week2.SpringBootWeb.service;

import com.SP.week2.SpringBootWeb.dto.EmployeeDTO;
import com.SP.week2.SpringBootWeb.entities.EmployeeEntity;
import com.SP.week2.SpringBootWeb.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }


    public EmployeeDTO getEmployeeById(Long employeeId) {
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).orElse(null);
        return modelMapper.map(employeeEntity, EmployeeDTO.class);
    }

    public List<EmployeeDTO> getAllEmployee() {
        List<EmployeeEntity> list =  employeeRepository.findAll();
        return list.stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDTO.class)).collect(Collectors.toList());

    }

    public EmployeeDTO createNewEmployee(EmployeeDTO employee) {
        EmployeeEntity toSaveEntity = modelMapper.map(employee, EmployeeEntity.class);
        EmployeeEntity savedemployeeEntity =  employeeRepository.save(toSaveEntity);
        return modelMapper.map(savedemployeeEntity, EmployeeDTO.class);
    }
}
