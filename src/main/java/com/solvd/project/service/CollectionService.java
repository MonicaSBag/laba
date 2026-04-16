package com.solvd.project.service;

import com.solvd.project.models.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectionService {

    public void demoCollections(Client client, Supplier supplier, Crew crew) {

        Set<String> uniqueRoles = Arrays.stream(crew.getHandyman())
                .map(People::getRole)
                .collect(Collectors.toSet());
        uniqueRoles.add(crew.getArchitect().getRole());
        uniqueRoles.add(crew.getSiteManager().getRole());

        List<String> workers = Stream.of(crew.getArchitect(), crew.getSiteManager())
                .map(p -> p.getName() + "-" + p.getRole())
                .collect(Collectors.toList());

        System.out.println("Person in charge: " + workers);
        System.out.println("Roles in team: " + uniqueRoles);
    }
}
