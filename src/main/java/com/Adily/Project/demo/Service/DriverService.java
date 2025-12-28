package com.Adily.Project.demo.Service;

import com.Adily.Project.demo.Entity.BookingInfo;
import com.Adily.Project.demo.Entity.UserInfo;
import com.Adily.Project.demo.Exception.AllreadyBooked;
import com.Adily.Project.demo.Exception.ExpiredBooking;
import com.Adily.Project.demo.Exception.ObjectNotFound;
import com.Adily.Project.demo.Repository.BookingRepo;
import com.Adily.Project.demo.Repository.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverService {
    @Autowired
    private UserRepo urepo;
    @Autowired
    private BookingRepo brepo;


    public List<BookingInfo> showactiverequest(String status) {
        return brepo.findByStatus(status.toUpperCase());
    }

    @Transactional
    public String assignbook(String username,int bookid) {
        UserInfo uinfo=urepo.findByName(username).orElse(null);
        BookingInfo binfo=brepo.findById(bookid).orElse(null);
        if (binfo == null) {
            throw new ObjectNotFound("Booking Not Found");
        }

        int row=brepo.updatebooking(bookid);
        if(row==0){
            if (!"REQUESTED".equals(binfo.getStatus())){
                throw new AllreadyBooked("Allready Booked");
            }

            else{
                binfo.setStatus("EXPIRED");
               throw new ExpiredBooking("Booking is Expired cant book");
            }
        }
        binfo.setDriver(uinfo);
        brepo.save(binfo);
         return "booked successfully";
    }
}
