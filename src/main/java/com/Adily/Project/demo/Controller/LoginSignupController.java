package com.Adily.Project.demo.Controller;

import com.Adily.Project.demo.Dtos.UserDetailDto;
import com.Adily.Project.demo.Security.jwtservice;
import com.Adily.Project.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoginSignupController {
    @Autowired
    private UserService service;
    @PostMapping("/public/register")
    public ResponseEntity<?> registeruser(@RequestBody UserDetailDto u)
    {
        return new ResponseEntity(service.usersave(u), HttpStatus.OK) ;
    }

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private jwtservice jservice;

    @PostMapping("/public/login")
    public String loginuser(@RequestBody UserDetailDto u)
    {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(u.getName(), u.getPassword())
        );

        if (auth.isAuthenticated()) {
            return jservice.generateToken(u.getName(), List.of(u.getRole().toUpperCase()));
        } else {
            return "fail";
        }
    }
}
