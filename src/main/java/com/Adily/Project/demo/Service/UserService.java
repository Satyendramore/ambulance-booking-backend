package com.Adily.Project.demo.Service;

import com.Adily.Project.demo.Dtos.UserDetailDto;
import com.Adily.Project.demo.Entity.BookingInfo;
import com.Adily.Project.demo.Entity.UserInfo;
import com.Adily.Project.demo.Exception.ObjectNotFound;
import com.Adily.Project.demo.Repository.BookingRepo;
import com.Adily.Project.demo.Repository.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service

public class UserService {
    @Autowired
    private UserRepo urepo;
    @Autowired
    private BookingRepo brepo;

     @Transactional
    public String createBooking(String username) {
        UserInfo u=urepo.findByName(username).orElse(null);

         if (u == null) {
             throw new ObjectNotFound("User Not Found");
         }
        BookingInfo book=new BookingInfo();
        book.setBookingDate(LocalDate.now());
        LocalDateTime now = LocalDateTime.now();
        book.setCreateTime(now);
        book.setExpiryTime(now.plusMinutes(2));
        book.setStatus("REQUESTED");
        book.setUser(u);
        brepo.save(book);
        return "Booking created successfully";
    }
    private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);

    @Transactional
    public String usersave(UserDetailDto u) {
         UserInfo ui=new UserInfo();
         ui.setName(u.getName());
         ui.setPassword(encoder.encode(u.getPassword()));
         ui.getRole().add(u.getRole().toUpperCase());
         urepo.save(ui);
         return "Successfully Saved";
    }
}
