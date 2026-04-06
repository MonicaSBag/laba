package com.solvd.project.service;

import com.solvd.project.models.*;
import java.util.*;

public class CollectionService {

    public void demoCollections(Client client, Supplier supplier, Crew crew) {

        Set<String> uniqueRoles = new HashSet<>();
        uniqueRoles.add(crew.getArchitect().getRole());
        uniqueRoles.add(crew.getSiteManager().getRole());
        for (People p : crew.getHandyman()) uniqueRoles.add(p.getRole());

        List<String> workers = new ArrayList<>();
        workers.add(crew.getArchitect().getName() +"-"+ crew.getArchitect().getRole());
        workers.add(crew.getSiteManager().getName() +"-"+ crew.getSiteManager().getRole());

        System.out.println("Person in charge: " + workers);
        System.out.println("Roles in team: " + uniqueRoles);
    }
}
