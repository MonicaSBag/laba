package com.solvd.project.models;

public abstract class ProjectBase {
    protected int squareMeters;

    public ProjectBase(int squareMeters) {
        this.squareMeters = squareMeters;
    }

    public int getSquareMeters() {
        return squareMeters;
    }
}