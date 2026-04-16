package com.solvd.project.models;

import java.util.Arrays;

public class Crew extends CrewBase{
    People architect;
    People siteManager;
    People[] handyman;

    public Crew(People architect, People siteManager, People[] handyman){
        super(architect, siteManager);
        this.architect = architect;
        this.siteManager = siteManager;
        this.handyman = handyman;
    }

    public People getArchitect() {
        return architect;
    }

    public People getSiteManager() {
        return siteManager;
    }

    public People[] getHandyman() {
        return handyman;
    }

    public void setArchitect(People architect) {
        this.architect = architect;
    }

    public void setSiteManager(People siteManager) {
        this.siteManager = siteManager;
    }

    public void setHandyman(People[] handyman) {
        this.handyman = handyman;
    }

    @Override
    public String toString(){
        StringBuilder string = new StringBuilder();

        string.append("Team:\n");
        string.append("Architect: ").append(architect).append("\n");
        string.append("Site Manager: ").append(siteManager).append("\n");
        string.append("Handymen:\n");
        if(handyman != null){
            Arrays.stream(handyman)
                    .map(i -> "-" + i + "\n")
                    .forEach(string::append);
        }
        return string.toString();
    }
}
