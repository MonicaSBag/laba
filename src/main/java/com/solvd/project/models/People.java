package com.solvd.project.models;

import  com.solvd.project.exceptions.InvalidOptionException;

public class People extends PersonBase {
    private String role;
    private double hourlyWage;

    public People(String name, String role) {
        super(name);
        this.role = role;
        switch (role) {
            case "Architect" -> this.hourlyWage = 5000.0;
            case "Site Manager" -> this.hourlyWage = 4000.0;
            case "Handyman" -> this.hourlyWage = 2500.0;
            default -> throw new InvalidOptionException("Invalid role: " + role);
        }
    }

    public String getRole() {
        return role;
    }

    public double getHourlyWage(){
        return hourlyWage;
    };

    public  void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setHourlyWage(Double hourlyWage){
        this.hourlyWage = hourlyWage;
    }

    @Override
    public String toString(){
        double weeklySalary = (hourlyWage * 8) * 6;
        return name + "\nHourly Salary: " + hourlyWage +
                "\nWeekly Salary: " + weeklySalary;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        People people = (People) obj;

        if (!name.equals(people.name))
            return false;
        return role.equals(people.role);
    }
}


