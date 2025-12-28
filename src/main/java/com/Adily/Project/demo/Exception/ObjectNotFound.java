package com.Adily.Project.demo.Exception;

public class ObjectNotFound extends RuntimeException{
    public ObjectNotFound(String message){
        super(message);
    }
    public ObjectNotFound(){
        super("Object is not found");
    }
}
