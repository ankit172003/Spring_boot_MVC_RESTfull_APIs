package com.SP.week2.SpringBootWeb.service;

import com.SP.week2.SpringBootWeb.dto.EmployeeDTO;
import com.SP.week2.SpringBootWeb.entities.EmployeeEntity;
import com.SP.week2.SpringBootWeb.exceptions.ResourceNotFoundException;
import com.SP.week2.SpringBootWeb.repositories.EmployeeRepository;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }


    public Optional<EmployeeDTO> getEmployeeById(Long employeeId) {
//        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(employeeId);
//        return employeeEntity.map(employeeEntity1 -> modelMapper.map(employeeEntity, EmployeeDTO.class));
//

        return employeeRepository.findById(employeeId).map(employeeEntity  -> modelMapper.map(employeeEntity, EmployeeDTO.class));
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

    public EmployeeDTO updateEmployeeById(Long employeeId, EmployeeDTO employeeDTO){
        isExistByEmployeeId(employeeId);
        EmployeeEntity enteredEmployee = modelMapper.map(employeeDTO,EmployeeEntity.class);
        enteredEmployee.setId(employeeId);
        employeeRepository.save(enteredEmployee);
        return modelMapper.map(enteredEmployee, EmployeeDTO.class);
    }

    //Common method used in multiple important method
    public void isExistByEmployeeId(Long employeeId){
        boolean exists = employeeRepository.existsById(employeeId);
        if(!exists) throw new ResourceNotFoundException("Employee not found with id "+ employeeId);
    }


    public boolean deleteEmployeeById(Long employeeId) {
        isExistByEmployeeId(employeeId);
        employeeRepository.deleteById(employeeId);
        return true;

    }

// Study topic reflection to understand better
    public EmployeeDTO updatePartialEmployeeById(Map<String, Object> updates, Long employeeId) {
        isExistByEmployeeId(employeeId);
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).get();
        // new concept reflection we will map each available value
        updates.forEach((field,value) -> {
            Field fieldToBeUpdated = ReflectionUtils.findField(EmployeeEntity.class,field);
            if(fieldToBeUpdated != null){
                fieldToBeUpdated.setAccessible(true);
                ReflectionUtils.setField(fieldToBeUpdated,employeeEntity,value);
            }
          });
        return modelMapper.map(employeeRepository.save(employeeEntity),EmployeeDTO.class);
    }

    public EmployeeDTO updatePassword(@Valid Map<String, Object> update, Long id) {
        isExistByEmployeeId(id);
        EmployeeEntity employeeEntity =  employeeRepository.findById(id).get();

        update.forEach((field,value)-> {
            Field fieldToBeUpdated = ReflectionUtils.findField(EmployeeEntity.class,field);
            if(fieldToBeUpdated != null){
                fieldToBeUpdated.setAccessible(true);
                ReflectionUtils.setField(fieldToBeUpdated,employeeEntity,value);
            }
        });
        EmployeeEntity employee = employeeRepository.save(employeeEntity);
        return modelMapper.map(employee,EmployeeDTO.class);
    }
}
