package com.Adily.Project.demo.Controller;

import com.Adily.Project.demo.Service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("driver")
public class DriverController {
    @Autowired
    private DriverService service;
   @GetMapping("/ActiveRequests")
   public ResponseEntity<?> showactiverequest(@RequestParam String status, Authentication authentication){
       String username=authentication.getName();;
       return new ResponseEntity(service.showactiverequest(status), HttpStatus.CREATED);
   }

    @PutMapping("/assignBooking")
    public ResponseEntity<?> assignbook(@RequestParam int bookid, Authentication authentication){
        String username=authentication.getName();
        return new ResponseEntity(service.assignbook(username,bookid), HttpStatus.CREATED);
    }

}
