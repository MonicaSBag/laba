package com.solvd.project.models;

public abstract class PersonBase {
    protected String name;

    public PersonBase(String name){
        this.name = name;
    }

    public String getName(){
        return  name;
    }
}
