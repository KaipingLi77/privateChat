package com.huge.Model;

import org.springframework.stereotype.Component;

@Component
public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String phone;
    private String position;
    
    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(){
        this.firstName = firstName
    }

    
}
