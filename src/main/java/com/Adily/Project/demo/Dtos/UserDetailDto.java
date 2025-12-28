package com.Adily.Project.demo.Dtos;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UserDetailDto {
    @Column(nullable=false, unique=true)
    private String name;
    @Column(nullable=false, unique=true)
    private String password;
    private String role;
}
