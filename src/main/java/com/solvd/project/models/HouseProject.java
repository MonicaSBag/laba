package com.solvd.project.models;

import com.solvd.project.exceptions.InvalidDataTypeException;
import com.solvd.project.exceptions.InvalidOptionException;
import com.solvd.project.exceptions.MissingDataException;
import com.solvd.project.service.CalculableTime;
import com.solvd.project.service.CostCalculable;
import com.solvd.project.service.LaborCost;
import com.solvd.project.service.MaterialCost;

import java.util.LinkedList;
import java.util.List;

public class HouseProject extends ProjectBase
    implements Reportable, Validatable, CalculableTime {

    int squareMeters;
    String materialQuality;
    Client client;
    Supplier supplier;
    Crew crew;

    public HouseProject(int squareMeters, String materialQuality, Client client, Supplier supplier, Crew crew) {
        super(squareMeters);
        this.squareMeters = squareMeters;
        this.materialQuality = materialQuality;
        this.client = client;
        this.supplier = supplier;
        this.crew = crew;
    }

    public String getMaterialQuality() {
        return materialQuality;
    }

    @Override
    public boolean isValid() {
        if(squareMeters <= 0 ) {
            throw new InvalidDataTypeException("Squa Meters should be bigger than 0");
        }
        if (materialQuality == null || materialQuality.isEmpty()){
            throw new MissingDataException("Material Quality should be set");
        }
        if (!materialQuality.equals("low") && !materialQuality.equals("medium") && !materialQuality.equals("high")) {
            throw new InvalidOptionException("Material Quality is not correct: " + materialQuality);
        }
        if (client == null){
            throw new MissingDataException("Client should be set");
        }
        if (!client.isValid()){
            throw new MissingDataException("Client should be valid");
        }
        if (supplier == null){
            throw new MissingDataException("Supplier should be set");
        }
        if (!supplier.isValid()){
            throw new MissingDataException("Supplier should be valid");
        }
        return true;
    }

    @Override
    public int estimateTime(){
        return squareMeters / 20;
    }

    @Override
    public String generateReport(){

        Pair<Client, HouseProject> clientProject =
                new Pair<>(client, this);

        MaterialQuality mq = new MaterialQuality("high");
        int price = mq.getPrice();

        List<String> tasks = new LinkedList<>();
        tasks.add("Kitchen complete remodeling");
        tasks.add("New pool project");
        tasks.add("Entire Floor");
        tasks.add("Entire Roof");

        CostCalculable calculator;
        
        calculator = new MaterialCost(this, mq);
        double materialCost = calculator.calcTotal();
        
        calculator = new LaborCost(crew);
        double laborCost = calculator.calcTotal();
        
        return "Project Details:\n" +
                "Client Info " + "\n" +
                "Client Name: " + clientProject.getKey().getName() + "\n" +
                "Client Rut: " + clientProject.getKey().getId() + "\n" +
                "Supplier contact info\n" +
                "Name : " + supplier.getName() + "\n" +
                "Phone number: " + supplier.getPhoneNumber() + "\n" +
                "Email: " + supplier.getEmail() + "\n" +
                "Material quality: " + materialQuality + "\n" +
                "Material price per m2: " + price + "\n" +
                "Square meters: " + clientProject.getValue().getSquareMeters() + "\n" +
                "Task: " + tasks + "\n" +
                "Estimated time: " + estimateTime() + " weeks" + "\n" +
                "Total Budget" + "\n" + 
                "Material Cost: " + String.format("%,d", (int) materialCost) + "\n" +
                "Labor Cost: " + String.format("%,d", (int) (laborCost * this.estimateTime())) + "\n" +
                "Total cost: " + String.format("%,d", (int) (materialCost + laborCost));
    }

    @Override
    public String toString() {
        return "HouseProject{" +
                ", client=" + client.getName() +
                ", supplier=" + supplier.getName() +
                '}';
    }
}
