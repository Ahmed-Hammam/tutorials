package com.tutorial.core.controller;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tutorial.core.model.Employee;
import com.tutorial.core.service.EmployeeService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequestMapping("/employees")
@RestController
public class EmployeeController {

	@Autowired
    private EmployeeService employeeService;

	@PostMapping
    public void create(@RequestBody Employee e) {
        employeeService.create(e);
    }
 
    @GetMapping(value="/{id}" ,produces="application/json")
    public ResponseEntity<Mono<Employee>> findById(@PathVariable("id") Integer id) {
        Mono<Employee> e = employeeService.findById(id);
        HttpStatus status = e != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<Employee>>(e, status);
    }
 
    @GetMapping(value="{name}" ,produces="application/json")
    public Flux<Employee> findByName(@PathVariable("name") String name) {
        return employeeService.findByName(name);
    }
 
    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Employee> findAll() {
        Flux<Employee> emps = employeeService.findAll().delayElements(Duration.ofMillis(2500));
        return emps;
    }
 
    @PutMapping
    public Mono<Employee> update(@RequestBody Employee e) {
        return employeeService.update(e);
    }
 
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") Integer id) {
        employeeService.delete(id).subscribe();
    }
}
