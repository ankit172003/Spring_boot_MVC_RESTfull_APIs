package com.SP.week2.SpringBootWeb.controllers;

import com.SP.week2.SpringBootWeb.dto.EmployeeDTO;
import com.SP.week2.SpringBootWeb.service.EmployeeService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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
    public EmployeeDTO getEmployeeById(@PathVariable Long employeeId){
        return employeeService.getEmployeeById(employeeId);
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
    public List<EmployeeDTO> getAllEmployeeDTOS(@RequestParam(required = false) Integer age){
        return employeeService.getAllEmployee();
    }

    //6. @PostMapping - @RequestBody
    // if the url of a get and post request exactly same and when we run it on browser it
    // automatically runs get url and gives its response
    @PostMapping("/add")
    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO employee){
        return employeeService.createNewEmployee(employee);
    }

    //8. @PutMapping
    @PutMapping
    public String updateEmployeeById(){
        return "Hello from Put";
    }
    // left endpoint annotations will be covered in later merges
}
