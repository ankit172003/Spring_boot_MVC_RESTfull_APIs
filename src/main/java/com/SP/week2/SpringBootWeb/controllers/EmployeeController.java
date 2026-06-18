package com.SP.week2.SpringBootWeb.controllers;

import com.SP.week2.SpringBootWeb.dto.EmployeeDTO;
import com.SP.week2.SpringBootWeb.service.EmployeeService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

// All learnings :-

@RestController
//1.
@RequestMapping("/employees") // mutual mapping
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    // 2. Demo controller
    @GetMapping("/getSecretMessage" )
    public String getMySuperSecretMessage(){
        return "Secret message: asdfghijkmnop";
    }

    //3. With @PathVariable
    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long employeeId){
        Optional<EmployeeDTO> employeeDTO = employeeService.getEmployeeById(employeeId);
        return employeeDTO.map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1))
                .orElse(ResponseEntity.notFound().build());
    }

    /* 4.
    if want to keep @GetMapping("/{employeeId}")
    name different in (@PathVariable Long id)

    so then mention like this :-
    (@PathVariable(name = "employeeId") Long id)

    now it works!!!!
    */

    //5. @RequestParam
    //    @GetMapping
    //    public String getAllEmployeeDTOS(@RequestParam(required = false) Integer age){
    //        return " Hi age "+age;
    //    }
    // (required = false) has to be mention otherwise work as @PathVariable
    // this method will still run if no age passed along url as its optional @RequestParam

    @GetMapping("/getAll")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployeeDTOS(@RequestParam(required = false) Integer age){
        return ResponseEntity.ok(employeeService.getAllEmployee());
    }

    //6. @PostMapping - @RequestBody
    // if the url of a get and post request exactly same and when we run it on browser it
    // automatically runs get url and gives its response
    @PostMapping("/add")
    public ResponseEntity<EmployeeDTO> createNewEmployee(@RequestBody @Valid EmployeeDTO employee){
        EmployeeDTO savedEmployee = employeeService.createNewEmployee(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    //8. @PutMapping
    @PutMapping("/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployeeById(@RequestBody EmployeeDTO employeeDTO, @PathVariable Long employeeId){
        return ResponseEntity.ok(employeeService.updateEmployeeById(employeeId, employeeDTO));
    }
    // left endpoint annotations will be covered in later merges

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployeeById ( @PathVariable Long employeeId){
       boolean gotDeleted = employeeService.deleteEmployeeById(employeeId);
        if(gotDeleted) return ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{employeeId}")
    public ResponseEntity<EmployeeDTO> updatePartialEmployeeById(@RequestBody Map<String, Object> Updates, @PathVariable Long employeeId){
        EmployeeDTO employeeDTO = employeeService.updatePartialEmployeeById(Updates,employeeId);
        if(employeeDTO == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(employeeDTO);
    }
}
