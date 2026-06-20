package com.SP.week2.SpringBootWeb.controllers;



import com.SP.week2.SpringBootWeb.dto.DepartmentDTO;

import com.SP.week2.SpringBootWeb.service.DepartmentService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dept")
public class DepartmentController {

    private final DepartmentService departmentService;


    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("{id}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable @Valid Long id){
        DepartmentDTO departmentDTO = departmentService.getDepartmentById(id);
        return new ResponseEntity<>(departmentDTO, HttpStatus.OK);

    }

    @GetMapping("/all")
    public ResponseEntity<List<DepartmentDTO>> getAllDepartment(){
        List<DepartmentDTO> departments = departmentService.getAllDepartment();
        return new ResponseEntity<>(departments,HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<DepartmentDTO> createDepartment(@RequestBody @Valid DepartmentDTO department){
        DepartmentDTO savedDepartment = departmentService.savedDepartment(department);
        return new ResponseEntity<>(savedDepartment,HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDTO> updateDepartment(@RequestBody @Valid DepartmentDTO departmentDTO, @PathVariable Long id){
        DepartmentDTO updatedDepartment =  departmentService.updateDepartment(departmentDTO, id);
        return new ResponseEntity<>(updatedDepartment,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable @Valid Long id){
        String result = departmentService.deleteById(id);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DepartmentDTO> updateDeptPartial(@RequestBody @Valid Map<String,Object> updates,@PathVariable Long id){
        DepartmentDTO departmentDTO = departmentService.updateDeptPartial(updates,id);
        return new ResponseEntity<>(departmentDTO,HttpStatus.OK);
    }

}
