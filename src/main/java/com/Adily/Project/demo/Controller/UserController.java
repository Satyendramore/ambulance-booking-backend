package com.Adily.Project.demo.Controller;

import com.Adily.Project.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService service;

    @PostMapping("book")
    public ResponseEntity<?> createBooking(Authentication authentication){
        String username=authentication.getName();;
        return new ResponseEntity(service.createBooking(username), HttpStatus.CREATED);
    }
}
