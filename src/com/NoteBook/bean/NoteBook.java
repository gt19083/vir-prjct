package com.NoteBook.bean;

public class NoteBook {
    protected int id;
    protected String name;
    


    public NoteBook() {}

    public NoteBook(String name) {
        super();
        this.name = name;

    }

    public NoteBook(int id, String name) {
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