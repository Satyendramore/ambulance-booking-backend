package com.Adily.Project.demo.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseModel {
    String message;
    private boolean success;
    private HttpStatus status;
}