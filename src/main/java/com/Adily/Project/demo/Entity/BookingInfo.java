package com.Adily.Project.demo.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Data
public class BookingInfo {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int bookingId;
    private LocalDate bookingDate;
    private LocalDateTime createTime;
    private LocalDateTime expiryTime;
    private String status;
    @ManyToOne
    @JoinColumn(name = "userId")
    private UserInfo user;
    @ManyToOne
    @JoinColumn(name = "driverId")
    private  UserInfo driver;


}
