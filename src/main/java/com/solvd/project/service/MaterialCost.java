package com.solvd.project.service;

import com.solvd.project.models.HouseProject;
import com.solvd.project.exceptions.InvalidOptionException;
import com.solvd.project.models.MaterialQuality;
import com.solvd.project.models.Reportable;

public class MaterialCost
    implements CostCalculable, Reportable {

    private final HouseProject project;
    private final MaterialQuality mq;
    double totalMaterialCost;

    public MaterialCost(HouseProject project, MaterialQuality mq) {
        this.project = project;
        this.mq = mq;
    }

    @Override
    public  double calcTotal() {
        return switch (project.getMaterialQuality()) {
            case "low", "medium", "high" -> mq.getPrice() * project.getSquareMeters();
            default -> throw new InvalidOptionException("Material Quality is not correct: " + project.getMaterialQuality());
        };
    }

    @Override
    public String generateReport() {
        return "Material cost: " + totalMaterialCost;
    }
}