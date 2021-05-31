package com.model;

public class User {
    protected int id;
    protected String name;
    

    public User() {}

    public User(String name) {
        super();
        this.name = name;
        
    }

    public User(int id, String name) {
        super();
        this.id = id;
        this.name = name;
        
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
}