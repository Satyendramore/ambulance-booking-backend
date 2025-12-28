package com.Adily.Project.demo.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data

public class UserInfo {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int userId;
    @Column(nullable=false, unique=true)
    private String name;
    @Column(nullable=false, unique=true)
    private String password;
    @ElementCollection
    private List<String> role=new ArrayList<>();
}
