package com.solvd.project.service;

import com.solvd.project.models.Crew;
import com.solvd.project.models.People;
import com.solvd.project.models.Reportable;

import java.util.Arrays;
import java.util.stream.Stream;

public class LaborCost
    implements CostCalculable, Reportable {

    private Crew crew;
    private double weeklyCost;

    public LaborCost(Crew crew) {
        this.crew = crew;
    }

    @Override
    public double calcTotal(){
        double total = Stream.concat(
                        Stream.of(crew.getArchitect(), crew.getSiteManager()),
                        Arrays.stream(crew.getHandyman())
                )
                .mapToDouble(p -> p.getHourlyWage() * 8 * 6)
                .sum();
        this.weeklyCost = total;
        return total;
    }
    @Override
    public String generateReport() {
        return "Labor weekly cost: " + weeklyCost;
    }
}
