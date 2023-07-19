package com.example.back_end.controller;

import com.example.back_end.service.customer.ICustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("api/customer")
@CrossOrigin("*")
public class CustomerController {
    @Autowired
    private ICustomersService customerService;

    @GetMapping("")
    public ResponseEntity<?> findByNameCustomer(@RequestParam(required = false, defaultValue = "") String name,
                                                @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC, size = 3) Pageable pageable) {
        return new ResponseEntity<>(customerService.findByNameProduct(name, pageable), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id")Long id){
        customerService.deleteById(id);
    }
}