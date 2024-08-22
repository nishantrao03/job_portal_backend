package com.tpcportal.backend.controller;

import com.tpcportal.backend.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private RegistrationService registrationService;

    @GetMapping("/hello")
    public String sayHello(){
        System.out.println("vicycyi");
        return "Welcome admin";
    }

    @PostMapping("/give-role")
    public ResponseEntity<?> giveRole(@RequestParam String rollNo, @RequestParam String role) {
        try {
            System.out.println("Debug 11");;
            boolean isRoleAdded = registrationService.addRole(rollNo, role);
            System.out.println("Debug 12");
            if (isRoleAdded) {
                return new ResponseEntity<>("Role added successfully", HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("Failed to add role", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to add role", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
