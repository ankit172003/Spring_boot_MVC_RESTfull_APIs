package com.SP.week2.SpringBootWeb.service;

import com.SP.week2.SpringBootWeb.dto.DepartmentDTO;
import com.SP.week2.SpringBootWeb.dto.EmployeeDTO;
import com.SP.week2.SpringBootWeb.entities.DepartmentEntity;
import com.SP.week2.SpringBootWeb.entities.EmployeeEntity;
import com.SP.week2.SpringBootWeb.exceptions.ResourceNotFoundException;
import com.SP.week2.SpringBootWeb.repositories.DepartmentRepository;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    public DepartmentService(DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

    public DepartmentEntity findDepartmentById(Long id){
        DepartmentEntity departmentEntity = departmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Department with id "+ id + " was not found"));
        return departmentEntity;
     }

    public DepartmentDTO getDepartmentById(@Valid Long id){
        DepartmentEntity departmentEntity = findDepartmentById(id);
        DepartmentDTO departmentDTO = modelMapper.map(departmentEntity,DepartmentDTO.class);
        return departmentDTO;

    }

    public DepartmentDTO savedDepartment(@Valid DepartmentDTO department) {
        DepartmentEntity departmentToBeSaved = modelMapper.map(department,DepartmentEntity.class);
        DepartmentEntity savedDepartment = departmentRepository.save(departmentToBeSaved);
        DepartmentDTO savedAndReturnDept = modelMapper.map(savedDepartment,DepartmentDTO.class);
        return savedAndReturnDept;
    }

    public List<DepartmentDTO> getAllDepartment() {
        List<DepartmentEntity> departments = departmentRepository.findAll();
        List<DepartmentDTO> departmentsDTO =
                departments.stream()
                .map(dept -> modelMapper.map(dept,DepartmentDTO.class))
                        .collect(Collectors.toList());
        return departmentsDTO;
    }

    public DepartmentDTO updateDepartment(@Valid DepartmentDTO departmentDTO, Long id) {
        checkDepartmentExists(id);
        DepartmentEntity savedToBeDepartment = modelMapper.map(departmentDTO,DepartmentEntity.class);
        savedToBeDepartment.setId(id);
        DepartmentEntity savedDept = departmentRepository.save(savedToBeDepartment);
        DepartmentDTO sendSavedDept = modelMapper.map(savedDept,DepartmentDTO.class);
        return sendSavedDept;

    }

    private void checkDepartmentExists(Long id) {
        boolean exists = departmentRepository.existsById(id);
        if(!exists) throw new ResourceNotFoundException("No such department exists with id "+ id);
    }

    public String deleteById(@Valid Long id) {
        checkDepartmentExists(id);
        departmentRepository.deleteById(id);
        return "Dept deleted successfully with id "+id;
    }

    public DepartmentDTO updateDeptPartial(@Valid Map<String, Object> updates, Long id) {
        checkDepartmentExists(id);
        DepartmentEntity department = departmentRepository.findById(id).get();
        updates.forEach((field,value) -> {
            Field fieldToBeUpdated = ReflectionUtils.findField(DepartmentEntity.class,field);
            if (fieldToBeUpdated !=null){
                fieldToBeUpdated.setAccessible(true);
                ReflectionUtils.setField(fieldToBeUpdated,department,value);
            }

        });
        DepartmentDTO dept = modelMapper.map(departmentRepository.save(department),DepartmentDTO.class);
        return dept;
    }
}
