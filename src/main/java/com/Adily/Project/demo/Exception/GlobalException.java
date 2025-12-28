package com.Adily.Project.demo.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice

public class GlobalException {
     @ExceptionHandler(ObjectNotFound.class)
    public ResponseEntity<?> objectnotfound(Exception e){
         ResponseModel rs=new ResponseModel();
         rs.setMessage(e.getMessage());
         rs.setSuccess(false);
         rs.setStatus(HttpStatus.NOT_FOUND);
         return new ResponseEntity(rs,HttpStatus.NOT_FOUND);
     }
    @ExceptionHandler(AllreadyBooked.class)
    public ResponseEntity<?> allreadyBooked(Exception e){
        ResponseModel rs=new ResponseModel();
        rs.setMessage(e.getMessage());
        rs.setSuccess(false);
        rs.setStatus(HttpStatus.CONFLICT);
        return new ResponseEntity(rs,HttpStatus.CONFLICT);
    }
    @ExceptionHandler(ExpiredBooking.class)
    public ResponseEntity<?> expiredBooking(Exception e){
        ResponseModel rs=new ResponseModel();
        rs.setMessage(e.getMessage());
        rs.setSuccess(false);
        rs.setStatus(HttpStatus.GONE);
        return new ResponseEntity(rs,HttpStatus.GONE);
    }


}
