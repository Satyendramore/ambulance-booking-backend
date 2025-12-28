package com.Adily.Project.demo.Exception;

public class AllreadyBooked extends RuntimeException{
    public AllreadyBooked(String message){
        super(message);
    }
    public AllreadyBooked(){
        super("Booking is done Allready");
    }
}
