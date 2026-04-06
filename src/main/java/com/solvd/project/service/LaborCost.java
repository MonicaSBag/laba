package com.solvd.project.service;

import com.solvd.project.models.Crew;
import com.solvd.project.models.People;
import com.solvd.project.models.Reportable;

public class LaborCost
    implements CostCalculable, Reportable {

    private Crew crew;
    private double weeklyCost;

    public LaborCost(Crew crew) {
        this.crew = crew;
    }

    @Override
    public double calcTotal(){
        double total = 0;
        total += crew.getArchitect().getHourlyWage() * 8 * 6;
        total += crew.getSiteManager().getHourlyWage() * 8 * 6;
        for(People p : crew.getHandyman()){
            total += p.getHourlyWage() * 8 * 6;
        }
        this.weeklyCost = total;
        return total;
    }
    @Override
    public String generateReport() {
        return "Labor weekly cost: " + weeklyCost;
    }
}
