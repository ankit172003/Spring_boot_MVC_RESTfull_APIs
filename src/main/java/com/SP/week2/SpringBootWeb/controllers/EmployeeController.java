package com.SP.week2.SpringBootWeb.controllers;

import com.SP.week2.SpringBootWeb.dto.EmployeeDTO;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

// All learnings :-

@RestController
//1.
@RequestMapping("/employees") // mutual mapping
public class EmployeeController {

    // 2. Demo controller
    @GetMapping(path = "/getSecretMessage" )
    public String getMySuperSecretMessage(){
        return "Secret message: asdfghijkmnop";
    }

    //3. With @PathVariable
    @GetMapping("/{employeeId}")
    public EmployeeDTO getEmployeeById(@PathVariable Long employeeId){
        return new EmployeeDTO(employeeId,"A","ak@gmail",12, LocalDate.of(2026,6,16),true);

    }

    /* 4.
    if want to keep @GetMapping("/{employeeId}")
    name different in (@PathVariable Long id)

    so then mention like this :-
    (@PathVariable(name = "employeeId") Long id)

    now it works!!!!
    */

    //5. @RequestParam
    @GetMapping
    public String getAllEmployeeDTOS(@RequestParam(required = false) Integer age){
        return "Hi age "+age;
    }
    // (required = false) has to be mention otherwise work as @PathVariable
    // this method will still run if no age passed along url as its optional @RequestParam

    //6. @PostMapping
    // if the url of a get and post request exactly same and when we run it on browser it
    // automatically runs get url and gives its response
    @PostMapping
    public String createNewEmployee(){
        return "Hello from POST";
    }


    //7. @PutMapping
    @PutMapping
    public String updateEmployeeById(){
        return "Hello from Put";
    }

    //8. RequestBody
    @PostMapping("/new")
    public EmployeeDTO createNewEmployee1(@RequestBody EmployeeDTO inputEmployee){
        inputEmployee.setId(100L);
        return  inputEmployee;
    }

    // left endpoint annotations will be covered in later merges
}
