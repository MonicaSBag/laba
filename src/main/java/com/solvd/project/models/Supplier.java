package com.solvd.project.models;

import com.solvd.project.exceptions.MissingDataException;

public class Supplier extends PersonBase
    implements Identifiable, Reportable, Validatable {

    private String rut;
    private String phoneNumber;
    private String email;

    public Supplier(String name, String rut, String phoneNumber, String email) {
        super(name);
        this.rut = rut;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    @Override
    public String getId(){
        return rut;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String generateReport(){
        return "Supplier:" + name + "\nRUT:" + rut + "\nEmail:" + email;
    }

    @Override
    public boolean isValid() {
        if (name  == null || name.isEmpty())
            throw new MissingDataException("Supplier's name is required");
        if (rut == null || rut.isEmpty())
            throw new MissingDataException("Supplier's RUT is required");
        if (phoneNumber == null || phoneNumber.isEmpty())
            throw new MissingDataException("Supplier's phone number is required");
        if (email == null || email.isEmpty())
            throw new MissingDataException("Supplier's email is required");
        return true;
    }

}
