package com.solvd.project;


import com.solvd.project.models.*;
import com.solvd.project.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class Main {
    public static void main(String[] args){
    final Logger logger = LogManager.getLogger(Main.class);

    Client client = new Client("Monica Cliente", "698-25825-548");

    Supplier supplier = new Supplier("Material for Construction S.R.L",
            "96.888.486-9", "6566560", "construction@gmail.com");

    People arquitect = new People("Luisa Lopez", "Architect");
    People siteManager = new People("Victor Brandam", "Site Manager");
    People[] handyman = {
            new People("Pedro Vazques", "Handyman"),
            new People("Luis Perez", "Handyman"),
            new People("Carlos Gonzales", "Handyman"),
    };

    Crew crew = new Crew(arquitect, siteManager, handyman);

    CollectionService newCollectionService = new CollectionService();
    newCollectionService.demoCollections(client, supplier, crew);

    HouseProject project = new HouseProject(
            200,
            "high",
            client,
            supplier,
            crew
    );

    if (!project.isValid()) {
        logger.error("Invalid Project");
        return;
    }

    GenericContainer<HouseProject> projectContainer = new GenericContainer<>(project);

    Repository<HouseProject> projectRepository = new Repository<>();
    projectRepository.add(projectContainer.getValue());

    logger.info(projectContainer.getValue().generateReport());
    logger.info("Projects in repo: " + projectRepository.getList());
    }
}

