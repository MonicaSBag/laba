package com.solvd.project.models;

public abstract class CrewBase {
    protected People architect;
    protected People siteManager;

    public CrewBase(People architect, People siteManager) {
        this.architect = architect;
        this.siteManager = siteManager;
    }
}