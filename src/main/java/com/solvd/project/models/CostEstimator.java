package com.solvd.project.models;

@FunctionalInterface
public interface CostEstimator {
    double estimate(HouseProject project, MaterialQuality quality);
}
