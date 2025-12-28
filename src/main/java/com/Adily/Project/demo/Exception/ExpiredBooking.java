package com.Adily.Project.demo.Exception;

public class ExpiredBooking extends RuntimeException{
    public ExpiredBooking(String message){
        super(message);
    }
    public ExpiredBooking(){
        super("Booking is Expired");
    }
}
