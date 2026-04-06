package com.solvd.project.models;

import com.solvd.project.exceptions.MissingDataException;

public class Client extends PersonBase
    implements Identifiable, Reportable, Validatable{

    private String rut;

    public Client(String name, String rut) {
        super(name);
        this.rut = rut;
    }

    @Override
            public String getId() {
            return rut;
            }

    @Override
    public String generateReport(){
        return "Client: " + name + "\nRUT:" + rut;
    }

    @Override
    public boolean isValid() {
        if (name  == null || name.isEmpty())
            throw new MissingDataException("Client's name is required");
        if (rut == null || rut.isEmpty())
            throw new MissingDataException("Client's RUT is required");
        return true;
    }
}

